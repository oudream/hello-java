/**
 * @version 1.10 1999-10-23
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class SecurityManagerTest
{  public static void main(String[] args)
   {  System.setSecurityManager(new WordCheckSecurityManager());
      JFrame f = new SecurityManagerFrame();
      f.show();
   }
}

class SecurityManagerFrame extends JFrame
{  public SecurityManagerFrame()
   {  setTitle("SecurityManagerTest");
      setSize(400, 300);
      addWindowListener(
         new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         });

      fileNameField = new JTextField(20);
      JPanel panel = new JPanel();
      panel.add(new JLabel("Text file:"));
      panel.add(fileNameField);
      JButton openButton = new JButton("Open");
      panel.add(openButton);
      openButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  loadFile(fileNameField.getText());
            }
         });

      Container contentPane = getContentPane();
      contentPane.add(panel, "North");

      fileText = new JTextArea();
      contentPane.add(new JScrollPane(fileText), "Center");
   }

   public void loadFile(String filename)
   {  try
      {  fileText.setText("");
         BufferedReader in
            = new BufferedReader(new FileReader(filename));
         String s;
         while ((s = in.readLine()) != null)
         fileText.append(s + "\n");
         in.close();
      }
      catch (IOException e)
      {  fileText.append(e + "\n");
      }
      catch (SecurityException e)
      {  fileText.append("I am sorry, but I cannot do that.");
      }
   }

   private JTextField fileNameField;
   private JTextArea fileText;
}

