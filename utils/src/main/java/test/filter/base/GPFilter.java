package test.filter.base;

import org.apache.commons.lang.StringUtils;

public abstract class GPFilter implements MyFilter {
    protected String name = "";

    public GPFilter(String name) {
        if(StringUtils.isNotEmpty(name)) {
            this.name = name;
        }else {
            this.getClass().getName();
        }
    }

    public abstract String execute(FilterParams params);
}
