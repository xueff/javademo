package gp.filter;

import gp.filter.impl.AAFilter;
import io.vertx.core.json.JsonArray;
import gp.base.gp.GPFilter;

import java.util.ArrayList;
import java.util.List;

public class FilterList {
    List<GPFilter> filterList = new ArrayList<>();

    public void init(JsonArray filters) {

        for (int i = 0; i < filters.size(); i++) {

            GPFilter filter =  create(filters.getJsonObject(i).getString("name"));
            if (filter != null){
                filterList.add(filter);
            }
        }
    }

    public static GPFilter create(String content) {
        GPFilter matcher = null;
        if (content.isEmpty ()) {
            return null;
        }
        switch (content) {
            case "手机号":
                matcher = new AAFilter(content);
                break;
            default:
                matcher = null;
                break;
        }
        return matcher;
    }
}
