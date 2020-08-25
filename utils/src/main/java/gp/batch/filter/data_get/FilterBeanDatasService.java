package gp.batch.filter.data_get;

import java.util.HashMap;
import java.util.Map;

public class FilterBeanDatasService {

    public Map<String,String> getDatas(String name,String columnFilter){
        Map<String,String> objectMap = new HashMap<>();
        if(name.equalsIgnoreCase("xf.entity.GaiNian")){
            return getGaiNian(columnFilter);
        }

        return objectMap;
    }

    private Map<String, String> getGaiNian(String columnFilter) {
        // TODO

        return null;
    }
}
