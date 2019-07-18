import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 执行：java -Xms4096m -Xmx4096m Memory
 * @author xuefei
 * @description 内存
 * @date 2019/7/18
 */
public class Memory {

    static List<Byte[]> list = new ArrayList<>();
    static  int length = 1024*1024*1000;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
            for(int i=0;i<100;i++) {

                // 从键盘接收数据

                // nextLine方式接收字符串
                System.out.println("nextLine方式接收：");
                // 判断是否还有输入
                if (scan.hasNextLine()) {
                   String str2 = scan.nextLine();
                    list.add(new Byte[length]);
                    System.out.println("内存：" + (length*list.size()));
                }
//                String str2 = scan.nextLine();
//                System.out.println("输入的数据为：" + str2);
//                scan.close();
        }
    }
}
