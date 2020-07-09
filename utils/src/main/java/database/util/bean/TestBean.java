package database.util.bean;


import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "xsec_databases")
public class TestBean {
    @Column(name = "database_id")
    private Long id;
    private String name; // 数据库名称
    private int dialect; // 数据库方言
    @Column(name = "description")
    private String desc = ""; // 数据库描述信息
    @Column(name = "db_type")
    private int type; // 数据库类型
//    @Column(name = "db_version")
    private int versionType; // 数据库版本信息
}
