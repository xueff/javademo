package test;

import common.bean.Person;
import io.netty.util.internal.SystemPropertyUtil;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONObject;
import javabase.ramdon.RamdonStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class Teat {
    public static void main(String[] args) {

        boolean windows = SystemPropertyUtil.get("os.name", "").toLowerCase(Locale.US).contains("win");


        String osname = "macosx8scvs".toLowerCase(Locale.US)
                .replaceAll("[0-9]+", "");
        boolean osx = osname.startsWith("macosx") || osname.startsWith("osx");

    }
}
