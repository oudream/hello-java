/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProgressMonitorInputStreamTest
{  public static void main(String[] args)
   {  JFrame frame = new TextFrame();
      frame.show();
   }
}

class TextFrame extends JFrame
   implements ActionListener
{  public TextFrame()
   {  setTitle("ProgressMonitorInputStreamTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();

      // this text area contains line counts and error messages
      textArea = new JTextArea();
      contentPane.add(new JScrollPane(textArea), "Center");

      // set up menu

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("File");
      menuBar.add(fileMenu);
      openItem = new JMenuItem("Open");
      openItem.addActionListener(this);
      fileMenu.add(openItem);
      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if (source == openItem)
      {  // have user select file

         JFileChooser chooser = new JFileChooser();
         chooser.setCurrentDirectory(new File("."));
         chooser.setFileFilter(
            new javax.swing.filechooser.FileFilter()
            {  public boolean accept(File f)
               {  String fname = f.getName().toLowerCase();
                  return fname.endsWith(".txt")
                     || f.isDirectory();
               }
               public String getDescription()
               { return "Text Files"; }
            });
         int r = chooser.showOpenDialog(this);
         if (r == JFileChooser.APPROVE_OPTION)
            readFile(chooser.getSelectedFile());
      }
      else if (source == exitItem)
         System.exit(0);
   }

   public void readFile(final File f)
   {  /* important: the monitored activity must be in a new
         thread. We define a thread class on the fly; the thread
         action is in the run method
      */

      Thread readThread = new Thread()
      {  public void run()
         {  try
            {  // set up stream and reader filter sequence

               FileInputStream fileIn = new FileInputStream(f);
               ProgressMonitorInputStream progressIn
                  = new ProgressMonitorInputStream(TextFrame.this,
                     "Reading " + f.getName(), fileIn);
               InputStreamReader inReader
                  = new InputStreamReader(progressIn);
               BufferedReader in = new BufferedReader(inReader);

               // read file and count lines

               int count = 0;
               String line;
               while ((line = in.readLine()) != null)
                  count++;
               textArea.append(f + ": " + count + " lines\n");
               fileIn.close();
            }
            catch(IOException e)
            {  textArea.append("Error " + e + "\n");
            }
         }
      };

      readThread.start();
   }

   private JTextArea textArea;
   private JMenuItem openItem;
   private JMenuItem exitItem;
}

