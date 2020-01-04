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
public class Sheep03 implements Serializable{
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
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(byteArrayInputStream));
            Object object = inputStream.readObject();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
