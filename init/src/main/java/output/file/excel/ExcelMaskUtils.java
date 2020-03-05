package output.file.excel;


import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created on: 2020/02/26.
 * Author:     xuefei
 */
public class ExcelMaskUtils {

    public static void main(String[] args) {
        System.out.println(new ExcelMaskUtils().maskExcel("C:\\Users\\admin\\Desktop\\ocyangjiatao\\bb.xlsx","C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\bb.xlsx","11111111000000011101010101010101"));
        System.out.println(new ExcelMaskUtils().traceExcel("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\bb.xlsx"));
    }

    public boolean maskExcel(String path,String fileOutPath,String waterKey) {
        try {
            long startTime = System.currentTimeMillis();
            File file = FileUtil.file(fileOutPath);
//            if(file.exists()){
//                file.delete();
//            }
            if(!System.getProperty("os.name").toLowerCase().startsWith("win")) {
                Runtime.getRuntime().exec(" cp -f "+path +" "+fileOutPath);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("copy:"+(endTime-startTime));
            if(FileUtil.file(fileOutPath).exists()){
                ExcelWriter excelWriter = ExcelUtil.getWriter(FileUtil.file(fileOutPath));
                int rowMax = 1;
                int columnMax = 1;
                for (int i = 1; ; i++) {
                    if (getValue(excelWriter.getCell(i, 1)) == null) {
                        columnMax = i;
                        break;
                    }
                }
                startTime = System.currentTimeMillis();
                System.out.println("列:"+(startTime-endTime));
                endTime = System.currentTimeMillis();
                for (int i = 1; ; i++) {
                    if (getValue(excelWriter.getCell(1, i)) == null) {
                        rowMax = i;
                        break;
                    }
                }
                System.out.println("行:"+(endTime-startTime));

                List<String> ChineseList = new WaterKey().getEncryptChineseList(waterKey);
                String encryptChinese = new WaterKey().getWaterKeyToEncryptChinese(waterKey);
                System.out.println(WaterKey.getEncryptChineseToWaterKey(encryptChinese));
                setWaterValueAndWrite(excelWriter, rowMax, columnMax + 20, ChineseList, encryptChinese,fileOutPath);
            }else {
                ExcelReader excelReader = ExcelUtil.getReader(FileUtil.file(path),0);
                int rowMax = 1;
                int columnMax = 1;
                for (int i = 1; ; i++) {
                    if (getValue(excelReader.getCell(i, 1)) == null) {
                        columnMax = i;
                        break;
                    }
                }
                startTime = System.currentTimeMillis();
                System.out.println("列:"+(startTime-endTime));
                endTime = System.currentTimeMillis();
                for (int i = 1; ; i++) {
                    if (getValue(excelReader.getCell(1, i)) == null) {
                        rowMax = i;
                        break;
                    }
                }
                System.out.println("行:"+(endTime-startTime));
                List<String> ChineseList = new WaterKey().getEncryptChineseList(waterKey);
                String encryptChinese = new WaterKey().getWaterKeyToEncryptChinese(waterKey);
                System.out.println(WaterKey.getEncryptChineseToWaterKey(encryptChinese));
                setWaterValueAndWrite(excelReader.getWriter(), rowMax, columnMax + 20, ChineseList, encryptChinese,fileOutPath);
            }





        }catch (Exception e){
            return  false;
        }
        return true;
    }

    public Map<String,Object> traceExcel(String path) {
        Map<String,Object> traceMap = new HashMap<>();
        //模板处理 sheetIndex=0代表第一个
        ExcelReader readerModel = ExcelUtil.getReader(FileUtil.file(path),0);
        int rowMax = 1;
        int columnMax = 1;
        List<String> chineseList = new ArrayList<>();
        Ok:
        for(int i=rowMax;i<500;i++){
            for(int j=columnMax;j<(columnMax+100);j++) {
                String value = getValue(readerModel.getCell(j, i));
                if (StringUtils.isEmpty(value)) {
                    continue;
                } else if (isMaskValue(value)) {
                    chineseList.add(value);
                    traceMap.put("maskList",chineseList);
                    getChineseList(readerModel,i,j,traceMap);
                    break Ok;
                } else {
                    columnMax = j+1;
                }
            }
        }
        return traceMap;
    }


    /**
     * 向excel写入水印和密钥水印
     */
    public void setWaterValueAndWrite(ExcelWriter excelWriter, int row, int column, List<String> list, String encryptChinese, String fileOutPath) {
        int rowLength = (row<100?100:row);
        int rowStart = 1;
        for(int i=0;i<rowLength;i++){
            for(int j=0;j<list.size();j++){
                excelWriter.getOrCreateCell(column,rowStart).setCellValue(list.get(j));
                excelWriter.getOrCreateCell(column+1,rowStart).setCellValue(encryptChinese);
                rowStart+=1;
            }
        }
        excelWriter.flush(FileUtil.file(fileOutPath));

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

    private void getChineseList(ExcelReader readerModel, int row, int column, Map<String, Object> traceMap) {
        List<String> list = (List<String>)traceMap.get("maskList");
        boolean findaterKey = isChineseWaterKey(readerModel, row, column+1, traceMap);
        int continueRow = 30;
        row+=1;

        ok:
        for (int i = row+1; ; i++) {
            String value = getValue(readerModel.getCell(column,i));
            if(isMaskValue(value)){
                list.add(value);
                if(!findaterKey){
                    findaterKey = isChineseWaterKey(readerModel, i, column+1, traceMap);
                }
            }else{
                if(continueRow<=0){
                    break ok;
                }
                for (int j = column-10; j<=column+10; j++) {
                    value = getValue(readerModel.getCell(column,i));
                    if(isMaskValue(value)){
                        list.add(value);
                        if(!findaterKey){
                            findaterKey = isChineseWaterKey(readerModel, i, column+1, traceMap);
                        }
                        continueRow = 30;
                        continue ok;
                    }else{
                        continue ;
                    }
                }
                continueRow -= continueRow;
            }
        }
    }
    /**
     * 水印密钥
     */
    private boolean isChineseWaterKey(ExcelReader readerModel, int row, int column, Map<String, Object> traceMap) {

        String EncryptChineseWaterKey = getValue(readerModel.getCell(column,row));
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
