package javabase.file.path;

import java.io.File;

public class GetClassPath {
    /**
     * 获取classpath windows  linux
     * @return
     */
    public String getClassPath() {

        String os = System.getProperty("os.name");
        System.out.println(os );
        String classUrl = "";
        if(os.toLowerCase().startsWith("linux")){
            classUrl = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        }else if(os.toLowerCase().startsWith("win")){
            classUrl = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
        }else{
            classUrl = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        }
//        File file = new File(classUrl);
//        System.out.println(classUrl);
//        System.out.println(file.getAbsolutePath());
        return classUrl;
    }
}
