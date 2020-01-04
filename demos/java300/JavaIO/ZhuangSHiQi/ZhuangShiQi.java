package JavaIO.ZhuangSHiQi;

/**
 * 说明：装饰器
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 09:41
 * @Description:
 */
public class ZhuangShiQi implements SayInterface {

    private Person person;

    public ZhuangShiQi(Person person) {
        this.person = person;
    }

    @Override
    public void say() {
        System.out.println("分贝为：" + (this.person.getVoice() * 100));
    }
}
