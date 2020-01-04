package JavaBasics.StudyJava.ObjectTest;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/29 13:36
 * @Description:
 */
public class OverrideObject extends Father {

    private String name;
    private int id;
    private String sex;

    public OverrideObject() {
    }

    public OverrideObject(String name, int id, String sex) {
        this();
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.value = "100";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString(){
        return "我是自定义toString方法";
    }

    @Override
    public boolean equals(Object object){
        if (object == this){ return true; }
        if (object == null){ return false; }
        if (object.getClass() != this.getClass()){ return false; }
        OverrideObject overrideObject = (OverrideObject) object;
        if (overrideObject.getId() != this.getId()){return false;}
        return true;
    }


    public String getFatherValue(){
        return super.value;
    }
}
