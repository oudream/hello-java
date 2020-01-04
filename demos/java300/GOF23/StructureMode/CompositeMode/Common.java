package GOF23.StructureMode.CompositeMode;

import java.util.List;

/**
 * 说明：抽象组件
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 13:48
 * @Description:
 */
public interface Common {
    public void say();
}
/** 叶子节点 */
interface Left extends Common {

}
/** 容器*/
interface NotLeft extends Common{
    /** 在当前节点的直接子节点中添加一个节点 */
    void add(Common child);
    /** 从当前节点的直接子节点中删除一个节点 */
    void remove(Common child);
    /** 获取所有child */
    List<Common> getChild(Common child);
    /** 获取该节点下的所有节点 */
    public List<Common> getAll();
}
