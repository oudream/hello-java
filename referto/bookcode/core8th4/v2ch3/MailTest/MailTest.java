/**
 * @version 1.00 1999-08-27
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class MailTest
{  public static void main(String[] args)
   {  JFrame frame = new MailTestFrame();
      frame.show();
   }
}

class MailTestFrame extends JFrame
   implements ActionListener
{  public MailTestFrame()
   {  setTitle("MailTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      getContentPane().setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.weightx = 0;
      gbc.weighty = 0;

      gbc.weightx = 0;
      add(new JLabel("From:"), gbc, 0, 0, 1, 1);
      gbc.weightx = 100;
      from = new JTextField(20);
      add(from, gbc, 1, 0, 1, 1);

      gbc.weightx = 0;
      add(new JLabel("To:"), gbc, 0, 1, 1, 1);
      gbc.weightx = 100;
      to = new JTextField(20);
      add(to, gbc, 1, 1, 1, 1);

      gbc.weightx = 0;
      add(new JLabel("SMTP server:"), gbc, 0, 2, 1, 1);
      gbc.weightx = 100;
      smtpServer = new JTextField(20);
      add(smtpServer, gbc, 1, 2, 1, 1);

      gbc.fill = GridBagConstraints.BOTH;
      gbc.weighty = 100;
      message = new JTextArea();
      add(new JScrollPane(message), gbc, 0, 3, 2, 1);

      response = new JTextArea();
      add(new JScrollPane(response), gbc, 0, 4, 2, 1);

      gbc.weighty = 0;
      JButton sendButton = new JButton("Send");
      sendButton.addActionListener(this);
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(sendButton);
      add(buttonPanel, gbc, 0, 5, 2, 1);
   }

   private void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   public void actionPerformed(ActionEvent evt)
   {  SwingUtilities.invokeLater(new Runnable()
        {  public void run()
           {   sendMail();
           }
        });
   }

   public void sendMail()
   {  try
      {  Socket s = new Socket(smtpServer.getText(), 25);

         out = new PrintWriter(s.getOutputStream());
         in = new BufferedReader(new
            InputStreamReader(s.getInputStream()));

         String hostName
            = InetAddress.getLocalHost().getHostName();

         send(null);
         send("HELO " + hostName);
         send("MAIL FROM: " + from.getText());
         send("RCPT TO: " + to.getText());
         send("DATA");
         out.println(message.getText());
         send(".");
         s.close();
      }
      catch (IOException exception)
      {  response.append("Error: " + exception);
      }
   }

   public void send(String s) throws IOException
   {  if (s != null)
      {  response.append(s + "\n");
         out.println(s);
         out.flush();
      }
      String line;
      if ((line = in.readLine()) != null)
         response.append(line + "\n");
   }

   private BufferedReader in;
   private PrintWriter out;
   private JTextField from;
   private JTextField to;
   private JTextField smtpServer;
   private JTextArea message;
   private JTextArea response;
}

