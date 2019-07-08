package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 任务分发(只是概率任务数量均衡)，随机均衡算法
 * 原理：workers * (jonNum)
 */

public class DistributionTask {
    public static void main(String[] args) {
        StringBuffer tail0 = new StringBuffer();
        for (int j = 0; j < 10000; j++) {
            int jobNum = new Random().nextInt(999999999);
            int workers = new Random().nextInt(9999) + 1;
            List<Integer> list = new ArrayList<>();

            int zhi = jobNum / workers;
            int yushu = jobNum % workers;
            int end = 0;
            StringBuffer tail = new StringBuffer();
            for (int i = 0; i < workers; i++) {
                if (yushu > (workers - i - 1)) {
                    end = end + zhi + 1;
                    tail.append("add:");
                } else if (yushu != 0 && new Random().nextBoolean()) {
                    yushu--;
                    end = end + zhi + 1;
                    tail.append("add:");
                } else {
                    end = end + zhi;
                }
            }
            if (end != jobNum) {  //每次结果不对
                tail0.append(tail.toString());
            }
        }
        if (tail0.length() == 0) {
            System.out.println("完美");
        }
    }
}

