package fileutil;



import fileutil.ExcelUtils.ExcelUtil;
import fileutil.dto.FileDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 文件整理
 */
public class FileArrangement {


    public static void main(String[] args) throws Exception {
        List<File> list = FindFile.find("E:\\BaiduNetdiskDownload\\200g",100);
        List<FileDto> dtos = new ArrayList<>(list.size());
        Map<String,Long> all = new HashMap<>();
        Set<String> samesRemove = new HashSet<>();
        list.forEach(it->{
            try {
                if(it.isFile()) {
                    dtos.add(new FileDto(it.getParentFile().getCanonicalPath(), it.getName(),it.length()));
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
}
