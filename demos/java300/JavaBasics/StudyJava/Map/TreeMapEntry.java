package JavaBasics.StudyJava.Map;

/**
 * 说明：Comparable接口（实现排序）
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 08:47
 * @Description:
 */
public class TreeMapEntry implements Comparable<TreeMapEntry>{

    private int id;
    private String name;
    private double salary;

    public TreeMapEntry(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public TreeMapEntry() {
    }

    @Override
    public int compareTo(TreeMapEntry object) {
        if (this.id > object.getId()){
            return 1;
        }else if(this.id < object.getId()){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
