package java8.lambdas;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1917:03
 */
public class ThreadStudy {
    public static void main(String[] args) {
        ////-----------new----------------//////
        new Thread(() -> System.out.println("Hi")).start();

        ////-------------old------------------////
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hi");
            }
        };
        new Thread(runnable).start();
    }
}
