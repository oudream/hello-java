package hello.java.lang.annotation.java300;

import java.util.Map;

/**
 * 说明：实体类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/18 14:49
 * @Description:
 */
@SxtTable("tb_student")
public class SxtStudent {

    @SxtField(columName = "id",type = "int",length = 10)
    private int id;
    @SxtField(columName = "sname",type = "varchar",length = 10)
    private String name;
    @SxtField(columName = "age",type = "int",length = 3)
    private int age;

    public SxtStudent(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public SxtStudent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void show(Map<Integer ,String> map){}
}
