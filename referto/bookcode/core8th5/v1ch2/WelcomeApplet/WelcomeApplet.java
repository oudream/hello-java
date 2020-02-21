/**
 * @version 1.20 10 Aug 1998 
 * @author Cay Horstmann
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class WelcomeApplet extends JApplet
   implements ActionListener
{  public void init()
   {  Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      JLabel label = new JLabel(getParameter("greeting"), 
         SwingConstants.CENTER);
      label.setFont(new Font("TimesRoman", Font.BOLD, 18));
      contentPane.add(label, "Center");
      JPanel panel = new JPanel();
      JButton cayButton = new JButton("Cay Horstmann");
      cayButton.addActionListener(this);
      panel.add(cayButton);
      JButton garyButton = new JButton("Gary Cornell");
      garyButton.addActionListener(this);
      panel.add(garyButton);
      contentPane.add(panel, "South");      
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      String urlName;
      if (source == cayButton) 
         urlName = "http://www.horstmann.com";
      else if (source == garyButton) 
         urlName = "mailto:gary@thecornells.com";
      else return;
      try
      {  URL u = new URL(urlName);
         getAppletContext().showDocument(u);
      }
      catch(Exception e)
      {  showStatus("Error " + e);
      }
   }
   
   private JButton cayButton;
   private JButton garyButton;
}

