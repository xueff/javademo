package output.file.excel;//package com.idss.processor.utils;
//
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.poi.excel.ExcelReader;
//import cn.hutool.poi.excel.ExcelUtil;
//import cn.hutool.poi.excel.ExcelWriter;
//import com.idss.processor.watermarkengine.WaterKey;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellReference;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.*;
//
///*
// * Created on: 2020/02/26.
// * Author:     xuefei
// */
///**
// * 处理excel读入的工具类
// */
//public class ExcelUtils {
//
//    public static void main(String[] args) throws Exception {
////        Workbook wb =  WorkbookFactory.create(new File("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xlsx"));
////        ExcelUtils.read2007PlusExcel(new File("C:\\Users\\admin\\Desktop\\ocyangjiatao\\bb - 副本.xlsx"));
//        ExcelUtils.getWorkBook(new File("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xls"));
//    }
//
//    /**
//     *
//     * @return
//     * @throws Exception
//     */
//    public boolean getWorkBook(String path) {
//        File file = new File(path);
//        try {
//
//            if(path.endsWith("\\.xlsx")){
//                return mask2007PlusExcel(file);
//            }else{
//                return  mask2003Excel(file);
//            }
//        }catch (Exception e){
//            return false;
//        }
//
//    }
//    /**
//     * 要求excel版本在2007以上
//     *
//     * @param file 文件信息
//     * @return
//     * @throws Exception
//     */
//    public boolean mask2007PlusExcel(File file) throws Exception {
//        XSSFWorkbook xwb = new XSSFWorkbook(file);
//        // 读取第一张表格内容
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        XSSFRow row = null;
//        XSSFCell cell = null;
//
//        int rowWaterMask = 0;
//
//
//        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
//            if(i==11 && rowWaterMask>1){
//                break;
//            }
//            row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            if(row.getLastCellNum()>rowWaterMask){
//                rowWaterMask = row.getLastCellNum();
//            }
//
//        }
//        rowWaterMask+=20;
//
//
//        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
//            row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            List<Object> linked = new LinkedList<Object>();
//
//            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
//                row.createCell(rowWaterMask).setCellValue("11111");
//            }
//
//        }
//        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\aa.xlsx");
//        xwb.write(out);
//    }
// /**
//     * 要求excel版本在2007以上
//     *
//     * @param file 文件信息
//     * @return
//     * @throws Exception
//     */
//    public boolean mask2003Excel(File file) throws Exception {
//        if(!file.exists()){
//            throw new Exception("找不到文件");
//        }
//        HSSFWorkbook wb = null;
//        try {
//
//            wb = new HSSFWorkbook(new FileInputStream(file));
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//        // 读取第一张表格内容
//        HSSFSheet sheet = wb.getSheetAt(0);
//        HSSFRow row = null;
//
//        int rowWaterMask = 0;
//
//
//        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
//            if(i==11 && rowWaterMask>1){
//                break;
//            }
//            row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            if(row.getLastCellNum()>rowWaterMask){
//                rowWaterMask = row.getLastCellNum();
//            }
//
//        }
//        rowWaterMask+=20;
//
//
//        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
//            row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            List<Object> linked = new LinkedList<Object>();
//
//            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
//                row.createCell(rowWaterMask).setCellValue("11111");
//            }
//
//        }
//        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\aa.xls");
//        wb.write(out);
//    }
//}
//
