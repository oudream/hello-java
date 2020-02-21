/**
 * @version 1.00 05 Sep 1997
 * @author Gary Cornell
 */

import java.io.*;

public class FindDirectories
{  public static void main(String[] args)
   {  if (args.length == 0) args = new String[] { ".." };

      try
      {  File pathName = new File(args[0]);
         String[] fileNames = pathName.list();

         for (int i = 0; i<fileNames.length; i++)
         {  File tf = new File(pathName.getPath(), 
               fileNames[i]);
            if (tf.isDirectory())
            {  System.out.println(tf.getCanonicalPath());
               main(new String [] { tf.getPath() });
            }
         }
      }
      catch(IOException e)
      {  System.out.println("Error: " + e);
      }
   }
}


