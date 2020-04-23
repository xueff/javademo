package common.bean;


import com.alibaba.fastjson.annotation.JSONField;
import javabase.ramdon.RamdonStudy;
import lombok.Data;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1714:20
 */
@Data
public class Person {
    @JSONField(name="userName")
    private String name;
    @JSONField(name="Age")
    private int age;
    private int id;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    private Double money;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
        this.name = RamdonStudy.getRandomString(RamdonStudy.getRamdonInt(62));
        this.age = RamdonStudy.getRamdonInt(150);
        this.money = RamdonStudy.getRamdonDouble();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
