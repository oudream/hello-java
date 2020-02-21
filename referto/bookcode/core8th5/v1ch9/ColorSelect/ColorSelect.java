/**
 * @version 1.20 17 Aug 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorSelect extends JFrame 
   implements AdjustmentListener
{  public ColorSelect()
   {  setTitle("ColorSelect");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );

      Container contentPane = getContentPane();

      JPanel p = new JPanel();
      p.setLayout(new GridLayout(3, 2));

      p.add(redLabel = new JLabel("Red 0"));
      p.add(red = new JScrollBar(Adjustable.HORIZONTAL, 
         0, 0, 0, 255));
      red.setBlockIncrement(16);
      red.addAdjustmentListener(this);

      p.add(greenLabel = new JLabel("Green 0"));
      p.add(green = new JScrollBar(Adjustable.HORIZONTAL, 
         0, 0, 0, 255));
      green.setBlockIncrement(16);
      green.addAdjustmentListener(this);

      p.add(blueLabel = new JLabel("Blue 0"));
      p.add(blue = new JScrollBar(Adjustable.HORIZONTAL, 
         0, 0, 0, 255));
      blue.setBlockIncrement(16);
      blue.addAdjustmentListener(this);

      contentPane.add(p, "South");
      
      colorPanel = new JPanel();
      colorPanel.setBackground(new Color(0, 0, 0));
      contentPane.add(colorPanel, "Center");
   }

   public void adjustmentValueChanged(AdjustmentEvent evt)
   {  redLabel.setText("Red " + red.getValue());
      greenLabel.setText("Green " + green.getValue());         
      blueLabel.setText("Blue " + blue.getValue());
      colorPanel.setBackground(new Color(red.getValue(), 
         green.getValue(), blue.getValue()));
      
      colorPanel.repaint();
   }
   
   public static void main(String[] args)
   {  Frame f = new ColorSelect();
      f.show();  
   }
   
   private JLabel redLabel;
   private JLabel greenLabel;
   private JLabel blueLabel;

   private JScrollBar red;
   private JScrollBar green;
   private JScrollBar blue;
   
   private JPanel colorPanel;
}
