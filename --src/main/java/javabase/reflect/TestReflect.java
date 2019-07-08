package javabase.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * java反射
 */
public class TestReflect {
    @Test
    public void getConstructors() throws Exception {
        Class<?> demo=null;
        try{
            demo=Class.forName("Reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }
        Person per1=null;
        Person per2=null;
        Person per3=null;
        Person per4=null;
        //取得全部的构造函数
        Constructor<?> cons[]=demo.getConstructors();
        try{
            per1=(Person)cons[0].newInstance();
            per2=(Person)cons[1].newInstance("Rollen");
            per3=(Person)cons[2].newInstance(20);
            per4=(Person)cons[3].newInstance("Rollen",20);
        }catch(Exception e){
            e.printStackTrace();
        }
        // 结果：[null  0] [Rollen  0] [null  20] [Rollen  20]
        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);

    }
    //实例化Class类对象
    @Test
    public void forName() throws Exception {
        Class<?> demo1=null;
        Class<?> demo2=null;
        Class<?> demo3=null;
        try{
            //一般尽量采用这种形式
            demo1=Class.forName("javabase.reflect.Person");
        }catch(Exception e){
            e.printStackTrace();
        }
        demo2=new Person().getClass();
        demo3=Person.class;

        System.out.println("类名称   "+demo1.getName());
        System.out.println("类名称   "+demo2.getName());
        System.out.println("类名称   "+demo3.getName());
    }

    @Test
    public void forNameInstance() throws Exception {
        Class<?> demo=null;
        try{
            //一般尽量采用这种形式
            demo=Class.forName("javabase.reflect.Demo");
        }catch(Exception e){
            e.printStackTrace();
        }
        Person per=null;
        try {
            per=(Person)demo.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

/**
 * 反射一定要有无参构造器
 */
class Person{

    public Person() {

    }
    public Person(String name){
        this.name=name;
    }
    public Person(int age){
        this.age=age;
    }
    public Person(String name, int age) {
        this.age=age;
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString(){
        return "["+this.name+"  "+this.age+"]";
    }
    private String name;
    private int age;
}


