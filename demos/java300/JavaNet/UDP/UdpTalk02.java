package JavaNet.UDP;

import JavaIO.RandomAccessFile.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * 说明：交流者02
 *
 * @Auther: 11432_000
 * @Date: 2019/1/14 15:32
 * @Description:
 */
public class UdpTalk02 {

    public static void main(String[] args) throws Exception {
        Thread msgThread = new Thread(new TalkReceive(65503));
        Thread inputThread = new Thread(new TalkSend(65504, "localhost", 65501),"刘志磊");
        inputThread.join();
        msgThread.setDaemon(true);
        inputThread.start();
        msgThread.start();
    }

}
