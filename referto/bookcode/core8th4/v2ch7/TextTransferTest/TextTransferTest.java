/**
 * @version 1.10 1999-09-16
 * @author Cay Horstmann
 */

import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;

public class TextTransferTest
{  public static void main(String[] args)
   {  JFrame frame = new TextTransferFrame();
      frame.show();
   }
}

class TextTransferFrame extends JFrame
   implements ActionListener
{  public TextTransferFrame()
   {  setTitle("TextTransferTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();

      textArea = new JTextArea();
      contentPane.add(new JScrollPane(textArea), "Center");
      JPanel panel = new JPanel();
      copyButton = new JButton("Copy");
      panel.add(copyButton);
      copyButton.addActionListener(this);
      pasteButton = new JButton("Paste");
      panel.add(pasteButton);
      pasteButton.addActionListener(this);
      contentPane.add(panel, "South");
      sysClipboard
         = Toolkit.getDefaultToolkit().getSystemClipboard();
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == copyButton) copy();
      else if (source == pasteButton) paste();
   }

   private void copy()
   {  String text = textArea.getSelectedText();
      if (text.equals("")) text = textArea.getText();
      StringSelection selection = new StringSelection(text);
      sysClipboard.setContents(selection, null);
   }

   private void paste()
   {  String text;
      Transferable contents = sysClipboard.getContents(this);
      if (contents == null) return;
      try
      {  text = (String)(contents.getTransferData
            (DataFlavor.stringFlavor));
         textArea.replaceSelection(text);
      }
      catch(Exception e)
      {  textArea.append("Error: " + e);
      }
   }

   private JTextArea textArea;
   private JButton copyButton;
   private JButton pasteButton;
   private Clipboard sysClipboard;
}
