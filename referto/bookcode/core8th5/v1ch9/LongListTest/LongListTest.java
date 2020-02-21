/**
 * @version 1.20 10 Jul 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class WordListModel extends AbstractListModel
{  public WordListModel(int n) { length = n; }
   public int getSize()
   {  return (int)Math.pow(LAST - FIRST + 1, length);
   }
   public Object getElementAt(int n)
   {  String r = "";
      for (int i = 0; i < length; i++)
      {  char c = (char)(FIRST + n % (LAST - FIRST + 1));
         r = c + r;
         n = n / (LAST - FIRST + 1);
      }
      return r;
   }

   private int length;
   public static final char FIRST = 'a';
   public static final char LAST = 'z';
}

class LongListFrame extends JFrame
   implements ListSelectionListener
{  public LongListFrame()
   {  setTitle("LongListTest");
      setSize(400, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      JList wordList = new JList(new WordListModel(3));
      wordList.setSelectionMode
         (ListSelectionModel.SINGLE_SELECTION);

      wordList.setFixedCellWidth(50);
      wordList.setFixedCellHeight(15);

      JScrollPane scrollPane = new JScrollPane(wordList);

      JPanel p = new JPanel();
      p.add(scrollPane);
      wordList.addListSelectionListener(this);

      getContentPane().add(p, "South");
      panel = new LongListPanel();
      getContentPane().add(panel, "Center");
   }

   public void  valueChanged(ListSelectionEvent evt)
   {  JList source = (JList)evt.getSource();
      String word = (String)source.getSelectedValue();
      panel.setJumper(word);
   }

   private LongListPanel panel;
}

class LongListPanel extends JPanel
{  public LongListPanel()
   {  setJumper("fox");
   }

   public void setJumper(String w)
   {  text = "The quick brown "
         + w + " jumps over the lazy dog.";
      repaint();
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.drawString(text, 0, 50);
   }

   private String text;
}

public class LongListTest
{  public static void main(String[] args)
   {  JFrame frame = new LongListFrame();
      frame.show();
   }
}

