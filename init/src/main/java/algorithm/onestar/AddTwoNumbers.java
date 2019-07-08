package algorithm.onestar;

import java.util.*;

/**
 * what：2个链表数相加，组成新链表
 * details：定义：（非0情况，最高位不为0）
 *       数组A：{1,2,3}   -> 数：321
 *       数组B：{1,2}     ->数：21
 *  要求 ： A+B -> 321+21 = 342
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        Integer[] a = {9,8,9};
        Integer[] b = {9,9,9};
        System.out.println(addTwoNumbers(Arrays.asList(a),Arrays.asList(b)));
    }


    public static List<Integer> addTwoNumbers(List<Integer> l1, List<Integer> l2) {
        Iterator<Integer> i1 =  l1.iterator();
        Iterator<Integer> i2 =  l2.iterator();
        List<Integer> res = new ArrayList<>();
        int jinwei = 0;
        while (true){
            if(!i1.hasNext() && !i2.hasNext()){
                if(jinwei>0)  res.add(jinwei);
                break;
            }
            int add = (i1.hasNext()?i1.next():0) +  (i2.hasNext()?i2.next():0) + jinwei;
            res.add(add%10);
            jinwei = add/10;
        }
        return res;
    }





}
