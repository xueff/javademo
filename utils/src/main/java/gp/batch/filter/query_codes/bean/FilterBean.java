package gp.batch.filter.query_codes.bean;

public class FilterBean{

    private String name;
    private BeanAction beanAction;
    private boolean isFilter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFilter() {
        return isFilter;
    }

    public void setFilter(boolean filter) {
        isFilter = filter;
    }

    public BeanAction getBeanAction() {
        return beanAction;
    }

    public void setBeanAction(BeanAction beanAction) {
        this.beanAction = beanAction;
    }
}

