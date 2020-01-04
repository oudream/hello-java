package JavaNet.UDP;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：接收端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/14 15:48
 * @Description:
 */
public class TalkReceive implements Runnable {

    private DatagramSocket datagramSocket;
    private byte[] bytes = new byte[1024 * 4];
    private String name;
    private int id;
    private ObjectInputStream objectInputStream;
    private ByteArrayInputStream byteArrayInputStream;

    public TalkReceive(int port) {
        try {
            this.datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            while(true){
                DatagramPacket datagramPacket = new DatagramPacket(bytes ,0 ,bytes.length);
                datagramSocket.receive(datagramPacket);
                byte[] data = datagramPacket.getData();
                byteArrayInputStream = new ByteArrayInputStream(data);
                objectInputStream = new ObjectInputStream(new BufferedInputStream(byteArrayInputStream));
                Object o = objectInputStream.readObject();
                if (o instanceof Message){
                    Message message = (Message) o;
                    System.out.println(message.getSenderId() + "-" + getTime(message.getTime()));
                    System.out.println(new String(message.getMsg() ,"UTF-8"));
                }
            }
        }catch (Exception e){ }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private String getTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
