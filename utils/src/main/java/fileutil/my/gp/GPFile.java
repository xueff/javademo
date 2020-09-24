package fileutil.my.gp;

import cn.hutool.core.io.FileUtil;
import com.uwyn.jhighlight.tools.FileUtils;
import database.util.bean.TableColumnBeanConvertInSql;
import fileutil.ExcelUtils.ExcelUtil;
import fileutil.FindFile;
import io.vertx.core.json.JsonArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class GPFile {

    public static void main(String[] args) throws Exception {
        read();
    }
    public static void find(String[] args) throws Exception {
        List<File> list = FindFile.find("E:\\BaiduNetdiskDownload\\200g",100);
        List<FileDto> dtos = new ArrayList<>(list.size());
        Map<String,Long> all = new HashMap<>();
        Set<String> samesRemove = new HashSet<>();
        list.forEach(it->{
            try {
                if(it.isFile()) {
                    dtos.add(new FileDto(it.getParentFile().getCanonicalPath(), it.getName()));
                    if(all.containsKey(it.getName())){
                        if(!all.get(it.getName()).equals(it.length())) {
                            all.put(it.getName(), it.length());
                        }else {
                            samesRemove.add(it.getPath());
                        }
                    }else {
                        all.put(it.getName(), it.length());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });







        LinkedHashMap<String,String> head = new LinkedHashMap<>();
        head.put("path","路径");
        head.put("name","名称");
        head.put("size","大小");
        FileOutputStream out = new FileOutputStream("E:\\BaiduNetdiskDownload\\books.xlsx");
        ExcelUtil.listToExcel(dtos,head,"0",10000,out);
        System.out.println("samesRemove:"+samesRemove.size());
        samesRemove.forEach(it->{
            new File(it).delete();
        });
    }
    public static void read() throws Exception {
        List<String> list = FileUtil.readUtf8Lines("C:\\Users\\admin\\Desktop\\gp.txt");
        List<Object> dtos = new ArrayList<>(list.size());
        list.forEach(it->{
            if(it.contains(".gpv")) {
                String[] arr = it.split("\t");
                dtos.add(new FileDto(arr[0].trim(), arr[1].trim()));
            }
        });
        System.out.println(TableColumnBeanConvertInSql.insertBeans(dtos));

    }
}
