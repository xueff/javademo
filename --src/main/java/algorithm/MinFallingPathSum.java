package algorithm;

/**性能差
 * leetcode:931. 下降路径最小和
 */
public class MinFallingPathSum {

    public static void main(String[] args) {
//        int[][] A = {{1,2,3},{12,5,6},{7,8,9},{12,3,1}};
        int[][] A = {{69}};
        System.out.println(minFallingPathSum(A));
    }
    private static int zhi = Integer.MAX_VALUE;
    public static int minFallingPathSum(int[][] A) {
//        for (int i = 0; i < A.length; i++) {
            sum(A,0,0,0);
//        }
        return zhi;
    }

    private static int sum(int[][] A,int row,int index,int st){
        if(row>A.length-1) return st;
        int temp = st;
        if(index-1>=0){
            temp += A[row][index-1];
            temp = sum(A, row+1,index-1,temp);
            if(row==A.length-1 && temp<zhi)
                zhi = temp;
        }
        temp = st + A[row][index];
        temp = sum(A, row+1,index,temp);
        if(row==A.length-1 && temp<zhi)
            zhi = temp;

        if(index+1<=A[row].length-1){
            temp = st + A[row][index+1];
            temp =sum(A, row+1,index+1,temp);
            if(row==A.length-1 && temp<zhi)
                zhi = temp;
        }
        return temp;
    }
}
