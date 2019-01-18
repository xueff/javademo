package utils;

import java.text.DecimalFormat;

public class NumberUtils {
    public static double keep2DecimalPlaces(Double value){
        if(value == null) return value;
        DecimalFormat df   = new DecimalFormat("######0.00");
        return Double.valueOf(df.format(value));
    }
}
