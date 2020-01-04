package GOF23.StructureMode.CompositeMode;

import java.util.List;

/**
 * 说明：组合模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 14:35
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        MyFolder folder = new MyFolder("TestFiles");
        MyFile myFile01 = new MyFile("a.txt");
        MyFile myFile02 = new MyFile("小说.txt");
        MyFile myFile03 = new MyFile("3012564.jpg");
        MyFile myFile04 = new MyFile("java-xxx.mp4");
        MyFile myFile05 = new MyFile("a.txt");
        MyFolder folder01 = new MyFolder("新建文件夹");

        folder.add(myFile01);
        folder.add(myFile02);
        folder.add(myFile04);
        folder.add(folder01);
        folder01.add(myFile03);
        folder01.add(myFile05);

        List<Common> child = folder.getAll();
        for (Common common : child){
            common.say();
        }

    }
}
