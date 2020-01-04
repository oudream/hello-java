package GOF23.BehaviorMode.MementoMode;

/**
 * 说明：备忘录模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 15:43
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        EmpMemento empMemento = new EmpMemento();
        Emp emp = new Emp("mayao" ,19 ,0);
        careTaker.setEmpMemento(emp.save());
        emp.setAge(35);
        emp.setSalary(15000.0);

        System.out.println(emp.getName() + emp.getAge() + emp.getSalary());
        emp.reconery(careTaker.getEmpMemento());
        System.out.println(emp.getName() + emp.getAge() + emp.getSalary());

    }
}
