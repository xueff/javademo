package javabase.generic;

import common.bean.Men;
import common.bean.Person;
import common.bean.Women;
import io.vertx.core.json.JsonObject;

/**
 * 泛型向下  子类满足
 */
public class Extends {
    public static void main(String[] args) {
        Person p = new Person();
        Women w = new Women();
        Men m = new Men();
        System.out.println(getString(p.getClass(),p));
        System.out.println(getString(w.getClass(),w));
        System.out.println(getString(m.getClass(),m));

    }



    private static String getString(Class<? extends Person> p, Object o){
        if(p.isInstance(o)){
            return JsonObject.mapFrom(p.cast(o)).toString();
        }
        return null;
    }
}
