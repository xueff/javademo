package jvm.classloader;


 public class LoadChildTest extends LoadParent {
     int x = 10;
     static int y = 11;
     static{
         System.out.println("Child静态代码块：y=" + y);
         y++;
     }
     {
          System.out.println("Child代码块： x=" + x);
          System.out.println("Child代码块： y=" + y);
          x++;
          y++;
     }

    public LoadChildTest() {
        System.out.println("Child无参构造函数： x=" + x);
        System.out.println("Child无参构造函数： y=" + y);
    }

    void function() {
        System.out.println("Child function run ……");
    }
    
    public static void main(String[] args) {
        LoadChildTest childTest = new LoadChildTest();
        childTest.function();
        System.out.println("============================");
        LoadChildTest childTest2 = new LoadChildTest();
        childTest2.function();
    }
}
