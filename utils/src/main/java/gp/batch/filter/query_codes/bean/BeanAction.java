package gp.batch.filter.query_codes.bean;

public class BeanAction{

    private String beanName;
    private String filterColumn;
    private String codeColumn;
    private String method;
    private String action;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }

    public String getCodeColumn() {
        return codeColumn;
    }

    public void setCodeColumn(String codeColumn) {
        this.codeColumn = codeColumn;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
