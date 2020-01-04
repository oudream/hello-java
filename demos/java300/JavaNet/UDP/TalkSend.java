package JavaNet.UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * 说明：发送端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/14 15:48
 * @Description:
 */
public class TalkSend implements Runnable {

    private BufferedReader reader;
    private DatagramSocket datagramSocket;
    private int targetPort;
    private String targetIp;
    private String name;
    private int id;

    public TalkSend(int thisPort ,String targetIp ,int targetPort) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.targetPort = targetPort;
        this.targetIp = targetIp;
        try {
            this.datagramSocket = new DatagramSocket(thisPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));){
            while (true){
                String msg = reader.readLine();
                if ("bye" .equals(msg)){
                    break;
                }
                Message message = new Message(msg.getBytes("UTF-8") ,new Date() ,Thread.currentThread().getName() ,"liuzhilei");
                outputStream.writeObject(message);
                outputStream.flush();
                byte[] bytes = byteArrayOutputStream.toByteArray();
                DatagramPacket datagramPacket = new DatagramPacket(bytes ,0 ,bytes.length ,new InetSocketAddress(this.targetIp ,this.targetPort));
                datagramSocket.send(datagramPacket);
            }
            datagramSocket.close();
            reader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
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
}
