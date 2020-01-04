package JavaNet.UDP;

import java.io.Serializable;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/14 15:31
 * @Description:
 */
public class Man implements Serializable {
    String name;
    String sex;
    transient int age;

    public Man(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
