package JavaIO.ZhuangSHiQi;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 09:39
 * @Description:
 */
public class Person implements SayInterface {
    private int voice = 10;

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    @Override
    public void say() {
        System.out.println("声音为：" + this.voice);
    }
}
