//
//package output.file.outputxls;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFRichTextString;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//
//import com.dayee.wintalent.constant.Constants;
//import com.dayee.wintalent.framework.export.CellStyleFactory;
//import com.dayee.wintalent.framework.export.WorkbookFactory;
//import com.dayee.wintalent.framework.utils.CollectionUtils;
//import com.dayee.wintalent.framework.utils.DataTypeUtils;
//import com.dayee.wintalent.framework.utils.StringUtils;
//
//public class StatisticsPositionInfo {
//
//    private static Connection consoleConn;
//
//    private static Connection testConn;
//
//    private static Connection corpConn;
//
//    public static Map<Integer, JobParameters> getChannelInfoMap()
//            throws SQLException {
//
//        Map<Integer, JobParameters> map = new LinkedHashMap<Integer, JobParameters>();
//        String sql = "select d.f_postname51job, d.F_WORKCONTENT ,d.F_SERVICECONDITION from t_export_post_data d ";
//        //   51   职位类型 岗位职责  要求  @#@
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            stat = consoleConn.prepareStatement(sql);
//            rs = stat.executeQuery();
//            int i = 0;
//            while (rs.next()) {
//                JobParameters job = new JobParameters();
//                String postTypes = rs.getString("f_postname51job")+"";
//                String workContent = rs.getString("F_WORKCONTENT")+"";
//                String serviceCondition = rs.getString("F_SERVICECONDITION")+"";
//                if(!StringUtils.isEmpty(postTypes)){
//                    String[] postids = postTypes.split("@#@");
//                    for (String postType : postids) {
//                        job.setPostType(postType);
//                        if(!StringUtils.isEmpty(workContent)){
//                            job.setWorkContent(workContent);
//                        }
//                        if(!StringUtils.isEmpty(serviceCondition)){
//                            job.setServiceCondition(serviceCondition);
//                        }
//                        i++;
//                    }
//                }else{
//                    job.setPostType(postTypes);
//                    if(!StringUtils.isEmpty(workContent)){
//                        job.setWorkContent(workContent);
//                    }
//                    if(!StringUtils.isEmpty(serviceCondition)){
//                        job.setServiceCondition(serviceCondition);
//                    }
//                    i++;
//                }
//                map.put(i, job);
//            }
//            
//            System.out.println("数据行数："+(i+1));
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stat != null) {
//                stat.close();
//            }
//        }
//        return map;
//    }
//
//    public static void main(String[] args) throws Exception {
//
////        testConn = getConnection("test");
////        corpConn = getConnection("ygbx8");
//        consoleConn = getConnection("wtconsole62");
//
////        Map<String, String> dicMap = getDicInfoMap();
//        Map<Integer, JobParameters> jobMap = getChannelInfoMap();
//      
////        Map<String, String> postTypeMap = getChannelInfoMap("F_CODE", "2/3658");
////        Map<String, String> workyearMap = getChannelInfoMap("F_STANDARD_CODE",
////                                                            "2/110988");
////        Map<String, String> salarMap = getChannelInfoMap("F_STANDARD_CODE",
////                                                         "2/107514");
////        System.out.println(tagMap);
////        System.out.println(postTypeMap);
//
//        WorkbookFactory workbook = new WorkbookFactory();
//        HSSFSheet sheet = workbook.getWorkbook().createSheet("Wintalent");
//        int rownum = 0;
//
//        HSSFRow row = sheet.createRow(rownum++);
//        addCellValue(workbook, row, 0, "职位类别",
//                     workbook.getCellStyle(CellStyleFactory.TITILE0));
//        addCellValue(workbook, row, 1, "任职要求",
//                     workbook.getCellStyle(CellStyleFactory.TITILE0));
//        addCellValue(workbook, row, 2, "岗位职责",
//                     workbook.getCellStyle(CellStyleFactory.TITILE0));
//        try {
//            for (Integer jobtype : jobMap.keySet()) {
//                row = sheet.createRow(rownum++);
//                addCellValue(workbook,
//                             row,
//                             0,
//                             jobMap.get(jobtype).getPostType(),
//                             workbook.getCellStyle(CellStyleFactory.TITILE0));
//                addCellValue(workbook,
//                             row,
//                             1,
//                             jobMap.get(jobtype).getServiceCondition(),
//                             workbook.getCellStyle(CellStyleFactory.TITILE0));
//                addCellValue(workbook,
//                             row,
//                             2,
//                             jobMap.get(jobtype).getWorkContent(),
//                             workbook.getCellStyle(CellStyleFactory.TITILE0));
//            }
//
//
//            OutputStream out = new FileOutputStream(new File(
//                    "D:\\职位信息.xls"));
//            workbook.getWorkbook().write(out);
//            out.close();
//            System.out.println("==end");
//        } finally {
//        }
//    }
//
//    protected static void addCellValue(WorkbookFactory workbook,
//                                       HSSFRow row,
//                                       int col,
//                                       Object value,
//                                       HSSFCellStyle style) throws Exception {
//
//        HSSFCell cell = row.createCell((col));
//
//        if (value == null) {
//            cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
//            if (style != null) {
//                cell.setCellStyle(style);
//            } else {
//                cell.setCellStyle(workbook
//                        .getCellStyle(CellStyleFactory.STYLE0));
//            }
//        } else {
//
//            // 类型转换后增加Cell
//            if (DataTypeUtils.isDouble(value)) {
//
//                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//                if (style != null) {
//                    cell.setCellStyle(style);
//                } else {
//                    cell.setCellStyle(workbook
//                            .getCellStyle(CellStyleFactory.NUMERIC0));
//                }
//                cell.setCellValue((Double) value);
//
//            } else if (DataTypeUtils.isInteger(value)) {
//
//                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//                if (style != null) {
//                    cell.setCellStyle(style);
//                } else {
//                    cell.setCellStyle(workbook
//                            .getCellStyle(CellStyleFactory.NUMERIC0));
//                }
//                cell.setCellValue((Integer) value);
//
//            } else {
//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//                if (style != null) {
//                    cell.setCellStyle(style);
//                } else {
//                    cell.setCellStyle(workbook
//                            .getCellStyle(CellStyleFactory.STRING0));
//                }
//                HSSFRichTextString str = new HSSFRichTextString(
//                        String.valueOf(value));
//                cell.setCellValue(str);
//
//            }
//        }
//
//    }
//
//    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
//
//    public static final String MYSQL_DRIVER  = "com.mysql.jdbc.Driver";
//
//    public static Connection getConnection(String db) throws Exception {
//
//        Class.forName(MYSQL_DRIVER);
//
//        Connection conn = DriverManager
//                .getConnection("jdbc:MySQL://localhost:3339/" + db
//                                       + "?useUnicode=true&characterEncoding=utf8",
//                               "root", "123456");
//
//        return conn;
//    }
//}
//
//class JobParameters{
//    private String postType = "";
//    private String workContent = "";
//    private String serviceCondition = "";
//    
//    public String getPostType() {
//    
//        return postType;
//    }
//    
//    public void setPostType(String postType) {
//    
//        this.postType = postType;
//    }
//    
//    public String getWorkContent() {
//    
//        return workContent;
//    }
//    
//    public void setWorkContent(String workContent) {
//    
//        this.workContent = workContent;
//    }
//    
//    public String getServiceCondition() {
//    
//        return serviceCondition;
//    }
//    
//    public void setServiceCondition(String serviceCondition) {
//    
//        this.serviceCondition = serviceCondition;
//    }
//}
