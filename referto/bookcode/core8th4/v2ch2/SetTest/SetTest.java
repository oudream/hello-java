/**
 * @version 1.00 1999-07-07
 * @author Cay Horstmann
 */

import java.util.*;
import java.io.*;

public class SetTest
{  public static void main(String[] args)
   {  Set words = new HashSet(59999);
         // set to HashSet or TreeSet
      long totalTime = 0;

      try
      {  BufferedReader in = new
            BufferedReader(new InputStreamReader(System.in));
         String line;
         while ((line = in.readLine()) != null)
         {  StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens())
            {  String word = tokenizer.nextToken();
               long callTime = System.currentTimeMillis();
               words.add(word);
               callTime = System.currentTimeMillis() - callTime;
               totalTime += callTime;
            }
         }
      }
      catch (IOException e)
      {  System.out.println("Error " + e);
      }

      Iterator iter = words.iterator();
      while (iter.hasNext())
         System.out.println(iter.next());
      System.out.println(words.size()
         + " distinct words. " + totalTime + " milliseconds.");
   }
}
