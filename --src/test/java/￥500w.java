
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * 每天五注，早晚掏空奖池(梦想总是遥不可及，还不如梭哈一把)
 * @author xiehu
 * @date 2018年11月08日
 *
 */
public class ￥500w {

    @Test
    public void 双色球(){

        Random random=new Random();
        for (int j = 0; j < 5; j++) {
            List<Integer> list=new ArrayList<>();
            StringBuffer sb=new StringBuffer();
            //红号
            for (int i = 0; i < 6; i++) {
                int red=random.nextInt(33)+1;
                if(list.contains(red)){
                    i=i-1;
                    continue;
                }
                list.add(red);
            }
            Collections.sort(list);
            for (Integer integer : list) {
                sb.append(integer).append(",");
            }
            //蓝号
            int blue=random.nextInt(16)+1;
            sb.append("--").append(blue);
            System.out.println(sb);
            if(j!=4){
                System.out.println("=================");
            }
        }
        String notify=chickenSoup.get(random.nextInt(chickenSoup.size()-1));
        System.out.println("======"+notify+"======");
    }


    @Test
    public void 大乐透(){

        Random random=new Random();
        for (int j = 0; j < 5; j++) {
            List<Integer> redlist=new ArrayList<>();
            StringBuffer sb=new StringBuffer();
            //红号
            for (int i = 0; i < 6; i++) {
                int red=random.nextInt(35)+1;
                if(redlist.contains(red)){
                    i=i-1;
                    continue;
                }
                redlist.add(red);
            }
            Collections.sort(redlist);
            for (Integer integer : redlist) {
                sb.append(integer).append(",");
            }
            //蓝号
            List<Integer> bluelist=new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                int blue=random.nextInt(12)+1;
                if(bluelist.contains(blue)){
                    i=i-1;
                    continue;
                }
                bluelist.add(blue);
            }
            sb.append("--");
            Collections.sort(bluelist);
            for (Integer integer : bluelist) {
                sb.append(integer).append(",");
            }
            System.out.println(sb);
            if(j!=4){
                System.out.println("=================");
            }
        }
        String notify=chickenSoup.get(random.nextInt(chickenSoup.size()-1));
        System.out.println("======"+notify+"======");
    }

    //鸡汤
    private static List<String> chickenSoup=new ArrayList<>();
    static{
        chickenSoup.add("没有加倍的勤奋，就既没有才能，也没有天才。");
        chickenSoup.add("才能的火花，常常在勤奋的磨石上迸发。");
        chickenSoup.add("所谓天才，那就是假话，勤奋的工作才是实在的。");
        chickenSoup.add("有远大抱负的人不可忽略眼前的工作。");
        chickenSoup.add("人生照例是一场值得一搏的争衡，然而它的奖品是拼斗。");
        chickenSoup.add("人生最终的价值在于觉醒和思考的能力，而不只在于生存。");
        chickenSoup.add("成功的秘诀，在永不改变既定的目的。");
        chickenSoup.add("生存的第一定律是：没有什么比昨天的成功更加危险。");
        chickenSoup.add("凡在小事上对真理持轻率态度的人，在大事上也是不足信的。");
        chickenSoup.add("人生就像骑单车。想保持平衡就得往前走。");
        chickenSoup.add("谁有历经千辛万苦的意志，谁就能达到任何目的。");
        chickenSoup.add("要从容地着手去做一件事，但一旦开始，就要坚持到底。");
        chickenSoup.add("活着就是为了改变世界，难道还有其他原因吗？");
        chickenSoup.add("你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。");
        chickenSoup.add("强的信念能赢得强者的心，并使他们变得更坚强。");
    }
}
