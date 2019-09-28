import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuefei
 * @description 11
 * @date 2019/8/8
 */
public class Test {

    public static void main(String[] args) {
        List<String> in2  = Arrays.asList("一级分类,二级分类,三级分类,四级分类,五级分类,六级分类,七级分类,八级分类,九级分类,敏感类型,级别,  ,  ,".split(","));
        List<String> in  = new ArrayList<>();
        in2.forEach(it->{
            in.add(it);
        });



        List<String> list  = Arrays.asList("一级分类,二级分类,三级分类,四级分类,五级分类,六级分类,七级分类,八级分类,九级分类,十级分类,敏感类型,级别".split(","));
        Iterator<String> its2 = in.iterator();
        while (its2.hasNext()) {
            if(StringUtils.isEmpty(its2.next().trim())){
                its2.remove();
            }
        }
        Iterator<String> its1 = list.iterator();
        its2 = in.iterator();

        if(in.size()>=3){
            if(in.get(in.size()-2).equals("敏感类型") && in.get(in.size()-1).equals("级别")) {
                ok1:
                while (its2.hasNext()) {
                    String item2 = its2.next();
                    ok2:
                    while (its1.hasNext()) {
                        String item1 = its1.next();
                        if (item2.equals(item1)) {
                            continue ok1;
                        } else {
                            if (!its1.hasNext()) {
                                throw new RuntimeException("格式不对");
                            }
                            continue ok2;
                        }

                    }
                }

                if(!its1.hasNext() && !its2.hasNext()){

                }else{
                    throw new RuntimeException("格式不对");
                }

                System.out.print(its1.hasNext());
                System.out.print(its2.hasNext());
            }else{
                throw new RuntimeException("格式不对");
            }

        }else{
            throw new RuntimeException("格式不对");
        }


    }

}
