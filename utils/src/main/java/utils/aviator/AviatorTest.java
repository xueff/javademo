package utils.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AviatorTest {

    @Test
    public void funcyion() {
        AviatorTest test = new AviatorTest();
        HashMap paramMap = new HashMap();
        paramMap.put("aviatorTest", test);
        String configInfo = "aviatorTest.a(1) && aviatorTest.b(6)";
        Expression expression = AviatorEvaluator.compile(configInfo);
        Boolean rst = (Boolean) expression.execute(paramMap);
        System.out.println(rst);//true

    }
    @Test
    public void userFunction() {

        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));
    }

    private boolean a(int o){
        System.out.print("a:"+o);
        return o>0;
    }

    private boolean b(int o){
        System.out.print("a:"+o);
        return o>5;
    }
    private boolean c(int o){
        System.out.print("a:"+o);
        return o%2==0;
    }
}

    class AddFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env,
                                  AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }
        @Override
        public String getName() {
            return "add";
        }
    }
    class CompaireFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env,
                                  AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }
        @Override
        public String getName() {
            return "add";
        }
    }
