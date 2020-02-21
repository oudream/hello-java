/**
 * @version 1.10 1999-10-23
 * @author Cay Horstmann
 */

import java.io.*;
import java.security.*;

public class WordCheckSecurityManager extends SecurityManager
{  public void checkPermission(Permission p)
   {  if (p instanceof FilePermission
         && p.getActions().equals("read"))
      {  if (inSameManager())
            return;
         String fileName = p.getName();
         if (containsBadWords(fileName))
            throw new SecurityException("Bad words in "
               + fileName);
      }
      else super.checkPermission(p);
   }

   boolean inSameManager()
   {  Class[] cc = getClassContext();

      // skip past current set of calls to this manager
      int i = 0;
      while (i < cc.length && cc[0] == cc[i])
         i++;

      // check if there is another call to this manager
      while (i < cc.length)
      {  if (cc[0] == cc[i]) return true;
         i++;
      }
      return false;
   }

   boolean containsBadWords(String fileName)
   {  if (!fileName.toLowerCase().endsWith(".txt")) return false;
         // only check text files
      BufferedReader in = null;
      try
      {  in = new BufferedReader(new FileReader(fileName));
         String s;
         while ((s = in.readLine()) != null)
         {  for (int i = 0; i < badWords.length; i++)
            if (s.toLowerCase().indexOf(badWords[i]) != -1)
               return true;
         }
         in.close();
         return false;
      }
      catch(IOException e)
      {  return true;
      }
      finally
      {  if (in != null)
            try { in.close(); } catch (IOException e) {}
      }
   }

   private String[] badWords = { "sex", "drugs", "c++" };
}
