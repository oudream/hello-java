package GOF23.CreateMode.CopyMode;

import java.io.*;
import java.util.Date;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 15:27
 * @Description:
 */
public class Sheep02 implements Serializable{
    private String name;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(
                    new FileOutputStream("D:\\idea-file\\javaDemo02\\src\\GOF23\\CopyMode\\a.txt")));
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();
            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream("D:\\idea-file\\javaDemo02\\src\\GOF23\\CopyMode\\a.txt")));
            Sheep02 object =(Sheep02) inputStream.readObject();
            inputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
