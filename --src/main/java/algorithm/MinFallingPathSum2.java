package algorithm;

/**新建二维数组 ，  从上取周围最小值和下方相加   ，一直重复执行，最后遍历最后数组，取最小
 * leetcode:931. 下降路径最小和
 */
public class MinFallingPathSum2 {

    public static void main(String[] args) {
        int[][] A = {{1,2,3},{12,5,6},{7,8,9},{12,3,1}};
//        int[][] A = {{69}};
        System.out.println(new MinFallingPathSum2().minFallingPathSum(A));
    }
    public static int[][] dp;

    public static int Min(int a,int b){
        return a < b ? a : b;
    }
    public int minFallingPathSum(int[][] A) {
        int len = A[0].length;
        if(len == 1) return A[0][0];
        dp = new int[len][len];
        for(int i = 0;i < len;i++){
            dp[0][i] = A[0][i];
        }
        for(int i = 1;i < len;i++){
            for(int j = 0;j < len;j++){
                if(j == 0){
                    dp[i][j] = A[i][j] + Min(dp[i-1][j],dp[i-1][j+1]);
                }else{
                    if(j == len-1){
                        dp[i][j] = A[i][j] + Min(dp[i-1][j],dp[i-1][j-1]);
                    }else{
                        dp[i][j] = A[i][j] + Min(Min(dp[i-1][j],dp[i-1][j+1]),dp[i-1][j-1]);
                    }
                }
            }
        }
        int ans = 20000;
        for(int i = 0;i < len;i++){
            ans = Min(ans,dp[len-1][i]);
        }
        return ans;
    }
}
