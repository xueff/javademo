package database.accsess;

import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    /**
     * 纯java代码实现 连接access数据库
     * @author xuyl
     *
     */
public class ConnectAccessDatabase {
        public static void main(String[] args) {
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=E:\\demo.mdb";
                Connection con = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
                Statement sta = con.createStatement();
                ResultSet rst = sta.executeQuery("select * from demoTable");//demoTable为access数据库中的一个表名
                if(rst.next()){
                    System.out.println("纯java代码实现:" + rst.getString("name"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
}
