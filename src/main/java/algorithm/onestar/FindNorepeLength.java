package algorithm.onestar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 查找字符串中无重复段的最大数量
 */
public class FindNorepeLength {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("ashflamfjd54123456789+*pioiytytrewqaxvbnbm,ioh"));
    }

    /**
     * 原理
     * 1.无重复加入set，统计长度
     * 2.遇到重复，删除最前面
     * 3.无重复加入set，统计长度
     * 4. ...
     * 5.next 重复   i删除到新位置
     * 6.开始统计新长度
     * 7.......
     * 8.结果为最大值
     * @text 滑动窗口
     * @效率 2n
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        System.out.println("i="+i+",j="+j);
        return ans;
    }
    /**
     * 原理：记录坐标和和当前减 取最大值
     * @text 优化滑动窗口
     * @效率 n
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
