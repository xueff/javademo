package kafka.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 文件追加内容
 * @version 2011-3-10 下午10:49:41
 */
public class JSONFileUtils {


//    public static void main(String[] args)   {
//        List<Dto> dtos = readFile(new File("C:\\Users\\ffxue\\hbase.json"));
//        dtos.forEach(it->{
//            appendFile(it.getDbName()+","+it.getFamily()+","+it.getTableName()+","+it.getColumn()+","+it.getValues()+"\n","C:\\Users\\ffxue\\hbase.csv");
//        });
//
//    }
    public static void main(String[] args)   {
       new File("D:\\project\\idr-holmes-copy\\processor_java\\tmp\\decompress\\1598427205608\\新建文件夹").delete();


    }


    /**
     *
     * @param cfg
     * @return
     */
    public static  List<String> readFile(File cfg) {
        List<String> dtos = new ArrayList<String>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(cfg), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                try {
                    dtos.add(line);
                }catch (Exception e){
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            System.out.println("读取文件失败");
        }
        return dtos;
    }
}


