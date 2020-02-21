/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class FontCellRenderer implements ListCellRenderer
{  public Component getListCellRendererComponent
      (final JList list, final Object value,
      final int index, final boolean isSelected,
      final boolean cellHasFocus)
   {  return new JPanel()
         {  public void paintComponent(Graphics g)
            {  Font font = (Font)value;
               String text = font.getFamily();
               FontMetrics fm = g.getFontMetrics(font);
               g.setColor(isSelected
                  ? list.getSelectionBackground()
                  : list.getBackground());
               g.fillRect(0, 0, getWidth(), getHeight());
               g.setColor(isSelected
                  ? list.getSelectionForeground()
                  : list.getForeground());
               g.setFont(font);
               g.drawString(text, 0, fm.getAscent());
            }

            public Dimension getPreferredSize()
            {  Font font = (Font)value;
               String text = font.getFamily();
               Graphics g = getGraphics();
               FontMetrics fm = g.getFontMetrics(font);
               return new Dimension(fm.stringWidth(text),
                  fm.getHeight());
            }
         };
   }
}

class ListRenderingFrame extends JFrame
   implements ListSelectionListener
{  public ListRenderingFrame()
   {  setTitle("ListRenderingTest");
      setSize(400, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Vector fonts = new Vector();
      fonts.add(new Font("Serif", Font.PLAIN, 12));
      fonts.add(new Font("SansSerif", Font.PLAIN, 12));
      fonts.add(new Font("Monospaced", Font.PLAIN, 12));
      fonts.add(new Font("Dialog", Font.PLAIN, 12));
      fonts.add(new Font("DialogInput", Font.PLAIN, 12));
      JList fontList = new JList(fonts);
      fontList.setSelectionMode
         (ListSelectionModel.SINGLE_SELECTION);
      fontList.setCellRenderer(new FontCellRenderer());
      JScrollPane scrollPane = new JScrollPane(fontList);

      JPanel p = new JPanel();
      p.add(scrollPane);
      fontList.addListSelectionListener(this);

      getContentPane().add(p, "South");
      panel = new ListRenderingPanel();
      getContentPane().add(panel, "Center");
   }

   public void  valueChanged(ListSelectionEvent evt)
   {  JList source = (JList)evt.getSource();
      Font font = (Font)source.getSelectedValue();
      panel.setFont(font);
   }

   private ListRenderingPanel panel;
}

class ListRenderingPanel extends JPanel
{  public ListRenderingPanel()
   {  setFont(new Font("Serif", Font.PLAIN, 12));
   }

   public void setFont(Font f)
   {  currentFont = f;
      repaint();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.setFont(currentFont);
      g.drawString
         ("The quick brown fox jumps over the lazy dog",
         0, 50);
   }

   private Font currentFont;
}

public class ListRenderingTest
{  public static void main(String[] args)
   {  JFrame frame = new ListRenderingFrame();
      frame.show();
   }
}