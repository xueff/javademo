package exception;

import java.io.IOException;

public class ExceptionUtils {

    public static String exception(Exception e){

        if(e instanceof RuntimeException){
            return e.getMessage();
        }else if(e instanceof IOException){

        }
        return "";

    }
}
