/**
 * @version 1.20 01 Sep 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataExchangeTest extends JFrame 
   implements ActionListener
{  public DataExchangeTest()
   {  setTitle("DataExchangeTest");
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
      connectItem = new JMenuItem("Connect");
      connectItem.addActionListener(this);
      fileMenu.add(connectItem);
      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if (source == connectItem)
      {  ConnectInfo transfer 
            = new ConnectInfo("yourname", "pw");
         if (dialog == null) 
            dialog = new ConnectDialog(this);
         if (dialog.showDialog(transfer))
         {  String uname = transfer.username;
            String pwd = transfer.password;
            Container contentPane = getContentPane();
            contentPane.add(new JLabel("username=" +
               uname + ", password=" + pwd),
               "South");
            validate();
         }
      }
      else if(source == exitItem)
         System.exit(0);
   }

   public static void main(String[] args)
   {  JFrame f = new DataExchangeTest();
      f.show();
   }

   private ConnectDialog dialog = null;
   private JMenuItem connectItem;
   private JMenuItem exitItem;
}

class ConnectInfo
{  public String username;
   public String password;
   public ConnectInfo(String u, String p) 
      { username = u; password = p; }
}   
                        
class ConnectDialog extends JDialog 
   implements ActionListener
{  public ConnectDialog(JFrame parent)
   {  super(parent, "Connect", true);
      Container contentPane = getContentPane();
      JPanel p1 = new JPanel();
      p1.setLayout(new GridLayout(2, 2));
      p1.add(new JLabel("User name:"));
      p1.add(username = new JTextField(""));
      p1.add(new JLabel("Password:"));
      p1.add(password = new JPasswordField(""));
      contentPane.add("Center", p1);
      
      Panel p2 = new Panel();
      okButton = addButton(p2, "Ok");
      cancelButton = addButton(p2, "Cancel");
      contentPane.add("South", p2);
      setSize(240, 120);
   }

   JButton addButton(Container c, String name)
   {  JButton button = new JButton(name);
      button.addActionListener(this);
      c.add(button);
      return button;
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if(source == okButton)
      {  ok = true;
         setVisible(false);
      }
      else if (source == cancelButton)
         setVisible(false);
   }

   public boolean showDialog(ConnectInfo transfer)
   {  username.setText(transfer.username);
      password.setText(transfer.password);
      ok = false;
      show();
      if (ok)
      {  transfer.username = username.getText();
         transfer.password = password.getText();
      }
      return ok;
   }

   private JTextField username;
   private JTextField password;
   private boolean ok;
   private JButton okButton;
   private JButton cancelButton;
}

                      