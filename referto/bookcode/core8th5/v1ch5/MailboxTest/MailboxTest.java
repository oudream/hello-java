/**
 * @version 1.20 07 Apr 1998
 * @author Cay Horstmann
 */

import java.net.*;
import java.applet.*;
import corejava.*;

public class MailboxTest
{  public static void main(String[] args)
   {  Mailbox mbox = new Mailbox();
      while (true)
      {  System.out.println(mbox.status());
         String cmd = Console.readLine
               ("play, text, voice, quit> ");
         if (cmd.equals("play"))
         {  Message m = mbox.remove();
            if (m != null)
            {  System.out.println("From: " + m.getSender());
               m.play();
            }
         }
         else if (cmd.equals("text"))
         {  String from = Console.readLine("Your name: ");
            boolean more = true;
            String msg = "";
            System.out.println
               ("Enter message, 'exit' when done");
             
            while (more)
            {  String line = Console.readLine();
               if (line.equals("exit"))
                  more = false;
               else msg = msg + line + "\n";
            }
            mbox.insert(new TextMessage(from, msg));
         }
         else if (cmd.equals("voice"))
         {  String from = Console.readLine("Your name: ");
            String msg
               = Console.readLine("Audio file name: ");         
            mbox.insert(new VoiceMessage(from, msg));
         }
         else if (cmd.equals("quit"))
            System.exit(0);
      }            
   }
}
               

abstract class Message
{  public Message(String from) { sender = from; }

   public abstract void play();
   public String getSender() { return sender; }
   
   private String sender;
}

class TextMessage extends Message
{  public TextMessage(String from, String t)
   { super(from); text = t; }

   public void play() { System.out.println(text); }
   
   private String text;
}

class VoiceMessage extends Message
{  public VoiceMessage(String from, String f)
   { super(from); filename = f; }
   
   public void play()
   {  try
      {  URL u = new URL("file", "localhost", filename);
         AudioClip clip = Applet.newAudioClip(u);
         clip.play();
      }
      catch(Exception e)
      {
         System.out.println("Can't open " + filename);
      }
   }   

   private String filename;
}


class Mailbox
{  public Message remove()
   {  if (nmsg == 0) return null;
      Message r = messages[out];
      nmsg--;
      out = (out + 1) % MAXMSG;
      return r;
   }
   
   public void insert(Message m)
   {  if (nmsg == MAXMSG) return;
      messages[in] = m;
      nmsg++;
      in = (in + 1) % MAXMSG;
   }
   
   public String status() 
   {  if (nmsg == 0) return "Mailbox empty";
      else if (nmsg == 1) return "1 message";
      else if (nmsg < MAXMSG) return nmsg + " messages";
      else return "Mailbox full";
   }
   
   private final int MAXMSG = 10;
   private int in = 0;
   private int out = 0;
   private int nmsg = 0;
   private Message[] messages = new Message[MAXMSG];
}


