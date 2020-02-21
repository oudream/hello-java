/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProgressBarTest
{  public static void main(String[] args)
   {  JFrame frame = new ProgressBarFrame();
      frame.show();
   }
}

class ProgressBarFrame extends JFrame
{  public ProgressBarFrame()
   {  setTitle("ProgressBarTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();

      // this text area holds the activity output
      textArea = new JTextArea();

      // set up panel with button and progress bar

      JPanel panel = new JPanel();
      startButton = new JButton("Start");
      progressBar = new JProgressBar();
      progressBar.setStringPainted(true);
      panel.add(startButton);
      panel.add(progressBar);
      contentPane.add(new JScrollPane(textArea), "Center");
      contentPane.add(panel, "South");

      // set up the button action

      startButton.addActionListener(
         new ActionListener()
            {  public void actionPerformed(ActionEvent event)
               {  progressBar.setMaximum(1000);
                  activity = new SimulatedActivity(1000);
                  activity.start();
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
                  progressBar.setValue(current);

                  // check if task is completed
                  if (current == activity.getTarget())
                  {  activityMonitor.stop();
                     startButton.setEnabled(true);
                  }
               }
            });
   }

   private Timer activityMonitor;
   private JButton startButton;
   private JProgressBar progressBar;
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
