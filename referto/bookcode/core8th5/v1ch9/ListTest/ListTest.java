/**
 * @version 1.20 27 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class ListFrame extends JFrame
   implements ListSelectionListener
{  public ListFrame()
   {  setTitle("ListTest");
      setSize(400,300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      String[] words =
      {  "quick","brown","hungry","wild","silent",
         "huge","private","abstract","static","final"
      };

      JList wordList = new JList(words);
      JScrollPane scrollPane = new JScrollPane(wordList);

      JPanel p = new JPanel();
      p.add(scrollPane);
      wordList.addListSelectionListener(this);

      getContentPane().add(p, "South");
      panel = new ListTestPanel();
      getContentPane().add(panel, "Center");
   }

   public void valueChanged(ListSelectionEvent evt)
   {  JList source = (JList)evt.getSource();
      Object[] values = source.getSelectedValues();

      String text = "";
      for (int i = 0; i < values.length; i++)
      {  String word = (String)values[i];
         text += word + " ";
      }

      panel.setAttribute(text);
   }

   private ListTestPanel panel;
}

class ListTestPanel extends JPanel
{  public ListTestPanel()
   {  setAttribute("");
   }

   public void setAttribute(String w)
   {  text = "The " + w + "fox jumps over the lazy dog.";
      repaint();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.drawString(text, 0, 50);
   }

   private String text;
}

public class ListTest
{  public static void main(String[] args)
   {  JFrame frame = new ListFrame();
      frame.show();
   }
}

