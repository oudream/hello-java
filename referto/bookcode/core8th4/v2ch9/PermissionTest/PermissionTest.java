/**
 * @version 1.00 1999-10-23
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javax.swing.*;

public class PermissionTest
{  public static void main(String[] args)
   {  System.setSecurityManager(new SecurityManager());
      JFrame f = new PermissionTestFrame();
      f.show();
   }
}

class PermissionTestFrame extends JFrame
{  public PermissionTestFrame()
   {  setTitle("PermissionTest");
      setSize(400, 300);
      addWindowListener(
         new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         });

      textField = new JTextField(20);
      JPanel panel = new JPanel();
      panel.add(textField);
      JButton openButton = new JButton("Insert");
      panel.add(openButton);
      openButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  insertWords(textField.getText());
            }
         });

      Container contentPane = getContentPane();
      contentPane.add(panel, "North");

      textArea = new WordCheckTextArea();
      contentPane.add(new JScrollPane(textArea), "Center");
   }

   public void insertWords(String words)
   {  try
      {  textArea.append(words + "\n");
      }
      catch (SecurityException e)
      {  JOptionPane.showMessageDialog(this,
            "I am sorry, but I cannot do that.");
      }
   }

   private JTextField textField;
   private WordCheckTextArea textArea;
}

class WordCheckTextArea extends JTextArea
{  public void append(String text)
   {  WordCheckPermission p
         = new WordCheckPermission(text, "insert");
      SecurityManager manager = System.getSecurityManager();
      if (manager != null) manager.checkPermission(p);
      super.append(text);
   }
}

