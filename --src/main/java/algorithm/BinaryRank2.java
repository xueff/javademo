package algorithm;

import java.util.Vector;
/**
 * 二分查找递归
 */

class BinaryRank {
    protected void a(){

    }
    public static int rank(int key, int[] a) {

        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {

        // 如果key存在于a[]中，
        // 它的索引不会小于lo且不会大于hi
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return rank(key, a, lo, mid - 1);
        else if (key > a[mid])
            return rank(key, a, mid + 1, hi);
        else
            return mid;
    }
    public static void main(String[] args) {
        Vector<String> v = new Vector<>();
        for(int i=0;i<50;i++){
            v.add(String.valueOf(i));
        }
        for (String string : v) {

            System.out.println(string);
        }
    }

}


/**
 * 二分查找递归
 */
public class BinaryRank2 extends BinaryRank {
    @Override
    public void a(){

    }
    public static int rank(int key, int[] a) {

        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {

        // 如果key存在于a[]中，
        // 它的索引不会小于lo且不会大于hi
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return rank(key, a, lo, mid - 1);
        else if (key > a[mid])
            return rank(key, a, mid + 1, hi);
        else
            return mid;
    }
    public static void main(String[] args) {
        Vector<String> v = new Vector<>();
        for(int i=0;i<50;i++){
            v.add(String.valueOf(i));
        }
        for (String string : v) {

            System.out.println(string);
        }
    }
}

