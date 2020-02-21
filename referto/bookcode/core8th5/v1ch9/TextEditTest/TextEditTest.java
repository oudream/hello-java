/**
 * @version 1.20 27 Jul 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class TextEditFrame extends JFrame
{  public TextEditFrame()
   {  setTitle("TextEditTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
      
      Container contentPane = getContentPane();

      JPanel panel = new JPanel();
      
      JButton replaceButton = new JButton("Replace");
      panel.add(replaceButton);
      replaceButton.addActionListener(new ActionListener()
         {  public void actionPerformed(ActionEvent evt)
            {  String f = from.getText();
               int n = textArea.getText().indexOf(f);
               if (n >= 0 && f.length() > 0)
                  textArea.replaceRange(to.getText(), n, 
                     n + f.length());
            }
         });

      from = new JTextField(8);
      panel.add(from);

      panel.add(new JLabel("with"));

      to = new JTextField(8);
      panel.add(to);
    
      textArea = new JTextArea(8, 40);
      scrollPane = new JScrollPane(textArea);

      contentPane.add(panel, "South");
      contentPane.add(scrollPane, "Center");
   }
   
   private JScrollPane scrollPane;
   private JTextArea textArea;
   private JTextField from, to;
}

public class TextEditTest {
   public static void main(String[] args)
   {  JFrame f = new TextEditFrame();
      f.show();  
   }
}   
