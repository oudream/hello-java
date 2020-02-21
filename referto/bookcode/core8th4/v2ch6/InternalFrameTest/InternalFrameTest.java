/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class InternalFrameTest
{  public static void main(String[] args)
   {  JFrame frame = new DesktopFrame();
      frame.show();
   }
}

class DesktopFrame extends JFrame
   implements ActionListener, VetoableChangeListener
{  public DesktopFrame()
   {  setTitle("InternalFrameTest");
      setSize(600, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      desktop = new JDesktopPane();
      setContentPane(desktop);

      // set up menus

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("File");
      menuBar.add(fileMenu);
      openItem = new JMenuItem("Open");
      openItem.addActionListener(this);
      fileMenu.add(openItem);
      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);
      JMenu windowMenu = new JMenu("Window");
      menuBar.add(windowMenu);
      nextItem = new JMenuItem("Next");
      nextItem.addActionListener(this);
      windowMenu.add(nextItem);
      cascadeItem = new JMenuItem("Cascade");
      cascadeItem.addActionListener(this);
      windowMenu.add(cascadeItem);
      tileItem = new JMenuItem("Tile");
      tileItem.addActionListener(this);
      windowMenu.add(tileItem);
      dragOutlineItem = new JCheckBoxMenuItem("Drag Outline");
      dragOutlineItem.addActionListener(this);
      windowMenu.add(dragOutlineItem);
   }

   public void createInternalFrame(Component c, String t)
   {  JInternalFrame iframe = new JInternalFrame(t,
         true, // resizable
         true, // closable
         true, // maximizable
         true); // iconifiable

      iframe.getContentPane().add(c);
      desktop.add(iframe);

      iframe.setFrameIcon(new ImageIcon("document.gif"));

      // add listener to confirm frame closing
      iframe.addVetoableChangeListener(this);

      // position frame
      int width = desktop.getWidth() / 2;
      int height = desktop.getHeight() / 2;
      iframe.reshape(nextFrameX, nextFrameY, width, height);

      iframe.show();

      // select the frame--might be vetoed
      try
      {  iframe.setSelected(true);
      }
      catch(PropertyVetoException e)
      {}

      /* if this is the first time, compute distance between
         cascaded frames
      */

      if (frameDistance == 0)
         frameDistance = iframe.getHeight()
            - iframe.getContentPane().getHeight();

      // compute placement for next frame

      nextFrameX += frameDistance;
      nextFrameY += frameDistance;
      if (nextFrameX + width > desktop.getWidth())
         nextFrameX = 0;
      if (nextFrameY + height > desktop.getHeight())
         nextFrameY = 0;
   }

   public void cascadeWindows()
   {  JInternalFrame[] frames = desktop.getAllFrames();
      int x = 0;
      int y = 0;
      int width = desktop.getWidth() / 2;
      int height = desktop.getHeight() / 2;

      for (int i = 0; i < frames.length; i++)
      {  if (!frames[i].isIcon())
         {  try
            {  /* try to make maximized frames resizable
                  this might be vetoed
               */
               frames[i].setMaximum(false);
               frames[i].reshape(x, y, width, height);

               x += frameDistance;
               y += frameDistance;
               // wrap around at the desktop edge
               if (x + width > desktop.getWidth()) x = 0;
               if (y + height > desktop.getHeight()) y = 0;
            }
            catch(PropertyVetoException e)
            {}
         }
      }
   }

   public void tileWindows()
   {  JInternalFrame[] frames = desktop.getAllFrames();

      // count frames that aren't iconized
      int frameCount = 0;
      for (int i = 0; i < frames.length; i++)
      {  if (!frames[i].isIcon())
            frameCount++;
      }

      int rows = (int)Math.sqrt(frameCount);
      int cols = frameCount / rows;
      int extra = frameCount % rows;
         // number of columns with an extra row

      int width = desktop.getWidth() / cols;
      int height = desktop.getHeight() / rows;
      int r = 0;
      int c = 0;
      for (int i = 0; i < frames.length; i++)
      {  if (!frames[i].isIcon())
         {  try
            {  frames[i].setMaximum(false);
               frames[i].reshape(c * width,
                  r * height, width, height);
               r++;
               if (r == rows)
               {  r = 0;
                  c++;
                  if (c == cols - extra)
                  {  // start adding an extra row
                     rows++;
                     height = desktop.getHeight() / rows;
                  }
               }
            }
            catch(PropertyVetoException e)
            {}
         }
      }
   }

   public void selectNextWindow()
   {  JInternalFrame[] frames = desktop.getAllFrames();
      for (int i = 0; i < frames.length; i++)
      {  if (frames[i].isSelected())
         {  /* find next frame that isn't an icon and can be
               selected
            */
            try
            {  int next = i + 1;
               while (next != i && frames[next].isIcon())
                  next++;
               if (next == i) return;
                  // all other frames are icons or veto selection
               frames[next].setSelected(true);
               frames[next].toFront();
               return;
            }
            catch(PropertyVetoException e)
            {}
         }
      }
   }

   public void vetoableChange(PropertyChangeEvent event)
      throws PropertyVetoException
   {  JInternalFrame iframe = (JInternalFrame)event.getSource();
      String name = event.getPropertyName();
      Object value = event.getNewValue();

      // we only want to check attempts to close a frame

      if (name.equals("closed") && value.equals(Boolean.TRUE))
      {  // ask user if it is ok to close
         int result
            = JOptionPane.showInternalConfirmDialog(iframe,
               "OK to close?");

         // if the user doesn't agree, veto the close
         if (result == JOptionPane.CANCEL_OPTION)
            throw new PropertyVetoException("User canceled close",
               event);
      }
   }

   public Component createEditorPane(URL u)
   {  // create an editor pane that follows hyperlink clicks

      JEditorPane editorPane = new JEditorPane();
      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(new HyperlinkListener()
         {  public void hyperlinkUpdate(HyperlinkEvent event)
            {  createInternalFrame(createEditorPane(event.getURL()),
                  event.getURL().toString());
            }
         });
      try
      {  editorPane.setPage(u);
      }
      catch(IOException e)
      {  editorPane.setText("Error: " + e);
      }
      return new JScrollPane(editorPane);
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if (source == openItem)
      {  // let user select file

         JFileChooser chooser = new JFileChooser();
         chooser.setCurrentDirectory(new File("."));
         chooser.setFileFilter(
            new javax.swing.filechooser.FileFilter()
            {  public boolean accept(File f)
               {  String fname = f.getName().toLowerCase();
                  return fname.endsWith(".html")
                     || fname.endsWith(".htm")
                     || f.isDirectory();
               }
               public String getDescription()
               { return "HTML Files"; }
            });
         int r = chooser.showOpenDialog(this);

         if (r == JFileChooser.APPROVE_OPTION)
         {  // open the file that the user selected

            String filename = chooser.getSelectedFile().getPath();
            try
            {  URL fileUrl = new URL("file:" + filename);
               createInternalFrame(createEditorPane(fileUrl),
                  filename);
            }
            catch(MalformedURLException e)
            {
            }
         }
      }
      else if (source == exitItem)
         System.exit(0);
      else if (source == nextItem)
         selectNextWindow();
      else if (source == cascadeItem)
         cascadeWindows();
      else if (source == tileItem)
         tileWindows();
      else if (source == dragOutlineItem)
      {  desktop.putClientProperty("JDesktopPane.dragMode",
            dragOutlineItem.isSelected() ? "outline" : null);
      }
   }

   private JDesktopPane desktop;

   private JMenuItem openItem;
   private JMenuItem exitItem;
   private JMenuItem nextItem;
   private JMenuItem cascadeItem;
   private JMenuItem tileItem;
   private JMenuItem dragOutlineItem;

   private int nextFrameX;
   private int nextFrameY;
   private int frameDistance;
}