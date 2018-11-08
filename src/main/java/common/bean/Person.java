package common.bean;


import javabase.ramdon.RamdonStudy;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1714:20
 */
public class Person {
    private String name;
    private int age;

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
}
