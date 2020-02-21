/**
 * @version 1.20 1999-07-07
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class CustomWorld
{  public static void main(String[] args)
   {  JFrame frame = new CustomWorldFrame();
      frame.show();
   }
}

class CustomWorldFrame extends JFrame
{  public CustomWorldFrame()
   {  addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Properties defaultSettings = new Properties();
      defaultSettings.put("FONT", "Monospaced");
      defaultSettings.put("SIZE", "300 200");
      defaultSettings.put("MESSAGE", "Hello, World");
      defaultSettings.put("COLOR", "0 50 50");
      defaultSettings.put("PTSIZE", "12");

      Properties settings = new Properties(defaultSettings);
      try
      {  FileInputStream sf
            = new FileInputStream("CustomWorld.ini");
         settings.load(sf);
      }
      catch (FileNotFoundException e) {}
      catch (IOException e) {}

      StringTokenizer st = new StringTokenizer
         (settings.getProperty("COLOR"));
      int red = Integer.parseInt(st.nextToken());
      int green = Integer.parseInt(st.nextToken());
      int blue = Integer.parseInt(st.nextToken());

      Color foreground = new Color(red, green, blue);

      String name = settings.getProperty("FONT");
      int size = Integer.parseInt(settings.getProperty("PTSIZE"));
      Font f = new Font(name, Font.BOLD, size);

      st = new StringTokenizer(settings.getProperty("SIZE"));
      int hsize = Integer.parseInt(st.nextToken());
      int vsize = Integer.parseInt(st.nextToken());
      setSize(hsize, vsize);
      setTitle(settings.getProperty("MESSAGE"));

      getContentPane().add(new HelloWorldPanel(getTitle(),
         foreground, f), "Center");
   }
}

class HelloWorldPanel extends JPanel
{  public HelloWorldPanel(String aMessage, Color aForeground,
      Font aFont)
   {  message = aMessage;
      foreground = aForeground;
      font = aFont;
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.setColor(foreground);
      g.setFont(font);

      FontMetrics fm = g.getFontMetrics(font);
      int w = fm.stringWidth(message);

      Dimension d = getSize();
      int cx = (d.width - w) / 2;
      int cy = (d.height + fm.getHeight()) / 2 - fm.getDescent();

      g.drawString(message, cx, cy);
   }

   private Color foreground;
   private Font font;
   private String message;
}