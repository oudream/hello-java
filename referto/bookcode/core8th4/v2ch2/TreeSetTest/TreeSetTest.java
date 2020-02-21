/**
 * @version 1.00 1999-07-07
 * @author Cay Horstmann
 */

import java.util.*;

public class TreeSetTest
{  public static void main(String[] args)
   {  SortedSet parts = new TreeSet();
      parts.add(new Item("Toaster", 1234));
      parts.add(new Item("Widget", 4562));
      parts.add(new Item("Modem", 9912));
      System.out.println(parts);

      SortedSet sortByDescription = new TreeSet(
         new Comparator()
         {  public int compare(Object a, Object b)
            {  Item itemA = (Item)a;
               Item itemB = (Item)b;
               String descrA = itemA.getDescription();
               String descrB = itemB.getDescription();
               return descrA.compareTo(descrB);
            }
         });

      sortByDescription.addAll(parts);
      System.out.println(sortByDescription);
   }
}

class Item implements Comparable
{  public Item(String aDescription, int aPartNumber)
   {  description = aDescription;
      partNumber = aPartNumber;
   }

   public String getDescription()
   {  return description;
   }

   public String toString()
   {  return "[descripion=" + description
         + ", partNumber=" + partNumber + "]";
   }

   public boolean equals(Object other)
   {  if (getClass() == other.getClass())
      {  Item otherItem = (Item)other;
         return description.equals(otherItem.description)
            && partNumber == otherItem.partNumber;
      }
      else
         return false;
   }

   public int hashCode()
   {  return 13 * description.hashCode() + 17 * partNumber;
   }

   public int compareTo(Object other)
   {  Item otherItem = (Item)other;
      return partNumber - otherItem.partNumber;
   }

   private String description;
   private int partNumber;
}

