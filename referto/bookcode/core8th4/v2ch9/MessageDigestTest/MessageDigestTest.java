/**
 * @version 1.10 1999-10-05
 * @author Cay Horstmann
 */

import java.io.*;
import java.security.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageDigestTest
{  public static void main(String[] args)
   {  JFrame f = new MessageDigestFrame();
      f.show();
   }
}

class MessageDigestFrame extends JFrame
{  public MessageDigestFrame()
   {  setTitle("MessageDigestTest");
      setSize(400, 200);
      addWindowListener(
         new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         });

      JPanel panel = new JPanel();
      ButtonGroup group = new ButtonGroup();
      ActionListener listener =
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  JCheckBox b = (JCheckBox)event.getSource();
               setAlgorithm(b.getText());
            }
         };
      addCheckBox(panel, "SHA-1", group, true, listener);
      addCheckBox(panel, "MD5", group, false, listener);

      Container contentPane = getContentPane();

      contentPane.add(panel, "North");
      contentPane.add(new JScrollPane(message), "Center");
      contentPane.add(digest, "South");
      digest.setFont(new Font("Monospaced", Font.PLAIN, 12));

      setAlgorithm("SHA-1");

      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu("File");
      JMenuItem fileDigestItem = new JMenuItem("File digest");
      fileDigestItem.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  loadFile();
            }
         });
      menu.add(fileDigestItem);
      JMenuItem textDigestItem
         = new JMenuItem("Text area digest");
      textDigestItem.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  String m = message.getText();
               computeDigest(m.getBytes());
            }
         });
      menu.add(textDigestItem);
      menuBar.add(menu);
      setJMenuBar(menuBar);
   }

   public void addCheckBox(Container c, String name,
      ButtonGroup g, boolean selected, ActionListener listener)
   {  JCheckBox b = new JCheckBox(name, selected);
      c.add(b);
      g.add(b);
      b.addActionListener(listener);
   }

   public void setAlgorithm(String alg)
   {  try
      {  currentAlgorithm = MessageDigest.getInstance(alg);
         digest.setText("");
      }
      catch(NoSuchAlgorithmException e)
      {  digest.setText("" + e);
      }
   }

   public void loadFile()
   {  JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));

      int r = chooser.showOpenDialog(this);
      if(r == JFileChooser.APPROVE_OPTION)
      {  String name
            = chooser.getSelectedFile().getAbsolutePath();
         computeDigest(loadBytes(name));
      }
   }

   public byte[] loadBytes(String name)
   {  FileInputStream in = null;

      try
      {  in = new FileInputStream(name);
         ByteArrayOutputStream buffer
            = new ByteArrayOutputStream();
         int ch;
         while ((ch = in.read()) != -1)
            buffer.write(ch);
         return buffer.toByteArray();
      }
      catch (IOException e)
      {  if (in != null)
         {  try { in.close(); } catch (IOException e2) {}
         }
         return null;
      }
   }

   public void computeDigest(byte[] b)
   {  currentAlgorithm.reset();
      currentAlgorithm.update(b);
      byte[] hash = currentAlgorithm.digest();
      String d = "";
      for (int i = 0; i < hash.length; i++)
      {  int v = hash[i] & 0xFF;
         if (v < 16) d += "0";
         d += Integer.toString(v, 16).toUpperCase() + " ";
      }
      digest.setText(d);
   }

   private JTextArea message = new JTextArea();
   private JTextField digest = new JTextField();
   private MessageDigest currentAlgorithm;
}