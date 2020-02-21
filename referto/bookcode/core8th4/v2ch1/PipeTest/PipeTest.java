/**
 * @version 1.20 1999-04-23
 * @author Cay Horstmann
 */

import java.util.*;
import java.io.*;

public class PipeTest
{  public static void main(String args[])
   {  try
      {  /* set up pipes */
         PipedOutputStream pout1 = new PipedOutputStream();
         PipedInputStream pin1 = new PipedInputStream(pout1);

         PipedOutputStream pout2 = new PipedOutputStream();
         PipedInputStream pin2 = new PipedInputStream(pout2);

         /* construct threads */

         Producer prod = new Producer(pout1);
         Filter filt = new Filter(pin1, pout2);
         Consumer cons = new Consumer(pin2);

         /* start threads */

         prod.start();
         filt.start();
         cons.start();
      }
      catch (IOException e){}
   }
}

class Producer extends Thread
{  public Producer(OutputStream os)
   {  out = new DataOutputStream(os);
   }

   public void run()
   {  while (true)
      {  try
         {  double num = rand.nextDouble();
            out.writeDouble(num);
            out.flush();
            sleep(Math.abs(rand.nextInt() % 1000));
         }
         catch(Exception e)
         {  System.out.println("Error: " + e);
         }
      }
   }

   private DataOutputStream out;
   private Random rand = new Random();
}

class Filter extends Thread
{  public Filter(InputStream is, OutputStream os)
   {  in = new DataInputStream(is);
      out = new DataOutputStream(os);
   }

   public void run()
   {  for (;;)
      {  try
         {  double x = in.readDouble();
            total += x;
            count++;
            if (count != 0) out.writeDouble(total / count);
         }
         catch(IOException e)
         {  System.out.println("Error: " + e);
         }
      }
   }

   private DataInputStream in;
   private DataOutputStream out;
   private double total = 0;
   private int count = 0;
}

class Consumer extends Thread
{  public Consumer(InputStream is)
   {   in = new DataInputStream(is);
   }

   public void run()
   {  for(;;)
      {  try
         {  double avg = in.readDouble();
            if (Math.abs(avg - old_avg) > 0.01)
            {  System.out.println("Current average is " + avg);
               old_avg = avg;
            }
         }
         catch(IOException e)
         {  System.out.println("Error: " + e);
         }
      }
   }

   private double old_avg = 0;
   private DataInputStream in;
}
