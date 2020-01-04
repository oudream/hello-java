package JavaIO.ByteStream.ObjectStream;

import java.io.*;
import java.lang.reflect.Field;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 15:07
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        test();
        test02();
    }

    public static void test()throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\object.txt"));
        Student student = new Student("20152176", "麻尧", "男" ,21);
        Student02 student02 = new Student02("20152176", "麻尧", "男" ,21);
//        outputStream.writeObject(student);
        outputStream.writeObject(student02);
        outputStream.flush();
        outputStream.close();
    }

    public static void test02()throws Exception{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\object.txt"));
        Object o = objectInputStream.readObject();
        if (o instanceof Student){
            System.out.println(true);
            Student o1 = (Student) o;
            Field[] fields = o1.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                System.out.println(field.getName() + "-->" + field.get(o1));
            }
        }if (o instanceof Student02){
            System.out.println(true);
            Student02 o1 = (Student02) o;
            Field[] fields = o1.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                System.out.println(field.getName() + "-->" + field.get(o1));
            }
        }
        objectInputStream.close();
    }
}
class Student implements Serializable ,Externalizable{
    private String Sno;
    private transient String Sname;
    private String Ssex;
    private int Sage;

    public Student(String sno, String sname, String ssex, int sage) {
        Sno = sno;
        Sname = sname;
        Ssex = ssex;
        Sage = sage;
    }

    public Student() {
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    @Override
    public String toString() {
        return this.Sno + "-->" + this.Sname + "-->" + this.Ssex + "-->" + this.Sage;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.Sno);
        objectOutput.writeUTF(this.Sname);
        objectOutput.writeInt(this.Sage);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.Sno = (String) objectInput.readObject();
        this.Sname = objectInput.readUTF() + "超人";
        this.Sage = objectInput.readInt() + 1000;
    }


}

class Student02 implements Serializable{
    private String Sno;
    private transient String Sname;
    private String Ssex;
    private int Sage;

    public Student02(String sno, String sname, String ssex, int sage) {
        Sno = sno;
        Sname = sname;
        Ssex = ssex;
        Sage = sage;
    }

    public Student02() {
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    @Override
    public String toString() {
        return this.Sno + "-->" + this.Sname + "-->" + this.Ssex + "-->" + this.Sage;
    }

    private void readObject(ObjectInputStream var1)throws Exception{
        this.Sno = (String) var1.readObject();
        this.Sname = var1.readUTF() + "勇者";
        this.Sage = var1.readInt();
    }

    private void writeObject(ObjectOutputStream var1) throws Exception{
        var1.writeObject(this.Sno + "sddd");
        var1.writeUTF(this.Sname);
        var1.writeInt(this.Sage);
    }
}

