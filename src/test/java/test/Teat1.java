package test;

import org.junit.Test;
import utils.NumberUtils;

import java.util.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat1 {
    public static void main(String[] args) {
        Double a = 22.01;
        for (int i=0;i<1000;i++)
            System.out.println( NumberUtils.keep2DecimalPlaces(a += 0.01));

    }

    @Test public void getMax(){
        Map<String,Integer> fbu = new HashMap<>();
        String test = "";
        String[] array = test.split("=");
        for(int i=0;i<array.length;i++){
            if(fbu.containsKey(array[i])){
                fbu.put(array[i], fbu.get(array[i])+1);
            }else{
                fbu.put(array[i], 1);
            }
        }
        System.out.println(fbu);
    }

}

