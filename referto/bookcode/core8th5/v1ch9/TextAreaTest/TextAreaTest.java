/**
 * @version 1.20 27 Jul 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TextAreaFrame extends JFrame
   implements ActionListener
{  public TextAreaFrame()
   {  JPanel p = new JPanel();

      insertButton = new JButton("Insert");
      p.add(insertButton);
      insertButton.addActionListener(this);

      wrapButton = new JButton("Wrap");
      p.add(wrapButton);
      wrapButton.addActionListener(this);

      noWrapButton = new JButton("No wrap");
      p.add(noWrapButton);
      noWrapButton.addActionListener(this);
      
      getContentPane().add(p, "South");
      
      textArea = new JTextArea(8, 40);
      scrollPane = new JScrollPane(textArea);
      getContentPane().add(scrollPane, "Center");
      
      setTitle("TextAreaTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if (source == insertButton)
         textArea.append
         ("The quick brown fox jumps over the lazy dog. ");
      else if (source == wrapButton)
      {  textArea.setLineWrap(true);
         scrollPane.validate();
      }
      else if (source == noWrapButton)
      {  textArea.setLineWrap(false);
         scrollPane.validate();
      }
   }

   private JButton insertButton;
   private JButton wrapButton;   
   private JButton noWrapButton;
   private JTextArea textArea;
   private JScrollPane scrollPane;
}

public class TextAreaTest {
   public static void main(String[] args)
   {  JFrame f = new TextAreaFrame();
      f.show();  
   }
}                                   
