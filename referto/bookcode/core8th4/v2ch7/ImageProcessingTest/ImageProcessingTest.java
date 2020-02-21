/**
 * @version 1.0 1999-09-12
 * @author Cay Horstmann
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class ImageProcessingTest
{  public static void main(String[] args)
   {  JFrame frame = new ImageProcessingFrame();
      frame.show();
   }
}

class ImageProcessingFrame extends JFrame
   implements ActionListener
{  public ImageProcessingFrame()
   {  setTitle("ImageProcessingTest");
      setSize(300, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      panel = new ImageProcessingPanel();
      contentPane.add(panel, "Center");

      JMenu fileMenu = new JMenu("File");
      openItem = new JMenuItem("Open");
      openItem.addActionListener(this);
      fileMenu.add(openItem);

      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);

      JMenu editMenu = new JMenu("Edit");
      blurItem = new JMenuItem("Blur");
      blurItem.addActionListener(this);
      editMenu.add(blurItem);

      sharpenItem = new JMenuItem("Sharpen");
      sharpenItem.addActionListener(this);
      editMenu.add(sharpenItem);

      brightenItem = new JMenuItem("Brighten");
      brightenItem.addActionListener(this);
      editMenu.add(brightenItem);

      edgeDetectItem = new JMenuItem("Edge detect");
      edgeDetectItem.addActionListener(this);
      editMenu.add(edgeDetectItem);

      negativeItem = new JMenuItem("Negative");
      negativeItem.addActionListener(this);
      editMenu.add(negativeItem);

      rotateItem = new JMenuItem("Rotate");
      rotateItem.addActionListener(this);
      editMenu.add(rotateItem);

      JMenuBar menuBar = new JMenuBar();
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      setJMenuBar(menuBar);
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if (source == openItem)
      {  JFileChooser chooser = new JFileChooser();
         chooser.setCurrentDirectory(new File("."));

         chooser.setFileFilter(new
            javax.swing.filechooser.FileFilter()
            {  public boolean accept(File f)
               {  String name = f.getName().toLowerCase();
                  return name.endsWith(".gif")
                     || name.endsWith(".jpg")
                     || name.endsWith(".jpeg")
                     || f.isDirectory();
               }
               public String getDescription()
               {  return "Image files";
               }
            });

         int r = chooser.showOpenDialog(this);
         if(r == JFileChooser.APPROVE_OPTION)
         {  String name
               = chooser.getSelectedFile().getAbsolutePath();
            panel.loadImage(name);
         }
      }
      else if (source == exitItem) System.exit(0);
      else if (source == blurItem) panel.blur();
      else if (source == sharpenItem) panel.sharpen();
      else if (source == brightenItem) panel.brighten();
      else if (source == edgeDetectItem) panel.edgeDetect();
      else if (source == negativeItem) panel.negative();
      else if (source == rotateItem) panel.rotate();
   }

   private ImageProcessingPanel panel;
   private JMenuItem openItem;
   private JMenuItem exitItem;
   private JMenuItem blurItem;
   private JMenuItem sharpenItem;
   private JMenuItem brightenItem;
   private JMenuItem edgeDetectItem;
   private JMenuItem negativeItem;
   private JMenuItem rotateItem;
}

class ImageProcessingPanel extends JPanel
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      if (image != null)
         g.drawImage(image, 0, 0, null);
   }

   public void loadImage(String name)
   {  Image loadedImage
         = Toolkit.getDefaultToolkit().getImage(name);
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(loadedImage, 0);
      try { tracker.waitForID(0); }
      catch (InterruptedException e) {}
      image = new BufferedImage(loadedImage.getWidth(null),
         loadedImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = image.createGraphics();
      g2.drawImage(loadedImage, 0, 0, null);

      repaint();
   }

   private void filter(BufferedImageOp op)
   {  BufferedImage filteredImage
         = new BufferedImage(image.getWidth(), image.getHeight(),
            image.getType());
      op.filter(image, filteredImage);
      image = filteredImage;
      repaint();
   }

   private void convolve(float[] elements)
   {  Kernel kernel = new Kernel(3, 3, elements);
      ConvolveOp op = new ConvolveOp(kernel);
      filter(op);
   }

   public void blur()
   {  float weight = 1.0f/9.0f;
      float[] elements = new float[9];
      for (int i = 0; i < 9; i++)
         elements[i] = weight;
      convolve(elements);
   }

   public void sharpen()
   {  float[] elements =
         {  0.0f, -1.0f, 0.0f,
            -1.0f,  5.f, -1.0f,
            0.0f, -1.0f, 0.0f
         };
      convolve(elements);
   }

   void edgeDetect()
   {  float[] elements =
         {  0.0f, -1.0f, 0.0f,
            -1.0f,  4.f, -1.0f,
            0.0f, -1.0f, 0.0f
         };
      convolve(elements);
   }

   public void brighten()
   {  float a = 1.5f;
      float b = -20.0f;
      RescaleOp op = new RescaleOp(a, b, null);
      filter(op);
   }

   void negative()
   {  byte negative[] = new byte[256];
      for (int i = 0; i < 256; i++)
         negative[i] = (byte)(255 - i);
      ByteLookupTable table = new ByteLookupTable(0, negative);
      LookupOp op = new LookupOp(table, null);
      filter(op);
   }

   void rotate()
   {  AffineTransform transform
         = AffineTransform.getRotateInstance(Math.toRadians(5),
            image.getWidth() / 2, image.getHeight() / 2);
      AffineTransformOp op = new AffineTransformOp(transform,
         AffineTransformOp.TYPE_BILINEAR);
      filter(op);
   }

   private BufferedImage image;
}
