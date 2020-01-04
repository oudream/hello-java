package JavaReflection.JavaSsist;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/21 14:00
 * @Description:
 */
@Author(name = "麻尧" ,year = 1997)
public class Emp {
    private int empno;
    private String ename;

    public int getEmpno() {
        return this.empno;
    }

    public void setEmpno(int i) {
        this.empno = i;
    }

    public Emp(int empno, String ename) {
        this.empno = empno;
        this.ename = ename;
    }

    public Emp() {
    }

    public void say(String a){
        System.out.println(a);
    }
}
