package JavaNet.UDP;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明：聊天内容
 *
 * @Auther: 11432_000
 * @Date: 2019/1/14 16:34
 * @Description:
 */
public class Message implements Serializable {

    private byte[] msg;
    private Date time;
    private String senderId;
    private String receiverId;

    public Message() {
    }

    public Message(byte[] msg, Date time, String senderId, String receiverId) {
        this.msg = msg;
        this.time = time;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public byte[] getMsg() {
        return msg;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
