package javabase.generic;

import common.bean.Men;
import common.bean.Person;
import common.bean.Women;
import io.vertx.core.json.JsonObject;

/**
 * 泛型向上，子类->父类
 */
public class Super {
    public static void main(String[] args) {
        Person p = new Person();
        Women w = new Women();
        Men m = new Men();
        System.out.println(getString(Person.class,p));
        System.out.println(getString(Women.class,w));
//        System.out.println(getString(Men.class,m));  Error

    }



    private static String getString(Class<? super Women> p, Object o){
        if(p.isInstance(o)){
            return JsonObject.mapFrom(p.cast(o)).toString();
        }
        return null;
    }
}
