package output.file.excel.pio;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcePassword {
    private static Logger log = LoggerFactory.getLogger(ExcePassword.class);
    public static void main(String[] args) throws Exception {

        new ExcePassword().setPasswd("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xls","111222");
    }

    public String openXlsx(String path,String pwd) throws IOException {
        String excelPath = "Excel文件路徑";
        String password = "Excel文件密碼";

        Workbook workbook;
        InputStream inp = new FileInputStream(excelPath);
//解密
        POIFSFileSystem pfs = null;
        try {
            pfs = new POIFSFileSystem(inp);
            inp.close();
            EncryptionInfo encInfo = new EncryptionInfo(pfs);
            Decryptor decryptor = Decryptor.getInstance(encInfo);
            decryptor.verifyPassword(password);
            workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String setPasswdInSheet(String path,String pwd) throws IOException {

        if(path.endsWith("xls")) {
            HSSFWorkbook hssfworkbook = null;
            try {

                hssfworkbook = new HSSFWorkbook(new FileInputStream(new File(path)));

            } catch (IOException e) {

                e.printStackTrace();
                throw new RuntimeException("文件读取异常");
            }
            HSSFSheet sheet1 = hssfworkbook.getSheet("Sheet1");

            sheet1.protectSheet(pwd);
            FileOutputStream out = new FileOutputStream(path);
            hssfworkbook.write(out);
            hssfworkbook.close();
        }else{

        }
        return "";
    }

    public void setPasswd(String excelPath,String password) throws IOException, InvalidFormatException, GeneralSecurityException {
//        if (path.endsWith("xls")) {
//            FileOutputStream fileOut = null;
//            try {
//                File file = new File(path);
//                // 创 建一个工作薄
//                HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
//                // 设置密 码 保 护 ·
//                wb.writeProtectWorkbook("password", pwd);
//                // 写入excel文件
//                fileOut = new FileOutputStream(file);
//                wb.write(fileOut);
//                fileOut.close();
//            } catch (IOException io) {
//                io.printStackTrace();
//            } finally {
//                if (fileOut != null) {
//                    try {
//                        fileOut.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }


        System.out.println("begin");

        String prefix = excelPath.substring(excelPath.lastIndexOf(".") + 1);

        Workbook workbook;

        InputStream inp = new FileInputStream(excelPath);

        if (excelPath.endsWith("xls")) {
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey
                    .setCurrentUserPassword(password);
            workbook = WorkbookFactory.create(inp);
            inp.close();
        } else {
            POIFSFileSystem pfs = new POIFSFileSystem(inp);
            inp.close();
            EncryptionInfo encInfo = new EncryptionInfo(pfs);
            Decryptor decryptor = Decryptor.getInstance(encInfo);
            decryptor.verifyPassword(password);
            workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
        }

        Sheet sheet = workbook.getSheetAt(0);
        int startRowNum = sheet.getFirstRowNum();
        int endRowNum = sheet.getLastRowNum();
        for (int rowNum = startRowNum; rowNum <= endRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null)
                continue;
            int startCellNum = row.getFirstCellNum();
            int endCellNum = row.getLastCellNum();
            for (int cellNum = startCellNum; cellNum < endCellNum; cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) {
                    continue;
                }
                int type = cell.getCellType();
                switch (type) {
                    case Cell.CELL_TYPE_NUMERIC:// 数值、日期类型
                        double d = cell.getNumericCellValue();
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 日期类型
                            Date date = cell.getDateCellValue();
                            System.out.print(" "
                                    + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(date) + " ");
                        } else {// 数值类型
                            System.out.print(" " + d + " ");
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK:// 空白单元格
                        System.out.print(" null ");
                        break;
                    case Cell.CELL_TYPE_STRING:// 字符类型
                        System.out.print(" " + cell.getStringCellValue() + " ");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:// 布尔类型
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        System.err.println("非法字符");// 非法字符;
                        break;
                    default:
                        System.err.println("error");// 未知类型
                        break;
                }
            }
            System.out.println();
        }
        FileOutputStream fileOut = new FileOutputStream(new File(excelPath));
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("end");
    }



}
