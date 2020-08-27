package gp.batch.filter.query_codes;

import gp.batch.filter.data_get.FilterBeanDatasService;
import gp.batch.filter.query_codes.bean.BeanAction;
import gp.batch.filter.query_codes.bean.FilterQueryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.search.SearchTerm;
import java.util.*;

public class DoFilter {
    private static Logger log = LoggerFactory.getLogger(DoFilter.class);

    public static Set<String> doing(FilterQueryBean queryBean, Set<String> codes) {

        if(queryBean != null && queryBean.getFilters() != null){
            queryBean.getFilters().forEach(it->{
                if(it.isFilter()){
                    Map<String,String>  map = new FilterBeanDatasService().getDatas(it.getBeanAction());
                    map.forEach((k,v)->{
                        if(codes.contains(k)){
                            BeanAction beanAction = it.getBeanAction();
                            if(!filter(beanAction,v)){
                                codes.remove(k);
                            }

                        }
                    });
                }
            });

        }
        return codes;
    }

    private static boolean filter(BeanAction beanAction, String v) {
        return CustomAlgorithm.algorithm(beanAction,v);
    }


    public static void main(String[] args) {
        String action = "AllIn(A,B)";

        action = action.substring(action.indexOf("(")+1,action.lastIndexOf(")"));
        String type = "AA";
        Set<Object> set = new HashSet<>();
        String[] vs = action.split(",");
        if(type.equalsIgnoreCase("String")){

        }else if(type.equalsIgnoreCase("Long")){

        }else if(type.equalsIgnoreCase("Integer")){

        }


    }
}
