package mytest;

import javabase.num.BigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;

public class Reduce {


    @Test
    public void reduce(){
        BigDecimal start = new BigDecimal(10.0);
        BigDecimal add = new  BigDecimal(0.01);
        //价格
        for(int i = 0;i<100;i++) {
//            数量
            for(int j=0;j<100;j++){
                BigDecimal price = start.multiply( new BigDecimal(j)).multiply( new BigDecimal(100));
                BigDecimal by;
                if(price.doubleValue()>10000.0){
                    by = price.divide(new BigDecimal(10000));
                }else{
                    by = new BigDecimal(5);
                }


                System.out.println("jiage:" + start +"  fei:"+by);
                start.add(add);
            }
        }
    }
}
