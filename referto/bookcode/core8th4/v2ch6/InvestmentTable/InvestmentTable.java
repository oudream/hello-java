/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

public class InvestmentTable
{  public static void main(String[] args)
   {  JFrame frame = new InvestmentTableFrame();
      frame.show();
   }
}

/* this data model computes the cell entries each time they
   are requested
*/

class InvestmentTableModel extends AbstractTableModel
{  public InvestmentTableModel(int y, int r1, int r2)
   {  years = y;
      minRate = r1;
      maxRate = r2;
   }

   public int getRowCount()
   {  return years;
   }

   public int getColumnCount()
   {  return maxRate - minRate + 1;
   }

   public Object getValueAt(int r, int c)
   {  double rate = (c + minRate) / 100.0;
      int nperiods = r;

      double futureBalance = INITIAL_BALANCE
         * Math.pow(1 + rate, nperiods);

      return
         NumberFormat.getCurrencyInstance().format(futureBalance);
   }

   public String getColumnName(int c)
   {  double rate = (c + minRate) / 100.0;
      return
         NumberFormat.getPercentInstance().format(rate);
   }

   private int years;
   private int minRate;
   private int maxRate;

   private static double INITIAL_BALANCE = 100000.0;
}

class InvestmentTableFrame extends JFrame
{  public InvestmentTableFrame()
   {  setTitle("InvestmentTable");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );


      TableModel model = new InvestmentTableModel(30, 5, 10);
      JTable table = new JTable(model);
      Container contentPane = getContentPane();
      contentPane.add(new JScrollPane(table), "Center");
   }
}
