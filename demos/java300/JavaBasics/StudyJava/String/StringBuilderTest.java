package JavaBasics.StudyJava.String;

/**
 * 说明：StringBuilder类
 *
 * @Auther: 11432_000
 * @Date: 2018/12/11 16:10
 * @Description:
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        long l = Runtime.getRuntime().freeMemory();
        StringBuilder stringBuilder = new StringBuilder("mayao");
        //尾部添加
        stringBuilder.append(2018);
        //倒序
        stringBuilder.reverse();
        //修改某一个字符
        stringBuilder.setCharAt(4,'h');
        //插入(多重载),支持链式调用，因为返回this
        stringBuilder.insert(0,"woshi");
        //删除,支持链式调用
        stringBuilder.delete(1,2).delete(6,5);
        //替换两个索引间的值
        stringBuilder.replace(0,1,"hhh");
    }
}
