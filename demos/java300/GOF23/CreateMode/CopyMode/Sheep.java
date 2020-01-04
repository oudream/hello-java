package GOF23.CreateMode.CopyMode;

import java.util.Date;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 15:27
 * @Description:
 */
public class Sheep implements Cloneable{
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
        Sheep clone =(Sheep) super.clone();
        clone.setDate((Date) this.date.clone());
        return clone;
    }
}
