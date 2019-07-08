package common.bean;


import com.alibaba.fastjson.annotation.JSONField;
import io.vertx.core.json.JsonObject;
import javabase.ramdon.RamdonStudy;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1714:20
 */
public class Women extends Person{
    private boolean sex;
    private String beauty;

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }


}
