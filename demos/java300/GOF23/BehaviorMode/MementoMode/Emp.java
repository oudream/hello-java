package GOF23.BehaviorMode.MementoMode;

/**
 * 说明：源类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 15:29
 * @Description:
 */
public class Emp {
    private String name;
    private int age;
    private double salary;

    public Emp(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Emp() {
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

    public EmpMemento save(){
        EmpMemento empMemento = new EmpMemento();
        empMemento.save(this);
        return empMemento;
    }

    public void reconery(EmpMemento empMemento){
        this.age = empMemento.getAge();
        this.name = empMemento.getName();
        this.salary = empMemento.getSalary();
    }
}
