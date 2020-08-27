package common.bean;


import com.alibaba.fastjson.annotation.JSONField;
import javabase.ramdon.RamdonStudy;
import lombok.Data;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1714:20
 */
@Data
public class Person {
    @JSONField(name="userName")
    private String name;
    @JSONField(name="Age")
    private int age;
    private int id;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    private Double money;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
        this.name = RamdonStudy.getRandomString(RamdonStudy.getRamdonInt(62));
        this.age = RamdonStudy.getRamdonInt(150);
        this.money = RamdonStudy.getRamdonDouble();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }


    public static void main(String[] args) {
        File file = new File("D:\\project\\idr-holmes-copy\\holmes\\taskContent\\187676");
        if(file.delete()){
            System.out.println("AAA");
        }


    }

    public static Object getBeanFiledValueByName(Object o,String filedName) {
        String getter = "get" +  filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        try {
            // 通过method的反射方法获取其属性值
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;

    }
}
