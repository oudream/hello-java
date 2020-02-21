/**
 * @version 1.20 23 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ImagePanel extends JPanel
{  public ImagePanel()
   {  image = Toolkit.getDefaultToolkit().getImage
         ("blue-ball.gif");
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(image, 0);
      try { tracker.waitForID(0); } 
      catch (InterruptedException e) {}
   }
   
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Dimension d = getSize();
      int clientWidth = d.width;
      int clientHeight = d.height;
   
      int imageWidth = image.getWidth(this);
      int imageHeight = image.getHeight(this);
      
      g.drawImage(image, 0, 0, this);
      for (int i = 0; i * imageWidth <= clientWidth; i++)
        for (int j = 0; 
               j * imageHeight <= clientHeight; j++)
            if (i + j > 0) 
               g.copyArea(0, 0, imageWidth, imageHeight,
                   i * imageWidth, j * imageHeight);
   }
   
   private Image image;
}

class ImageFrame extends JFrame
{  public ImageFrame()
   {  setTitle("ImageTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      Container contentPane = getContentPane();
      contentPane.add(new ImagePanel());
   }
}

public class ImageTest
{  public static void main(String[] args)
   {  JFrame frame = new ImageFrame();
      frame.show();  
   }
}