package output.file.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件 目录  处理
 * 文件查找
 * 文件
 */
public class FindFile {

    private static int depth=1;

    /**
     * 文件查找   文件遍历 文件  文件夹
     * @param pathName
     * @param depth
     * @return
     * @throws IOException
     */
    public static List<File> find(String pathName,int depth) throws IOException {
        List<File> list = new ArrayList<>();
        int filecount=0;
        //获取pathName的File对象
        File dirFile = new File(pathName);
        //判断该文件或目录是否存在，不存在时在控制台输出提醒
        if (!dirFile.exists()) {
            System.out.println("do not exit");
            return list;
        }
        //判断如果不是一个目录，就判断是不是一个文件，时文件则输出文件路径
        if (!dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                System.out.println(dirFile.getCanonicalFile());
                list.add(dirFile.getCanonicalFile());
            }
            return list;
        }
//
//        for (int j = 0; j < depth; j++) {
//            System.out.print("  ");
//        }
//        System.out.print("|--");
//        System.out.println(dirFile.getName());
        //获取此目录下的所有文件名与目录名
        String[] fileList = dirFile.list();
        int currentDepth=depth+1;
        for (int i = 0; i < fileList.length; i++) {
            //遍历文件目录
            String string = fileList[i];
            //File("documentName","fileName")是File的另一个构造器
            File file = new File(dirFile.getPath(),string);
            list.add(file);
            String name = file.getName();
            //如果是一个目录，搜索深度depth++，输出目录名后，进行递归
            if (file.isDirectory()) {
                //递归
                list.addAll(find(file.getCanonicalPath(),currentDepth));
            }else{
                //如果是文件，则直接输出文件名
//                for (int j = 0; j < currentDepth; j++) {
//                    System.out.print("   ");
//                }
//                System.out.print("|--");
//                System.out.println(name);

            }
        }

        return list;
    }


    public static void main(String[] args) throws IOException{

        List<File> list = find("D:\\XF\\apache-maven-3.6.3", 3);
        System.out.println(list);
    }
}
