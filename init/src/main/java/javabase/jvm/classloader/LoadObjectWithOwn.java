package javabase.jvm.classloader;
/**
 * 对象加载
 * @author ffxue
 *
 */
public class LoadObjectWithOwn{
/**
 * 解析如下：
1.首先，需要明白类的加载顺序。
(1) 父类静态对象和静态代码块
(2) 子类静态对象和静态代码块
(3) 父类非静态对象和非静态代码块
(4) 父类构造函数
(5) 子类 非静态对象和非静态代码块
(6) 子类构造函数
2.因而，整体的执行顺序为
public static Test t1 = new Test(); //(1)
static
{
    System.out.println("B"); //(2)
}
Test t2 =new Test(); //(3)

在执行(1)时创建了一个Test对象，在这个过程中会执行非静态代码块和缺省的无参构造函数，
在执行非静态代码块时就输出了A；然后执行(2)输出B；执行(3)的过程同样会执行非静态代码块和缺省的无参构造函数，在执行非静态代码块时输出A。

 *因此，最终的结果为ABA
 */
    public static LoadObjectWithOwn t1 = new LoadObjectWithOwn();

    {
        System.out.println("A");
    }

    static{
        System.out.println("B");
    }

    public static void main(String[] args){
        LoadObjectWithOwn t2 = new LoadObjectWithOwn();
    }
}