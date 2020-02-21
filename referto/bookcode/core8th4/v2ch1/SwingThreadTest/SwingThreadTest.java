/**
 * @version 1.20 1999-04-26
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SwingThreadTest
{  public static void main(String[] args)
   {  JFrame frame = new SwingThreadFrame();
      frame.show();
   }
}

class SwingThreadFrame extends JFrame
{  public SwingThreadFrame()
   {  setTitle("SwingThread");
      setSize(400,300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      model = new DefaultListModel();

      JList list = new JList(model);
      JScrollPane scrollPane = new JScrollPane(list);

      JPanel p = new JPanel();
      p.add(scrollPane);
      getContentPane().add(p, "South");

      JButton b = new JButton("Good");
      b.addActionListener(new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  new GoodWorkerThread(model).start();
            }
         });
      p = new JPanel();
      p.add(b);
      b = new JButton("Bad");
      b.addActionListener(new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  new BadWorkerThread(model).start();
            }
         });
      p.add(b);

      getContentPane().add(p, "North");
   }

   private DefaultListModel model;
}

class BadWorkerThread extends Thread
{  public BadWorkerThread(DefaultListModel aModel)
   {  model = aModel;
      generator = new Random();
   }

   public void run()
   {  while (true)
      {  Integer i = new Integer(generator.nextInt(10));

         if (model.contains(i))
            model.removeElement(i);
         else
            model.addElement(i);

         yield();
      }
   }

   private DefaultListModel model;
   private Random generator;
}

class GoodWorkerThread extends Thread
{  public GoodWorkerThread(DefaultListModel aModel)
   {  model = aModel;
      generator = new Random();
   }

   public void run()
   {  while (true)
      {  final Integer i = new Integer(generator.nextInt(10));
         EventQueue.invokeLater(new Runnable()
            {  public void run()
               {  if (model.contains(i))
                     model.removeElement(i);
                  else
                     model.addElement(i);
               }
            });
         yield();
      }
   }

   private DefaultListModel model;
   private Random generator;
}
