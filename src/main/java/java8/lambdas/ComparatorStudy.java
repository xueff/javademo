package java8.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1917:08
 */
public class ComparatorStudy {
    public static void main(String[] args) {
        //-------old------------
        List<String> strs = Arrays.asList("C", "a", "A", "b");
        Collections.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        //-----------new
        Collections.sort(strs, String::compareToIgnoreCase);

    }
}
