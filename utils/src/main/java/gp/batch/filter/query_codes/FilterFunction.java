package gp.batch.filter.query_codes;


import gp.batch.filter.enums.FilterAction;

import java.util.List;

public class FilterFunction {
    private String name;
    private FilterAction action;
    private List<String> args;

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }


    public FilterAction getAction() {
        return action;
    }

    public void setAction(FilterAction action) {
        this.action = action;
    }
}
