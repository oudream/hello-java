package GOF23.StructureMode.CompositeMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：文件夹
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 14:15
 * @Description:
 */
public class MyFolder implements NotLeft{

    private String name;
    private List<Common> list = new ArrayList<>();

    public MyFolder(String name) {
        this.name = name;
    }

    @Override
    public void say() {
        System.out.println("我是文件夹-->" + this.name);
    }

    @Override
    public void add(Common child) {
        list.add(child);
    }

    @Override
    public void remove(Common child) {
        list.remove(child);
    }

    @Override
    public List<Common>  getChild(Common child) {
        List<Common> have = new ArrayList<>();
        for (Common common : list){
            if (common instanceof NotLeft){
                List<Common> child01 = ((NotLeft) common).getChild(child);
                if (child01 != null && child01.size() > 0){
                    have.addAll(child01);
                }
            }
            if (child.equals(common)){
                have.add(common);
            }
        }
        return have;
    }

    @Override
    public List<Common> getAll() {
        List<Common> have = new ArrayList<>();
        for (Common common : list){
            if (common instanceof NotLeft){
                List<Common> child01 = ((NotLeft) common).getAll();
                have.addAll(child01);
            }
            have.add(common);
        }
        return have;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof MyFolder)){return false;}
        if (object == this){return true;}
        if (this.name.equals(((MyFolder)object).getName())){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/** 文件 */
class MyFile implements Left{

    private String name;

    public MyFile(String name) {
        this.name = name;
    }

    @Override
    public void say() {
        System.out.println("我是文件-->" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof MyFile)){return false;}
        if (object == this){return true;}
        if (this.name.equals(((MyFile)object).getName())){
            return true;
        }
        return false;
    }

}