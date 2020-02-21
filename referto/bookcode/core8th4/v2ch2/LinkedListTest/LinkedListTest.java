/**
 * @version 1.00 1999-07-07
 * @author Cay Horstmann
 */

import java.util.*;

public class LinkedListTest
{  public static void main(String[] args)
   {  List a = new LinkedList();
      a.add("Angela");
      a.add("Carl");
      a.add("Erica");

      List b = new LinkedList();
      b.add("Bob");
      b.add("Doug");
      b.add("Frances");
      b.add("Gloria");

      // merge the words from b into a

      ListIterator aIter = a.listIterator();
      Iterator bIter = b.iterator();

      while (bIter.hasNext())
      {  if (aIter.hasNext()) aIter.next();
         aIter.add(bIter.next());
      }

      System.out.println(a);

      // remove every second word from b

      bIter = b.iterator();
      while (bIter.hasNext())
      {  bIter.next(); // skip one element
         if (bIter.hasNext())
         {  bIter.next(); // skip next element
            bIter.remove(); // remove that element
         }
      }

      System.out.println(b);

      // bulk operation: remove all words in b from a

      a.removeAll(b);

      System.out.println(a);
   }
}
