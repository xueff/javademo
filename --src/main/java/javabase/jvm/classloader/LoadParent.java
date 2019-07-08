package javabase.jvm.classloader;


 public class LoadParent {
     int a = 10;
     static int b = 11;
     static{
         System.out.println("Parent静态代码块：b=" + b);
         b++;
     }
     {
          System.out.println("Parent代码块： a=" + a);
          System.out.println("Parent代码块： b=" + b);
          b++;
          a++;
     }

    public LoadParent() {
        System.out.println("Parent无参构造函数： a=" + a);
        System.out.println("Parent无参构造函数： b=" + b);
    }
    public LoadParent(int a) {
        System.out.println("Parent有参构造函数： a=" + a);
        System.out.println("Parent有参构造函数： b=" + b);
    }

    void function() {
        System.out.println("Parent function run ……");
    }
}
