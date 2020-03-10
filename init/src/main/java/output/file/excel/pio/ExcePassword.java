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

        new ExcePassword().setPasswd("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.xls","222");
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

        System.out.println("begin");


        if (excelPath.endsWith("xls")) {
            InputStream inp = new FileInputStream(excelPath);
            File file = new File(excelPath);
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey
                    .setCurrentUserPassword(password);
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(inp);
            hssfworkbook.writeProtectWorkbook(password, "lili");
            inp.close();
            FileOutputStream fileOut = new FileOutputStream(new File(excelPath));
            hssfworkbook.write(fileOut);
            fileOut.close();


//            POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(excelPath));
//            EncryptionInfo encInfo = new EncryptionInfo(pfs);
//            Decryptor decryptor = Decryptor.getInstance(encInfo);
//            decryptor.verifyPassword(password);
//            HSSFWorkbook workbook = new HSSFWorkbook(decryptor.getDataStream(pfs));
//            FileOutputStream fileOut = new FileOutputStream(new File(excelPath));
//            workbook.write(fileOut);
//            fileOut.close();
        } else {
//            POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(excelPath));
//            EncryptionInfo encInfo = new EncryptionInfo(pfs);
//            Decryptor decryptor = Decryptor.getInstance(encInfo);
//            decryptor.verifyPassword(password);
//            XSSFWorkbook workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
//            FileOutputStream fileOut = new FileOutputStream(new File(excelPath));
//            workbook.write(fileOut);
//            fileOut.close();
        }
        System.out.println("end");
    }



}
