package javabase.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xuefei
 * @description arraylist
 * @date 2019/8/8
 */
public class ArrayListTest {

    /**
     * 安全转ArrayList
     */
    public static void arraysToArrayList() {
        List<String> assetList = new ArrayList();

        String[] asset = {"equity", "stocks", "gold", "foriegn exchange", "fixed income", "futures", "options"};



        Collections.addAll(assetList, asset);
        assetList.add("s");
        System.out.println(assetList);

    }
}
