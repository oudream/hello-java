/**
 * @version 1.20 1999-09-24
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;

public class FileNameBean extends JPanel
   implements Serializable
{  public FileNameBean()
   {  dialogButton = new JButton("...");
      nameField = new JTextField("");

      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));

      chooser.setFileFilter(
         new javax.swing.filechooser.FileFilter()
         {  public boolean accept(File f)
            {  String name = f.getName().toLowerCase();
               return name.endsWith("." + defaultExtension)
                  || f.isDirectory();
            }
            public String getDescription()
            {  return defaultExtension + " files";
            }
         });

      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.BOTH;
      add(nameField, gbc, 0, 0, 1, 1);
      dialogButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent evt)
            {  showFileDialog();
            }
         });
      nameField.setEditable(false);
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.EAST;
      gbc.fill = GridBagConstraints.NONE;
      add(dialogButton, gbc, 1, 0, 1, 1);
   }

   public void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      add(c, gbc);
   }

   public void showFileDialog()
   {  int r = chooser.showOpenDialog(null);
      if(r == JFileChooser.APPROVE_OPTION)
      {  String name
            = chooser.getSelectedFile().getAbsolutePath();
         setFileName(name);
      }
   }

   public void setFileName(String newValue)
   {  String oldValue = nameField.getText();
      firePropertyChange("fileName", oldValue, newValue);
      nameField.setText(newValue);
   }

   public String getFileName()
   {  return nameField.getText();
   }

   public Dimension getMinimumSize()
   {  return new Dimension(XMINSIZE, YMINSIZE);
   }

   public String getDefaultExtension()
   {  return defaultExtension;
   }

   public void setDefaultExtension(String s)
   {  defaultExtension = s;
   }

   private static final int XMINSIZE = 200;
   private static final int YMINSIZE = 20;
   private JButton dialogButton;
   private JTextField nameField;
   private JFileChooser chooser;
   private String defaultExtension = "gif";
}