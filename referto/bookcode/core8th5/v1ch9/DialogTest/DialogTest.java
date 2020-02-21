/**
 * @version 1.20 27 Aug 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class DialogFrame extends JFrame 
   implements ActionListener
{  public DialogFrame()
   {  setTitle("DialogTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );

      JMenuBar mbar = new JMenuBar();
      setJMenuBar(mbar);
      JMenu fileMenu = new JMenu("File");
      mbar.add(fileMenu);
      aboutItem = new JMenuItem("About");
      aboutItem.addActionListener(this);
      fileMenu.add(aboutItem);
      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if(source == aboutItem)
      {  if (dialog == null) // first time
            dialog = new AboutDialog(this);   
         dialog.show();
      }
      else if(source == exitItem)
      {  System.exit(0);
      }
   }
   
   private AboutDialog dialog;
   private JMenuItem aboutItem;
   private JMenuItem exitItem;
}

class AboutDialog extends JDialog
{  public AboutDialog(JFrame parent)
   {  super(parent, "About DialogTest", true);         

      Box b = Box.createVerticalBox();
      b.add(Box.createGlue());
      b.add(new JLabel("Core Java"));
      b.add(new JLabel("By Cay Horstmann and Gary Cornell"));
      b.add(Box.createGlue());
      getContentPane().add(b, "Center");
                
      JPanel p2 = new JPanel();
      JButton ok = new JButton("Ok");
      p2.add(ok);
      getContentPane().add(p2, "South");

      ok.addActionListener(new ActionListener() 
         {  public void actionPerformed(ActionEvent evt) 
            { setVisible(false); } 
         } );

      setSize(250, 150);
   }
}

public class DialogTest {
   public static void main(String[] args)
   {  JFrame f = new DialogFrame();
      f.show();
   }
}
