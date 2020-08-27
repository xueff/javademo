package gp.batch.filter.data_get;

import gp.batch.filter.query_codes.bean.BeanAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterBeanDatasService {

    public Map<String,String> getDatas(BeanAction action){
        Map<String,String> objectMap = new HashMap<>();
        // beanName -> cache -->beanDao
        if(action.getBeanName().equalsIgnoreCase("xf.entity.GaiNian")){
            return getGaiNian(action);
        }

        return objectMap;
    }

    private Map<String, String> getGaiNian(BeanAction action) {
        // TODO
        return null;
    }
}
