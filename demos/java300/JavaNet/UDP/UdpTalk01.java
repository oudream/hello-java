package JavaNet.UDP;

import JavaIO.RandomAccessFile.Data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 说明：交流者01
 *
 * @Auther: 11432_000
 * @Date: 2019/1/14 15:32
 * @Description:
 */
public class UdpTalk01 {

    public static void main(String[] args)throws Exception {
        Thread msgThread = new Thread(new TalkReceive(65501));
        Thread inputThread = new Thread(new TalkSend(65502, "localhost", 65503) ,"麻尧");
        inputThread.join();
        msgThread.setDaemon(true);
        inputThread.start();
        msgThread.start();
    }
}
