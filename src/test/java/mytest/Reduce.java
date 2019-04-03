package mytest;

import javabase.num.BigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;

public class Reduce {


    @Test
    public void sale(){
        BigDecimal start = new BigDecimal("1.0");
        BigDecimal add = new  BigDecimal("0.01");
        BigDecimal last = new  BigDecimal("0");
        //价格

        for(int i = 0;i<100;i++) {
//            数量
            for(int j=0;;j++){
                BigDecimal mount = new BigDecimal(j).multiply( new BigDecimal(100));
                BigDecimal total = start.multiply(mount);
                if(total.doubleValue()<1000) continue;
                if(total.doubleValue()>20000) continue;

                BigDecimal by;
                //手
                if(total.divide(new BigDecimal(10000)).multiply(new BigDecimal(2.5)).doubleValue()>5){
                    by = total.divide(new BigDecimal(10000)).multiply(new BigDecimal(2.5));
                }else{
                    by = new BigDecimal(5);
                }
                //印
//                by = by.add(total.divide(new BigDecimal(1000)));
                if( mount.divide(new BigDecimal(1000)).doubleValue() >1){
                    by = by.add(mount.divide(new BigDecimal(1000)));
                }else{
                    by = by.add(new BigDecimal(1));
                }

                //上过(1->除)
                if( total.divide(new BigDecimal(1000)).doubleValue() >1){
                    by = by.add(total.divide(new BigDecimal(1000)));
                }else{
                    by = by.add(new BigDecimal(1));
                }
//                //shen过
//                if( total.divide(new BigDecimal(1000)).doubleValue() >1){
//                    by = by.add(total.divide(new BigDecimal(1000)));
//                }else{
//                    by = by.add(new BigDecimal(1));
//                }

                if(by.subtract(last).doubleValue()>5) {
                    System.out.println("total:" + total +"jiage:" + start + "  shulian:" + j + "  fei:" + by);
                    last = by;
                }

            }
            last = new  BigDecimal(0.00);
            start = start.add(add);
        }
    }

    @Test
    public void by(){
        BigDecimal start = new BigDecimal(10.0);
        BigDecimal add = new  BigDecimal(0.05);
        BigDecimal last = new  BigDecimal(0.00);
        //价格

        for(int i = 0;i<100;i++) {
//            数量
            for(int j=0;j<100;j++){
                BigDecimal price = start.multiply( new BigDecimal(j)).multiply( new BigDecimal(100));
                BigDecimal by;
                if(price.divide(new BigDecimal(10000)).multiply(new BigDecimal(2.5)).doubleValue()>5){
                    by = price.divide(new BigDecimal(10000)).multiply(new BigDecimal(2.5));
                }else{
                    by = new BigDecimal(5);
                }

                if(by.subtract(last).doubleValue()>5) {
                    System.out.println("jiage:" + start + "  shulian:" + j + "  fei:" + by);
                    last = by;
                }

            }
            last = new  BigDecimal(0.00);
            start = start.add(add);
        }
    }
}
