package output.file.excel;


import com.idss.processor.watermarkengine.WaterKey;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created on: 2020/02/26.
 * Author:     xuefei
 */
public class ExcelTraceUtils {
    private static Logger log = LoggerFactory.getLogger(ExcelTraceUtils.class);
    public static void main(String[] args) {

        new ExcelTraceUtils().traceExcel("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\aa.xlsx");
//        System.out.println(new ExcelMaskUtils2().traceExcel("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\bb.xlsx"));
    }


    public Map<String,Object> traceExcel(String path) {
        Map<String,Object> traceMap = new HashMap<>();
        try {
            File file = new File(path);
            if(!file.exists()){
                log.error("找不到文件:");
                throw new Exception("找不到文件");
            }


            List<Object> lists = EasyExcelUtil.readMoreThan1000Row(path);
            if(lists != null && lists.size()>0){
                Ok:
                for(int i=0;i< lists.size()-1;i++){
                    Object o = lists.get(i);
                    if(null == o){
                        continue ;
                    }
                    if(o instanceof  List){
                        ArrayList<String> list = (ArrayList<String>)o;
                        for (int j = 0; j<list.size(); j++) {
                            String value = list.get(j);
                            if (StringUtils.isEmpty(value)) {
                                continue;
                            } else if (isMaskValue(value)) {
                                traceMap.put("maskList",new ArrayList<>());
                                getChineseList(lists,list,i,j,traceMap);
                                break;
                            }
                        }

                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return  traceMap;
    }





    /**
     * 获取excel cell的值
     */
    public String getValue(Cell c) {
        if(c == null){
            return null;
        }
        try {
            if(CellType.NUMERIC.equals(c.getCellTypeEnum())) {
                return c.getNumericCellValue()+"";
            }else{
                return c.getStringCellValue();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }
    /**
     * 是否是水印字符
     */
    public boolean isMaskValue(String value) {
        if(StringUtils.isEmpty(value) || value.length()!=8){
            return false;
        }

        for (int i = 0; i<value.length(); i++) {
            String it = Integer.toString((int) value.charAt(i));
            if(!WaterKey.indexMap2.containsKey(it)){
                return false;
            }
        }
        return true;
    }


    public boolean isEncryptChineseWaterKey(String value) {
        if(StringUtils.isEmpty(value) || value.length()%8!=0){
            return false;
        }

        char first = (char)WaterKey.hideChar[0];
        char second = (char)WaterKey.hideChar[1];
        for (int i = 0; i<value.length(); i++) {
            char it = value.charAt(i);
            if(it != first && it !=second){
                return false;
            }
        }
        return true;
    }
    /**
     * 获取excel水印列表
     */

    private void getChineseList(List<Object> lists,List<String> list, int row, int column, Map<String, Object> traceMap) {

        boolean findaterKey = isChineseWaterKey(list, column+1, traceMap);
        List<String> maskList = (List<String>)traceMap.get("maskList");
        if(lists != null && lists.size()>0){
            Ok:
            for(int i=0;i< lists.size();i++){

                if(i == 80){
                    if(lists.size()>160) {
                        i = lists.size() - 160;
                    }
                }

                Object o = lists.get(i);
                if(null == o){
                    continue ;
                }
                if(o instanceof  List){
                    list = (ArrayList<String>)o;
                    int min = column-10>0?column-10:0;
                    int max = list.size()>column+10?column+10:list.size();
                    for (int j =min; j<max; j++) {
                        String value = list.get(j);
                        if (StringUtils.isEmpty(value)) {
                            continue;
                        } else if (isMaskValue(value)) {
                            maskList.add(value);
                            if(!findaterKey){
                                findaterKey = isChineseWaterKey(list,column+1, traceMap);
                            }
                            break;
                        }
                    }
                }
            }
        }


//
//        List<String> list = (List<String>)traceMap.get("maskList");
//        boolean findaterKey = isChineseWaterKey(readerModel, row, column+1, traceMap);
//        int continueRow = 30;
//        row+=1;
//
//        ok:
//        for (int i = row+1; ; i++) {
//            String value = getValue(readerModel.getCell(column,i));
//            if(isMaskValue(value)){
//                list.add(value);
//                if(!findaterKey){
//                    findaterKey = isChineseWaterKey(readerModel, i, column+1, traceMap);
//                }
//            }else{
//                if(continueRow<=0){
//                    break ok;
//                }
//                for (int j = column-10; j<=column+10; j++) {
//                    value = getValue(readerModel.getCell(column,i));
//                    if(isMaskValue(value)){
//                        list.add(value);
//                        if(!findaterKey){
//                            findaterKey = isChineseWaterKey(readerModel, i, column+1, traceMap);
//                        }
//                        continueRow = 30;
//                        continue ok;
//                    }else{
//                        continue ;
//                    }
//                }
//                continueRow -= continueRow;
//            }
//        }
    }
    /**
     * 水印密钥
     */
    private boolean isChineseWaterKey(List<String> list, int column,Map<String, Object> traceMap) {

        String EncryptChineseWaterKey = list.get(column);
        if(isEncryptChineseWaterKey(EncryptChineseWaterKey)){
            WaterKey.getEncryptChineseToWaterKey(EncryptChineseWaterKey);
            traceMap.put("waterKey", WaterKey.getEncryptChineseToWaterKey(EncryptChineseWaterKey));
            return true;
        }
        return false;
    }

//
//    public void  throws Throwable {
//        //创建基于stream的工作薄对象的
//        Workbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
//        //SXSSFWorkbook wb = new SXSSFWorkbook();
//        //wb.setCompressTempFiles(true); // temp files will be gzipped
//
//    }

}
