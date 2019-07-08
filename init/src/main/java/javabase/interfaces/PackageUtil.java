package javabase.interfaces;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

public class PackageUtil {

    private static Set<Class<?>> getclass() {
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        boolean flag = true;//是否循环迭代

        String packName = "javabase.interfaces";
//  String packName = "org.jdom";
        String packDir = packName.replace(".", "/");
        Enumeration<URL> dir;
        try {
            dir = Thread.currentThread().getContextClassLoader().getResources(packDir);
            while(dir.hasMoreElements()){
                URL url = dir.nextElement();
                System.out.println("url:***" + url);
                String protocol = url.getProtocol();//获得协议号
                if("file".equals(protocol)){
                    System.err.println("file类型的扫描");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    System.out.println("filePath :" + filePath);
                    findAndAddClassesInPackageByFile(packName, filePath,flag,classes);
                }else if("jar".equals(protocol)){
                    System.err.println("jar类型扫描");
                    JarFile jar;
                    jar = ((JarURLConnection)url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entries = jar.entries();
                    while(entries.hasMoreElements()){
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        System.out.println(">>>>:" + name);
                        //......
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(classes.size());
        return classes;

    }

    private static void findAndAddClassesInPackageByFile(String packName, String filePath, final boolean flag, Set<Class<?>> classes) {
        File dir = new File(filePath);
        if( !dir.exists() || !dir.isDirectory()){
            System.out.println("此路径下没有文件");
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                return flag && pathname.isDirectory() || pathname.getName().endsWith(".class");
            }
        });
        for (File file : dirfiles) {
            if(file.isDirectory()){//如果是目录，继续扫描
                findAndAddClassesInPackageByFile(packName + "." + file.getName(),file.getAbsolutePath(),flag,classes);
            }else{//如果是文件
                String className = file.getName().substring(0,file.getName().length() - 6);
                System.out.println("类名：" +className);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(getclass());
    }






    /** jar中的文件路径分隔符 */
    private static final char SLASH_CHAR = '/';
    /** 包名分隔符 */
    private static final char DOT_CHAR = '.';

    /**
     * 在当前项目中寻找指定包下的所有类
     *
     * @param packageName 用'.'分隔的包名
     * @param recursion 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClass(String packageName, boolean recursive) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            //获取当前线程的类装载器中相应包名对应的资源
            Enumeration<URL> iterator = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(DOT_CHAR, File.separatorChar));
            while (iterator.hasMoreElements()) {
                URL url = iterator.nextElement();
                String protocol = url.getProtocol();
                System.out.println(protocol);
                List<Class<?>> childClassList = Collections.emptyList();
                switch (protocol) {
                    case "file":
                        childClassList = getClassInFile(url, packageName, recursive);
                        break;
                    case "jar":
                        childClassList = getClassInJar(url, packageName, recursive);
                        break;
                    default:
                        //在某些WEB服务器中运行WAR包时，它不会像TOMCAT一样将WAR包解压为目录的，如JBOSS7，它是使用了一种叫VFS的协议
                        System.out.println("unknown protocol " + protocol);
                        break;
                }
                classList.addAll(childClassList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的文件或文件夹中寻找指定包下的所有类
     *
     * @param filePath 包的路径
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInFile(String filePath, String packageName, boolean recursive) {
        Path path = Paths.get(filePath);
        return getClassInFile(path, packageName, recursive);
    }

    /**
     * 在给定的文件或文件夹中寻找指定包下的所有类
     *
     * @param url 包的统一资源定位符
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInFile(URL url, String packageName, boolean recursive) {
        try {
            Path path = Paths.get(url.toURI());
            return getClassInFile(path, packageName, recursive);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的文件或文件夹中寻找指定包下的所有类
     *
     * @param path 包的路径
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInFile(Path path, String packageName, boolean recursive) {
        if (!Files.exists(path)) {
            return Collections.emptyList();
        }
        List<Class<?>> classList = new ArrayList<Class<?>>();
        if (Files.isDirectory(path)) {
            if (!recursive) {
                return Collections.emptyList();
            }
            try {
                //获取目录下的所有文件
                Stream<Path> stream = Files.list(path);
                Iterator<Path> iterator = stream.iterator();
                while (iterator.hasNext()) {
                    classList.addAll(getClassInFile(iterator.next(), packageName, recursive));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                //由于传入的文件可能是相对路径, 这里要拿到文件的实际路径, 如果不存在则报IOException
                path = path.toRealPath();
                String pathStr = path.toString();
                //这里拿到的一般的"aa:\bb\...\cc.class"格式的文件名, 要去除末尾的类型后缀(.class)
                int lastDotIndex = pathStr.lastIndexOf(DOT_CHAR);
                //Class.forName只允许使用用'.'分隔的类名的形式
                String className = pathStr.replace(File.separatorChar, DOT_CHAR);
                //获取包名的起始位置
                int beginIndex = className.indexOf(packageName);
                if (beginIndex == -1) {
                    return Collections.emptyList();
                }
                className = lastDotIndex == -1 ? className.substring(beginIndex) : className.substring(beginIndex, lastDotIndex);
                classList.add(Class.forName(className));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }

    /**
     * 在给定的jar包中寻找指定包下的所有类
     *
     * @param filePath 包的路径
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInJar(String filePath, String packageName, boolean recursive) {
        try {
            JarFile jar = new JarFile(filePath);
            return getClassInJar(jar, packageName, recursive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的jar包中寻找指定包下的所有类
     *
     * @param url jar包的统一资源定位符
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInJar(URL url, String packageName, boolean recursive) {
        try {
            JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
            return getClassInJar(jar, packageName, recursive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的jar包中寻找指定包下的所有类
     *
     * @param jar jar对象
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInJar(JarFile jar, String packageName, boolean recursive) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        //该迭代器会递归得到该jar底下所有的目录和文件
        Enumeration<JarEntry> iterator = jar.entries();
        while (iterator.hasMoreElements()) {
            //这里拿到的一般的"aa/bb/.../cc.class"格式的Entry或 "包路径"
            JarEntry jarEntry = iterator.nextElement();
            if (!jarEntry.isDirectory()) {
                String name = jarEntry.getName();
                //对于拿到的文件,要去除末尾的.class
                int lastDotClassIndex = name.lastIndexOf(".class");
                if(lastDotClassIndex != -1) {
                    int lastSlashIndex = name.lastIndexOf(SLASH_CHAR);
                    name = name.replace(SLASH_CHAR, DOT_CHAR);
                    if(name.startsWith(packageName)) {
                        if(recursive || packageName.length() == lastSlashIndex) {
                            String className = name.substring(0, lastDotClassIndex);
                            System.out.println(className);
                            try {
                                classList.add(Class.forName(className));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return classList;
    }
}