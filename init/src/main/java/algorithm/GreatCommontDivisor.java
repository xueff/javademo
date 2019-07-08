package algorithm;

/**
 * 最大公约数
 */
public class GreatCommontDivisor {

    /**
     *  m n 最大公约数后：碾转相除法
     * @param m
     * @param n
     * @return
     */
    public int getGreatCommontDivisor(int m,int n ) {
        // m和n的最大公约数 = n 和 m%n的最大公约数
        if(n == 0) {
            return m;
        }else {
            return getGreatCommontDivisor(n , m%n);
        }
    }

    public static void main(String[] args) {
        System.out.println(new GreatCommontDivisor().getGreatCommontDivisor(1024,64 ));
    }
}
