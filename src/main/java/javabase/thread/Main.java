package javabase.thread;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) {
        Locale lo = new Locale("zh","UK");
        ResourceBundle bu = ResourceBundle.getBundle("message",lo);
        Locale lo1 = new Locale("zh","CN");
        ResourceBundle bu2 = ResourceBundle.getBundle("message",lo1);
        System.out.println("uk"+bu.getString("a"));
        System.out.println("cn"+bu2.getString("a"));
        System.out.println(bu.getLocale());
        System.out.println( bu2.getLocale());



    }
}
