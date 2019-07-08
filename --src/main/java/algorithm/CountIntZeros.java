package algorithm;

/**
 * 统计int前有多少个0
 */
public class CountIntZeros {

    /**
     * 注意4个if，无else
     * 1.先判断i前面0的最大范围（16,8,4,2），满足则将i向高位移动对应范围
     * 2.依次判断新i前面0的最大范围，执行步骤1
     * 3.该方法，最多判断30位   16+8+4+2 = 30，
     * 3.1  32位情况无需判断
     * 3.2  n初始值为1，判断结果的可能偏大，所以最后 n -= i >>> 31;实际为判断判断移位后首位是否为1
     * @param i
     * @return
     */
    public static int numberOfLeadingZeros(int i) {
        if (i == 0)
            return 32;
        //计算0的个数
        int n = 1;
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;   //i = i<<16
        }
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        n -= i >>> 31;   //i移位为偶数位移动，判断移位后首位是0否为1
        return n;
    }

    public static void main(String[] args) {
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            numberOfLeadingZeros(i);
            System.out.print("i="+i);
        }
    }
}
