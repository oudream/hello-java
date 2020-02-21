/**
 * @version 1.20 07 Apr 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.util.*;

public class TileTest
{  public static void main(String[] args)
   {  Tile[] a = new Tile[20];

      int i;
      for (i = 0; i < a.length; i++) 
         a[i] = new Tile(i, i, 10, 20, 
            (int)(100 * Math.random()));
              
      Arrays.sort(a);
      
      for (i = 0; i < a.length; i++) 
         System.out.println(a[i]);
   }
}

class Tile extends Rectangle implements Comparable
{  public Tile(int x, int y, int w, int h, int zz)
   {  super(x, y, w, h);
      z = zz;
   }
   
   public int compareTo(Object b)
   {  Tile tb = (Tile)b;
      return z - tb.z;
   }
   
   public String toString()
   {  return super.toString() + "[z=" + z + "]";
   }

   private int z;
}


