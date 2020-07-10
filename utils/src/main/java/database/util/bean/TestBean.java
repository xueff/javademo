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

    public TestBean() {
    }

    public TestBean(Long id, String name, int dialect, String desc, int type, int versionType) {
        this.id = id;
        this.name = name;
        this.dialect = dialect;
        this.desc = desc;
        this.type = type;
        this.versionType = versionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDialect() {
        return dialect;
    }

    public void setDialect(int dialect) {
        this.dialect = dialect;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersionType() {
        return versionType;
    }

    public void setVersionType(int versionType) {
        this.versionType = versionType;
    }
}
