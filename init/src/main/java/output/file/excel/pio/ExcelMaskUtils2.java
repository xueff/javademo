package output.file.excel.pio;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ExcelMaskUtils2 {
    private static Logger log = LoggerFactory.getLogger(ExcelMaskUtils2.class);
    public static void main(String[] args) {

        new ExcelMaskUtils2().maskExcel("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xlsx","C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\aa.xlsx","11111111000000011101010101010101");
//        System.out.println(new ExcelMaskUtils2().traceExcel("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\bb.xlsx"));
    }

    public boolean maskExcel(String path,String fileOutPath,String waterKey) {

        try {

            File file = new File(path);
            if(!file.exists()){
                log.error("找不到文件");
                throw new RuntimeException("找不到文件");
            }

            if(fileOutPath.endsWith(".xlsx")){
                mask2007PlusExcel(file,fileOutPath);
            }else{
                mask2003Excel(new File(fileOutPath),fileOutPath);
            }

            if(new File(fileOutPath).exists()){
                if(!System.getProperty("os.name").toLowerCase().startsWith("win")) {
                    Runtime.getRuntime().exec("chmod -R 777 " + fileOutPath);
                }
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }


    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public void mask2007PlusExcel(File file,String fileOutPath)  {
        XSSFWorkbook xwb = null;
        try {
            xwb = new XSSFWorkbook(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件读取异常");
        }
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        int cellWaterMask = 5;  //非数据区
        for (int i = 1; i <= 5; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            for (int j = cellWaterMask ; j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cellWaterMask = j+1;
                    continue;
                }
                if(StringUtils.isEmpty(cell.getRawValue()) && j>cellWaterMask){
                    cellWaterMask = j;
                    continue;
                }

            }
        }
        cellWaterMask+=10;
        for (int i = sheet.getFirstRowNum() ; i < 80; i++) {
            row = sheet.getRow(i);
            if(row ==null){
                row = sheet.createRow(i);
            }
            row.createCell(cellWaterMask).setCellValue("1");
            row.createCell(cellWaterMask+1).setCellValue(1);

        }
        int maxRow = sheet.getLastRowNum();
        log.info("set waterkey");
        for (int i = maxRow ; i <= maxRow + 80; i++) {
            row = sheet.getRow(i);
            if(row ==null){
                row = sheet.createRow(i);
            }
            row.createCell(cellWaterMask).setCellValue(1);
            row.createCell(cellWaterMask+1).setCellValue(1);

        }

        try {
            log.info("flush file");
            FileOutputStream out = new FileOutputStream(fileOutPath);
            xwb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("另一个程序正在使用此文件");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件写入失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                xwb.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }

    }
    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public void mask2003Excel(File file,String fileOutPath) {

        HSSFWorkbook wb = null;
        try {

            wb = new HSSFWorkbook(new FileInputStream(file));

        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException("文件读取异常");
        }
        // 读取第一张表格内容
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;

        // 读取第一张表格内容
        int cellWaterMask = 5;  //非数据区
        for (int i = 1; i <= 5; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            for (int j = cellWaterMask ; j < row.getLastCellNum(); j++) {
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cellWaterMask = j+1;
                    continue;
                }
                if(StringUtils.isEmpty(cell.toString()) && j>cellWaterMask){
                    cellWaterMask = j;
                    continue;
                }

            }
        }
        cellWaterMask+=10;
        for (int i = sheet.getFirstRowNum() ; i < 80; i++) {
            row = sheet.getRow(i);
            if(row ==null){
                row = sheet.createRow(i);
            }
            row.createCell(cellWaterMask).setCellValue(1);
            row.createCell(cellWaterMask+1).setCellValue(1);

        }
        int maxRow = sheet.getLastRowNum();
        log.info("set waterkey");
        for (int i = maxRow ; i <= maxRow + 80; i++) {
            row = sheet.getRow(i);
            if(row ==null){
                row = sheet.createRow(i);
            }
            row.createCell(cellWaterMask).setCellValue(1);
            row.createCell(cellWaterMask+1).setCellValue(1);

        }

        try {
            log.info("flush file");
            FileOutputStream out = new FileOutputStream(fileOutPath);
            wb.write(out);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件写入失败");
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
