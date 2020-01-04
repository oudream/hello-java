package GOF23.BehaviorMode.MementoMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 15:31
 * @Description:
 */
public class EmpMemento {

    private String name;
    private int age;
    private double salary;

    public void save(Emp emp){
        this.age = emp.getAge();
        this.name = emp.getName();
        this.salary = emp.getSalary();
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
