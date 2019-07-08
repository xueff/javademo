package algorithm;

/**
 * Z 字形变换
 */
public class Z {

    public static void main(String[] args) {
//        String s = "AB";
//        int len = s.length();
//        int n = 1;
//        if(n==1) return;
//        String[] result = new String[len];
//        for(int i=0;i<=n;i++){
//          int zhi = i/(4*(n-1));
//          int yu = i%(4*(n-1));
//          if(yu<=(n-1))
//            result[i] = s.charAt();
//        }
//        System.out.println(result.toString());
    }

    public static void test(String[] args) {
        String s = "AB";
        int len = s.length();
        int n = 1;
        if(n<=1) return ;
        StringBuffer result = new StringBuffer();
        for(int i=1;i<=n;i++){
            if(i!=1)
                result.append("\n");
//                int count = 1;
//                for (int j=(i-1); ;j++) {
//                    if(j>len) break;
//                    if(j== (2*(n-1)+1))  count++;
//                    if((j==((2 * (n-1)) * (count - 1) + (i)))){
//                        System.out.print(s.charAt(j-1));
//                    }else if ((j==((2 * (n-1)) * (count - 1) + (i)+2*(n-i))) ){
//
//                        System.out.print(s.charAt(j-1));
//                    }else {
//                        System.out.print(" ");
//                    }
//
//
//                }



            if(i==1 || i==n) {
                int count = 1;
                for (int j=(i-1); ;j++) {
                    if(j>len) break;
                    if(j==((2 * (n-1)) * (count - 1) + (i))){
                        count++;
                        result.append(s.charAt(j-1));
                    }else {
                        result.append(" ");
                    }
                }
            }

            if(i!=1 &&  i!=n) {
                int count = 1;
                for (int j=(i-1); ;j++) {
                    if(j>len) break;
                    if((j==((2 * (n-1)) * (count - 1) + (i)))){
                        result.append(s.charAt(j-1));
                    }else if ((j==((2 * (n-1)) * (count - 1) + (i)+2*(n-i))) ){
                        count++;
                        result.append(s.charAt(j-1));
                    }else {
                        result.append(" ");
                    }


                }
            }
        }
        System.out.println(result.toString());
    }
    }

