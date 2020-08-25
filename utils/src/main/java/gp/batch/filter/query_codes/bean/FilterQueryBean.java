package gp.batch.filter.query_codes.bean;

import java.util.List;

public class FilterQueryBean {
    private List<FilterBean> filters;
    private boolean isMail;

    public List<FilterBean> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterBean> filters) {
        this.filters = filters;
    }

    public boolean isMail() {
        return isMail;
    }

    public void setMail(boolean mail) {
        isMail = mail;
    }
}