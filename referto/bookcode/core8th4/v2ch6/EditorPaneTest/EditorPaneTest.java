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

public class EditorPaneTest
{  public static void main(String[] args)
   {  JFrame frame = new EditorPaneFrame();
      frame.show();
   }
}

class EditorPaneFrame extends JFrame
{  public EditorPaneFrame()
   {  setTitle("EditorPaneTest");
      setSize(600, 400);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // set up text field and load button for typing in URL

      url = new JTextField(30);

      loadButton = new JButton("Load");
      loadButton.addActionListener(new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  try
               {  // remember URL for back button
                  urlStack.push(url.getText());

                  editorPane.setPage(url.getText());
               }
               catch(IOException e)
               {  editorPane.setText("Error: " + e);
               }
            }
         });

      // set up back button and button action

      backButton = new JButton("Back");
      backButton.addActionListener(new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  if (urlStack.size() <= 1) return;
               try
               {  // get URL from back button
                  urlStack.pop();
                  // show URL in text field
                  String urlString = (String)urlStack.peek();
                  url.setText(urlString);

                  editorPane.setPage(urlString);
               }
               catch(IOException e)
               {  editorPane.setText("Error: " + e);
               }
            }
         });

      // set up editor pane and hyperlink listener

      editorPane = new JEditorPane();
      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(new HyperlinkListener()
         {  public void hyperlinkUpdate(HyperlinkEvent event)
            {  if (event.getEventType()
                  == HyperlinkEvent.EventType.ACTIVATED)
               {  try
                  {  // remember URL for back button
                     urlStack.push(event.getURL().toString());
                     // show URL in text field
                     url.setText(event.getURL().toString());

                     editorPane.setPage(event.getURL());
                  }
                  catch(IOException e)
                  {  editorPane.setText("Error: " + e);
                  }
               }
            }
         });

      // set up checkbox for toggling edit mode

      editable = new JCheckBox();
      editable.addActionListener(new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  editorPane.setEditable(editable.isSelected());
            }
         });

      Container contentPane = getContentPane();
      contentPane.add(new JScrollPane(editorPane), "Center");

      // put all control components in a panel

      JPanel panel = new JPanel();
      panel.add(new JLabel("URL"));
      panel.add(url);
      panel.add(loadButton);
      panel.add(backButton);
      panel.add(new JLabel("Editable"));
      panel.add(editable);

      contentPane.add(panel, "South");
   }

   private JTextField url;
   private JCheckBox editable;
   private JButton loadButton;
   private JButton backButton;
   private JEditorPane editorPane;
   private Stack urlStack = new Stack();
}
