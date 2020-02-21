/**
 * @version 1.20 01 Sep 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class ButtonPanel extends JPanel
{  public ButtonPanel(String title, String[] options)
   {  setBorder(BorderFactory.createTitledBorder
            (BorderFactory.createEtchedBorder(), title));
      setLayout(new BoxLayout(this, 
         BoxLayout.Y_AXIS));
      group = new ButtonGroup();
      
      for (int i = 0; i < options.length; i++)
      {  JRadioButton b = new JRadioButton(options[i]);
         b.setActionCommand(options[i]);
         add(b);
         group.add(b);
         b.setSelected(i == 0);
      }
   }
   
   String getSelection()
   {  return group.getSelection().getActionCommand();
   }
   
   ButtonGroup group;
}
   
public class OptionDialogTest extends JFrame 
   implements ActionListener
{  public OptionDialogTest()
   {  setTitle("OptionDialogTest");
      setSize(600, 400);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );

      JPanel gridPanel = new JPanel();
      gridPanel.setLayout(new GridLayout(2, 3));
      
      typePanel = new ButtonPanel("Type", 
         new String[] 
         {  "Message", 
            "Confirm", 
            "Option", 
            "Input" 
         });

      messageTypePanel = new ButtonPanel("Message Type", 
         new String[] 
         {  "ERROR_MESSAGE", 
            "INFORMATION_MESSAGE", 
            "WARNING_MESSAGE", 
            "QUESTION_MESSAGE",
            "PLAIN_MESSAGE" 
         });
         
      messagePanel = new ButtonPanel("Message", 
         new String[] 
         {  "String", 
            "Icon", 
            "Component", 
            "Other", 
            "Object[]"
         });
      
      optionTypePanel = new ButtonPanel("Confirm", 
         new String[] 
         {  "DEFAULT_OPTION", 
            "YES_NO_OPTION", 
            "YES_NO_CANCEL_OPTION", 
            "OK_CANCEL_OPTION" 
         });
         
      optionsPanel = new ButtonPanel("Option", 
         new String[] 
         {  "String[]", 
            "Icon[]", 
            "Object[]" 
         });
         
      inputPanel = new ButtonPanel("Input",
         new String[]
         {  "Text field",
            "Combo box"
         });
         
      JPanel showPanel = new JPanel();
      JButton showButton = new JButton("Show");
      showButton.addActionListener(this);
      showPanel.add(showButton);
      
      gridPanel.add(typePanel);
      gridPanel.add(messageTypePanel);
      gridPanel.add(messagePanel);
      gridPanel.add(optionTypePanel);
      gridPanel.add(optionsPanel);
      gridPanel.add(showPanel);
      gridPanel.add(inputPanel);

      Container contentPane = getContentPane();
      contentPane.add(gridPanel, "Center");
      contentPane.add(showPanel, "South");
   }

   public Object getMessage()
   {  String s = messagePanel.getSelection();
      if (s.equals("String"))
         return messageString;
      else if (s.equals("Icon"))
         return messageIcon;
      else if (s.equals("Component"))
         return messageComponent;
      else if (s.equals("Object[]"))
         return new Object[]
         {  messageString,
            messageIcon,
            messageComponent,
            messageFont
         };
      else if (s.equals("Other"))
         return messageFont;
      else return null;
   }
   
   public Object[] getOptions()
   {  String s = optionsPanel.getSelection();
      if (s.equals("String[]"))
         return new String[] { "Yellow", "Blue", "Red" };
      else if (s.equals("Icon[]"))
         return new Icon[]
         {  new ImageIcon("yellow-ball.gif"),
            new ImageIcon("blue-ball.gif"),
            new ImageIcon("red-ball.gif")
         };
      else if (s.equals("Object[]"))
         return new Object[]
         {  messageString,
            messageIcon,
            messageComponent,
            messageFont
         };
      else 
         return null;
   }
   
   public int getType(ButtonPanel panel)
   {  String s = panel.getSelection();
      try
      {  Class cl = JOptionPane.class;
         return cl.getField(s).getInt(cl);
      }
      catch(Exception e)
      {  return -1;
      }
   }
   
   public void actionPerformed(ActionEvent evt)
   {  if (typePanel.getSelection().equals("Confirm"))
         JOptionPane.showConfirmDialog(this, 
            getMessage(),
            "Title",
            getType(optionTypePanel),
            getType(messageTypePanel));
      else if (typePanel.getSelection().equals("Input"))            
      {  if (inputPanel.getSelection().equals("Text field"))
            JOptionPane.showInputDialog(this,
               getMessage(),
               "Title",
               getType(messageTypePanel));
         else
            JOptionPane.showInputDialog(this,
               getMessage(),
               "Title",
               getType(messageTypePanel),
               null,
               new String[] { "Yellow", "Blue", "Red" },
               "Blue");
      }
      else if (typePanel.getSelection().equals("Message"))            
         JOptionPane.showMessageDialog(this,
            getMessage(),
            "Title",
            getType(messageTypePanel));
      else if (typePanel.getSelection().equals("Option"))
         JOptionPane.showOptionDialog(this, 
            getMessage(),
            "Title",
            getType(optionTypePanel),
            getType(messageTypePanel),
            null,
            getOptions(),
            getOptions()[0]);           
   }

   public static void main(String[] args)
   {  JFrame f = new OptionDialogTest();
      f.show();
   }
   
   private ButtonPanel typePanel;
   private ButtonPanel messagePanel;
   private ButtonPanel messageTypePanel;
   private ButtonPanel optionTypePanel;
   private ButtonPanel optionsPanel;
   private ButtonPanel inputPanel;
   
   private String messageString = "Message";
   private Icon messageIcon 
      = new ImageIcon("blue-ball.gif");
   private Font messageFont 
      = new Font("Serif", Font.PLAIN, 8);
   private Component messageComponent 
      = new JPanel()
         {  public void paintComponent(Graphics g)
            {  super.paintComponent(g);
               g.setFont(messageFont);
               g.drawString("Component", 0, 8);
            }
            public Dimension getMinimumSize()
            {  return new Dimension(12, 30);
            }
         };
}

