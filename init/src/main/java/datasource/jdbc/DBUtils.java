package datasource.jdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

public class DBUtils {
    private static  Connection connection = null;
    public static Connection getConnection() {
        return connection;
    }

    public static void main(String args[]) {
        Connection conn;
        QueryRunner queryRunner = new QueryRunner();
        ArrayHandler arrayHandler = new ArrayHandler();
        try {
            Class.forName(DBConf.JDBC_DRIVER);
            conn = DriverManager.getConnection(DBConf.  DB_URL, DBConf.USER, DBConf.PASS);

//            String sql = "select * from gbk where id=?";
            String sql = "SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name='gbk' AND table_schema='db-gbk'  and column_name='name' ";
            Map<String, Object> resultMap = queryRunner.query(conn, sql, new MapHandler());
            System.out.println(resultMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
