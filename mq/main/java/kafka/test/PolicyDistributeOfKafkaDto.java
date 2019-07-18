package kafka.test;


/**
 * @author xuefei@idss.com
 * @description 策略下发kafka
 * @date 2019/7/9
 */
public class PolicyDistributeOfKafkaDto {
    private String database;
    private String tableName;
//    private String columnFamilyName;
    private String columnName;
//    private String ownerGroup;
    private String ownerUser;
    private String transformer;
    private String type;
//    private Date createTime;


    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }

    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
