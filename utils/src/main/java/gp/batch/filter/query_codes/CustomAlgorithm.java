package gp.batch.filter.query_codes;

import gp.batch.filter.enums.FilterAction;
import gp.batch.filter.query_codes.bean.BeanAction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:frank
 * @Create:2018/8/6
 * @Explain:
 */
public class CustomAlgorithm {
    private static boolean equals(String v, String source) {
        return v.equalsIgnoreCase(source);
    }

    private static boolean contains(String v, String source) {
        return source.contains(v);
    }

    private static boolean prefix(String v, String source) {
        return source.startsWith(v);
    }

    private static boolean suffix(String v, String source) {
        return source.endsWith(v);
    }

    private static boolean regex(String v, String source) {
        boolean result = false;
            Pattern pattern = Pattern.compile(v);
            Matcher matcher = pattern.matcher(source);
            if (matcher.matches()) {
                result = true;
            }
        return result;
    }

    public static boolean algorithm(BeanAction beanAction, String source) {
        boolean result = false;
        String action = beanAction.getAction().substring(0, beanAction.getAction().indexOf("("));
        String filterV = beanAction.getAction().substring(beanAction.getAction().indexOf("(")+1, beanAction.getAction().indexOf(")"));
            if (FilterAction.Equals.equals(action)) {
                result = equals(filterV, source);
            } else if (FilterAction.Large.equals(action)) {
                result = large(filterV, source);
            }else if (FilterAction.ELarge.equals(action)) {
                result = eLarge(filterV, source);
            } else if (FilterAction.Contains.equals(action)) {
                result = contains(filterV, source);
            } else if (FilterAction.Between.equals(action)) {
                result = between(filterV, source);
            } else if (FilterAction.Less.equals(action)) {
                result = less(filterV, source);
            }else if (FilterAction.ELess.equals(action)) {
                result = eLess(filterV, source);
            } else if (FilterAction.In.equals(action)) {
                result = in(filterV, source);
            } else if (FilterAction.AllIn.equals(action)) {
                result = allIn(filterV, source);
            } else if (FilterAction.Prefix.equals(action)) {
                result = prefix(filterV, source);
            } else if (FilterAction.Suffix.equals(action)) {
                result = suffix(filterV, source);
            } else if (FilterAction.Regex.equals(action)) {
                result = regex(filterV, source);
            }

        return result;
    }

    private static boolean allIn(String v, String source) {
        Set<String> vSet = new HashSet<>(Arrays.asList(v.split(",")));
        Set<String> set = new HashSet<>(Arrays.asList(source.split(",")));
        boolean result = false;
        for (String args: set) {
            if (!vSet.contains(args)) {
                return false;
            }else {
                result = true;
            }
        }
        return result;
    }

    private static boolean between(String v, String source) {
            if(source == null){
                return false;
            }
            String[] vs = v.split(",");
            Integer it = Integer.valueOf(source);
            Integer v1 = Integer.valueOf(vs[0]);
            Integer v2 = Integer.valueOf(vs[1]);
            if("~".equals(v1) && "~".equals(v2) ){
                return true;
            }else if(v1.equals( v2)){
                return v1.equals(it);
            }else if("~".equals(v1) && !"~".equals(v2)){
                return it<=v2;
            }else if(!"~".equals(v1) && "~".equals(v2)){
                return it>=v1;
            }else {
                return it >= v1 && it<= v2;
            }
    }

    private static boolean eLess(String v, String source) {
        if(source.indexOf(".")>0){
            return Double.valueOf(v) <=  Double.valueOf(source);
        }else {
            return Long.valueOf(v) <= Long.valueOf(source);
        }
    }

    private static boolean less(String v, String source) {
        if(source.indexOf(".")>0){
            return Double.valueOf(v) <  Double.valueOf(source);
        }else {
            return Long.valueOf(v) < Long.valueOf(source);
        }
    }

    private static boolean large(String v, String source) {
        if(source.indexOf(".")>0){
            return Double.valueOf(v) >  Double.valueOf(source);
        }else {
            return Long.valueOf(v) > Long.valueOf(source);
        }
    }
    private static boolean eLarge(String v, String source) {
        if(source.indexOf(".")>0){
            return Double.valueOf(v) >=  Double.valueOf(source);
        }else {
            return Long.valueOf(v) >= Long.valueOf(source);
        }
    }

    private static boolean in(String v, String source) {
        Set<String> vSet = new HashSet<>(Arrays.asList(v.split(",")));
        Set<String> set = new HashSet<>(Arrays.asList(source.split(",")));

        boolean result = false;
        for (String args: set) {
            if (vSet.contains(args)) {
                result = true;
            }
        }
        return result;
    }
}
