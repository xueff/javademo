package output.file;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/*
 * Created on: 2020/02/26.
 * Author:     xuefei
 */

/**
 * 处理excel读入的工具类
 */
public class ExcelUserModelUtils {


    public static void main(String[] args) throws Exception {
//        Workbook wb =  WorkbookFactory.create(new File("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xlsx"));
//        ExcelUtils.read2007PlusExcel(new File("C:\\Users\\admin\\Desktop\\ocyangjiatao\\bb - 副本.xlsx"));
        ExcelUtils.read2003Excel(new File("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xls"));
    }

    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public static void read2007PlusExcel(File file) throws Exception {
        if(!file.exists()){
            throw new Exception("找不到文件");
        }
        XSSFWorkbook xwb = new XSSFWorkbook(file);
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;

        int rowWaterMask = 0;


        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            if(i==11 && rowWaterMask>1){
                break;
            }
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if(row.getLastCellNum()>rowWaterMask){
                rowWaterMask = row.getLastCellNum();
            }

        }
        rowWaterMask+=20;


        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();

            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                row.createCell(rowWaterMask).setCellValue("11111");
            }

        }
        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\aa.xlsx");
        xwb.write(out);
    }
    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public static void read2003Excel(File file) throws Exception {
        if(!file.exists()){
            throw new Exception("找不到文件");
        }
        HSSFWorkbook wb = null;
        try {

            wb = new HSSFWorkbook(new FileInputStream(file));

        } catch (IOException e) {

            e.printStackTrace();
        }
        // 读取第一张表格内容
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;

        int rowWaterMask = 0;


        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            if(i==11 && rowWaterMask>1){
                break;
            }
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if(row.getLastCellNum()>rowWaterMask){
                rowWaterMask = row.getLastCellNum();
            }

        }
        rowWaterMask+=20;


        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();

            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                row.createCell(rowWaterMask).setCellValue("11111");
            }

        }
        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\watermark\\ocyangjiatao\\aa.xls");
        wb.write(out);
    }

//    /**
//     * 导出excel
//     * @param excel_name 导出的excel路径（需要带.xlsx)
//     * @param headList  excel的标题备注名称
//     * @param fieldList excel的标题字段（与数据中map中键值对应）
//     * @param dataList  excel数据
//     * @throws Exception
//     */
//    public static void createExcel(String excel_name, String[] headList,
//                                   String[] fieldList, List<Map<String, Object>> dataList)
//            throws Exception {
//        // 创建新的Excel 工作簿
//        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(""));
//        SXSSFWorkbook workbook = new SXSSFWorkbook(wb,100);
//        Sheet sh = workbook.createSheet();
//        //使用createRow将信息写在内存中。
//        for(int rownum = 0; rownum < 1000; rownum++){
//            Row row = sh.createRow(rownum);
//            for(int cellnum = 0; cellnum < 10; cellnum++){
//                Cell cell = row.createCell(cellnum);
//                String address = new CellReference(cell).formatAsString();
//                cell.setCellValue(address);
//            }
//
//        }
//
//        // Rows with rownum < 900 are flushed and not accessible
//        //当使用getRow方法访问的时候，将内存中的信息刷新到硬盘中去。
//        for(int rownum = 0; rownum < 900; rownum++){
//            System.out.println(sh.getRow(rownum));
//        }
//
//        // ther last 100 rows are still in memory
//        for(int rownum = 900; rownum < 1000; rownum++){
//            System.out.println(sh.getRow(rownum));
//        }
//        //写入文件中
//        FileOutputStream out = new FileOutputStream("C://sxssf.xlsx");
//        wb.write(out);
//        //关闭文件流对象
//        out.close();
//        System.out.println("基于流写入执行完毕!");
//
//
//
//
//
//
//        // 在Excel工作簿中建一工作表，其名为缺省值
//        XSSFSheet sheet = workbook.createSheet();
//        // 在索引0的位置创建行（最顶端的行）
//        XSSFRow row = sheet.createRow(0);
//        // 设置excel头（第一行）的头名称
//        for (int i = 0; i < headList.length; i++) {
//
//            // 在索引0的位置创建单元格（左上端）
//            XSSFCell cell = row.createCell(i);
//            // 定义单元格为字符串类型
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            // 在单元格中输入一些内容
//            cell.setCellValue(headList[i]);
//        }
//        // ===============================================================
//        //添加数据
//        for (int n = 0; n < dataList.size(); n++) {
//            // 在索引1的位置创建行（最顶端的行）
//            XSSFRow row_value = sheet.createRow(n + 1);
//            Map<String, Object> dataMap = dataList.get(n);
//            // ===============================================================
//            for (int i = 0; i < fieldList.length; i++) {
//
//                // 在索引0的位置创建单元格（左上端）
//                XSSFCell cell = row_value.createCell(i);
//                // 定义单元格为字符串类型
//                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                // 在单元格中输入一些内容
//                cell.setCellValue((dataMap.get(fieldList[i])).toString());
//            }
//            // ===============================================================
//        }
//        // 新建一输出文件流
//        FileOutputStream fos = new FileOutputStream(excel_name);
//        // 把相应的Excel 工作簿存盘
//        workbook.write(fos);
//        fos.flush();
//        // 操作结束，关闭文件
//        fos.close();
//    }
}

