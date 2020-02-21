/**
 * version 1.00 1999-08-27
 * author Cay Horstmann
 */

import java.io.*;
import java.net.*;

public class SocketOpenerTest
{  public static void main(String[] args)
   {  String host;
      if (args.length > 0) host = args[0];
      else host = "www.yourcompany.com";

      int port;
      if (args.length > 1) port = Integer.parseInt(args[1]);
      else port = 80;

      int timeout = 5000;
      Socket s = SocketOpener.openSocket(host, port, timeout);

      if (s == null)
         System.out.println("The socket could not be opened.");
      else
         System.out.println(s);
   }
}

class SocketOpener implements Runnable
{  public static Socket openSocket(String aHost, int aPort,
      int timeout)
   {  SocketOpener opener = new SocketOpener(aHost, aPort);
      Thread t = new Thread(opener);
      t.start();
      try
      {  t.join(timeout);
      }
      catch (InterruptedException exception)
      {
      }
      return opener.getSocket();
   }

   public SocketOpener(String aHost, int aPort)
   {  socket = null;
      host = aHost;
      port = aPort;
   }

   public void run()
   {  try
      {  socket = new Socket(host, port);
      }
      catch (IOException exception)
      {
      }
   }

   public Socket getSocket()
   {  return socket;
   }

   private String host;
   private int port;
   private Socket socket;
};
