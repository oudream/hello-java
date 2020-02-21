/**
 * @version 1.10 1999-09-16
 * @author Cay Horstmann
 */

import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageTransferTest
{  public static void main(String[] args)
   {  JFrame frame1 = new ImageTransferFrame();
      JFrame frame2 = new ImageTransferFrame();
      frame1.setTitle("Frame 1");
      frame2.setTitle("Frame 2");
      frame1.show();
      frame2.show();
   }
}

class ImageTransferFrame extends JFrame
   implements ActionListener
{  public ImageTransferFrame()
   {  setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      label = new JLabel();
      contentPane.add(label, "Center");

      JMenu fileMenu = new JMenu("File");
      openItem = new JMenuItem("Open");
      openItem.addActionListener(this);
      fileMenu.add(openItem);

      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);

      JMenu editMenu = new JMenu("Edit");
      copyItem = new JMenuItem("Copy");
      copyItem.addActionListener(this);
      editMenu.add(copyItem);

      pasteItem = new JMenuItem("Paste");
      pasteItem.addActionListener(this);
      editMenu.add(pasteItem);

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
            setImage(Toolkit.getDefaultToolkit().getImage(name));
         }
      }
      else if (source == exitItem) System.exit(0);
      else if (source == copyItem) copy();
      else if (source == pasteItem) paste();
   }

   private void copy()
   {  ImageSelection selection = new ImageSelection(theImage);
      localClipboard.setContents(selection, null);
   }

   private void paste()
   {  Transferable contents
         = localClipboard.getContents(this);
      if (contents == null) return;
      try
      {  Image image = (Image)contents.getTransferData
            (ImageSelection.imageFlavor);
         setImage(image);
      }
      catch(Exception e) {}
   }

   public void setImage(Image image)
   {  theImage = image;
      label.setIcon(new ImageIcon(image));
   }

   private static Clipboard localClipboard
      = new Clipboard("local");
   private Image theImage;
   private JLabel label;
   private JMenuItem openItem;
   private JMenuItem exitItem;
   private JMenuItem copyItem;
   private JMenuItem pasteItem;
}

class ImageSelection implements Transferable
{  public ImageSelection(Image image)
   {  theImage = image;
   }

   public DataFlavor[] getTransferDataFlavors()
   {  return flavors;
   }

   public boolean isDataFlavorSupported(DataFlavor flavor)
   {  return flavor.equals(imageFlavor);
   }

   public synchronized Object getTransferData
      (DataFlavor flavor)
      throws UnsupportedFlavorException
   {  if(flavor.equals(imageFlavor))
      {  return theImage;
      }
      else
      {  throw new UnsupportedFlavorException(flavor);
      }
   }

   public static final DataFlavor imageFlavor
      = new DataFlavor(java.awt.Image.class, "AWT Image");

   private static DataFlavor[] flavors = { imageFlavor };
   private Image theImage;
}
