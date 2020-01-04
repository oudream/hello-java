package GOF23.BehaviorMode.IteratorMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/28 14:24
 * @Description:
 */
public interface MyIterator {
    /** 判断是否还有下一个 */
    boolean hasNext();
    /** 获取当前元素并指向下一个元素 */
    Object next();
}
