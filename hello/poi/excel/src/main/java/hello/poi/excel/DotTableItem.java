package hello.poi.excel;

// 序号	Redis STRING	Value	实际转换设备名称	设备编号	接入号	实际转换变量名称
public class DotTableItem {
    private String no;
    private String redisString;
    private Object attrValue;
    private String deviceName;
    private String deviceId;
    private String devAccessNo;
    private String attrName;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRedisString() {
        return redisString;
    }

    public void setRedisString(String redisString) {
        this.redisString = redisString;
    }

    public Object getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(Object attrValue) {
        this.attrValue = attrValue;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevAccessNo() {
        return devAccessNo;
    }

    public void setDevAccessNo(String devAccessNo) {
        this.devAccessNo = devAccessNo;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
}
