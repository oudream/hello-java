/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProgressMonitorTest
{  public static void main(String[] args)
   {  JFrame frame = new ProgressMonitorFrame();
      frame.show();
   }
}

class ProgressMonitorFrame extends JFrame
{  public ProgressMonitorFrame()
   {  setTitle("ProgressMonitorTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();

      // this text area holds the activity output
      textArea = new JTextArea();

      // set up a button panel
      JPanel panel = new JPanel();
      startButton = new JButton("Start");
      panel.add(startButton);

      contentPane.add(new JScrollPane(textArea), "Center");
      contentPane.add(panel, "South");

      // set up the button action

      startButton.addActionListener(
         new ActionListener()
            {  public void actionPerformed(ActionEvent event)
               {  // start activity
                  activity = new SimulatedActivity(1000);
                  activity.start();

                  // launch progress dialog
                  progressDialog = new ProgressMonitor(
                     ProgressMonitorFrame.this,
                     "Waiting for Simulated Activity",
                     null, 0, activity.getTarget());

                  // start timer
                  activityMonitor.start();

                  startButton.setEnabled(false);
               }
            });

      // set up the timer action

      activityMonitor = new Timer(500,
         new ActionListener()
            {  public void actionPerformed(ActionEvent event)
               {  int current = activity.getCurrent();

                  // show progress
                  textArea.append(current + "\n");
                  progressDialog.setProgress(current);

                  // check if task is completed or canceled
                  if (current == activity.getTarget()
                     || progressDialog.isCanceled())
                  {  activityMonitor.stop();
                     progressDialog.close();
                     activity.interrupt();
                     startButton.setEnabled(true);
                  }
               }
            });
   }

   private Timer activityMonitor;
   private JButton startButton;
   private ProgressMonitor progressDialog;
   private JTextArea textArea;
   private SimulatedActivity activity;
}

class SimulatedActivity extends Thread
{  public SimulatedActivity(int t)
   {  current = 0;
      target = t;
   }

   public int getTarget()
   {  return target;
   }

   public int getCurrent()
   {  return current;
   }

   public void run()
   {  while (current < target && !interrupted())
      {  try
         {  sleep(100);
         }
         catch(InterruptedException e)
         {  return;
         }
         current++;
      }
   }

   private int current;
   private int target;
}
