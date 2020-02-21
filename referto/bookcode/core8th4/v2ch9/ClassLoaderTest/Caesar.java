/**
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */

import java.io.*;

public class Caesar
{  public static void main(String[] args)
   {  if (args.length != 3)
      {  System.out.println("USAGE: java Caesar in out key");
         return;
      }

      try
      {  FileInputStream in = new FileInputStream(args[0]);
         FileOutputStream out = new FileOutputStream(args[1]);
         int key = Integer.parseInt(args[2]);
         int ch;
         while ((ch = in.read()) != -1)
         {  byte c = (byte)(ch + key);
            out.write(c);
         }
         in.close();
         out.close();
      }
      catch(IOException e)
      {  System.out.println("Error: " + e);
      }
   }
}
