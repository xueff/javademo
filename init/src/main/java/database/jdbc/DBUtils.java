//package database.jdbc;
//
//import io.vertx.core.json.JsonObject;
//import io.vertx.ext.web.RoutingContext;
//import org.apache.commons.dbutils.QueryRunner;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DBUtils {
//    private static  Connection connection = null;
//    public static Connection getConnection() {
//        return connection;
//    }
//
//    public void getHotelDevice(RoutingContext context){
//        JsonObject params = context.getBodyAsJson();
//        System.out.println(params);
//        String hotelId = params.getString("hotelId");
//        Integer deviceKey = params.getInteger("deviceKey");
//
//        try {
//            String sql= "SELECT " +
//                    "      HotelID as hotelId\n" +
//                    "      ,DeviceName as deviceName\n" +
//                    "      ,DeviceKey as deviceKey\n" +
//                    "  FROM dbo.dbname\n" +
//                    "  where" +
//                    (deviceKey== null?"":" deviceKey ="+deviceKey+  " AND " )+
//                    " HotelID = '" +hotelId +"'";
//
//            //创建SQL执行工具
//            QueryRunner qRunner = new QueryRunner();
//            //执行SQL查询，并获取结果
//            List<HotelLiftInfoModel> list = (List<HotelLiftInfoModel>) qRunner.query(connection, sql, new BeanListHandler(HotelLiftInfoModel.class));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void addHotelDevice(RoutingContext context){
//        JsonObject req = context.getBodyAsJson();
//        String hotelId = req.getString("hotelId");
//        try {
//
//            QueryRunner qr = new QueryRunner();
//            String sql = "insert into RoomDevices (hotelId,masterId,roomHostId,roomNo,deviceList,createdTime,updatedBy,updatedTime) values (?,?,?,?,?,?,?,?)";
//            Object params[][] = new Object[req.getJsonArray("data").size()][];
//
//            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
//            String date_str=df.format(new Date());
//            int count = 0;
//            for (int i = 0; i < req.getJsonArray("data").size(); i++) {
//                JsonObject json = req.getJsonArray("data").getJsonObject(i);
//                params[i] = new Object[] { hotelId,"","",json.getString("roomNo"),json.getJsonArray("deviceList").toString()
//                        ,date_str,json.getString("updatedBy"),date_str};
//            }
//            int[] res = qr.batch(connection, sql, params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
