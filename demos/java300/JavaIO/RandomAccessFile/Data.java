package JavaIO.RandomAccessFile;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/22 16:29
 * @Description:
 */
public class Data{

    private byte[] datas;
    private long length;

    public Data(byte[] datas, long length) {
        this.datas = datas;
        this.length = length;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
