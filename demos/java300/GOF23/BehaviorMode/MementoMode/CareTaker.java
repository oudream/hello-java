package GOF23.BehaviorMode.MementoMode;

/**
 * 说明：备忘录管理类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/30 15:36
 * @Description:
 */
public class CareTaker {

    private EmpMemento empMemento;

    public EmpMemento getEmpMemento() {
        return empMemento;
    }

    public void setEmpMemento(EmpMemento empMemento) {
        this.empMemento = empMemento;
    }
}
