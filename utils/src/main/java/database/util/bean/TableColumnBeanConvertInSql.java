package database.util.bean;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库bean spring ：驼峰命名转下划线
 */
public class TableColumnBeanConvertInSql {
    public static void main(String[] args) {
        System.out.println(getTableName(TestBean.class));
        System.out.println(JSONObject.toJSONString(getColumnsName(TestBean.class)));
    }

    /**
     * 获取注解或者驼峰命名的列名
     * @param classz
     * @return
     */
    public static Map<String,String> getColumnsName(Class classz) {
        //通过反射获取到类，填入类名
        Class cl1 = null;
        try {
            cl1 = Class.forName(classz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取类中所有的方法
        Field[] fields = cl1.getDeclaredFields();
        Map<String,String> map = new HashMap<>(fields.length);
        for (Field field : fields) {
            Column column = (Column) field.getAnnotation(Column.class);
            String beanName = field.getName();
            String columnName ="";
            if(column != null && StringUtils.isNotEmpty(column.name())){
                columnName = column.name();
            }else {
                columnName = humpToUnderline(beanName);
            }
            map.put(beanName,columnName);
        }
        return map;

    }

    /**
     * 获取注解或者驼峰命名的表名
     * @param classz
     * @return
     */
    public static String getTableName(Class classz) {
        //通过反射获取到类，填入类名
        Class cl1 = null;
        try {
            cl1 = Class.forName(classz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取Tableg注解
        Table table = (Table) cl1.getAnnotation(Table.class);
        //获取类注解的value值
        String tableName = "";
        if(table != null && StringUtils.isNotEmpty(table.name())){
            tableName = table.name();
        }else {
            tableName = humpToUnderline(classz.getSimpleName());
        }
        return tableName;

    }

    /***
     * 下划线命名转为驼峰命名
     *
     * @param para
     *        下划线命名的字符串
     */

    public static String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }



    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//偏移量，第i个下划线的位置是 当前的位置+ 偏移量（i-1）,第一个下划线偏移量是0
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toLowerCase();
    }

}
