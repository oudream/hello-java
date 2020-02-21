/**
 * @version 1.20 1999-08-23
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import javax.swing.*;

public class WarehouseClient
{  public static void main(String[] args)
   {  JFrame frame = new WarehouseClientFrame();
      frame.show();
   }
}

class WarehouseClientFrame extends JFrame
   implements ActionListener
{  public WarehouseClientFrame()
   {  initUI();

      System.setSecurityManager(new RMISecurityManager());
      try
      {  Properties props = new Properties();
         String fileName = "WarehouseClient.properties";
         FileInputStream in = new FileInputStream(fileName);
         props.load(in);
         String url = props.getProperty("warehouse.url");
         if (url == null)
            url = "rmi://localhost/central_warehouse";

         centralWarehouse = (Warehouse)Naming.lookup(url);
      }
      catch(Exception e)
      {  System.out.println("Error: Can't connect to warehouse. " + e);
      }
   }

   private void callWarehouse(Customer c)
   {  try
      {  Vector recommendations = centralWarehouse.find(c);
         result.setText(c + "\n");
         for (int i = 0; i < recommendations.size(); i++)
         {  Product p = (Product)recommendations.get(i);
            String t = p.getDescription() + "\n";
            result.append(t);
         }
      }
      catch(Exception e)
      {  result.setText("Error: " + e);
      }
   }


   public void actionPerformed(ActionEvent evt)
   {  Object[] hobbyObjects = hobbies.getSelectedValues();
      String[] hobbyStrings = new String[hobbyObjects.length];
      System.arraycopy(hobbyObjects, 0, hobbyStrings, 0,
         hobbyObjects.length);
      Customer c = new Customer(Integer.parseInt(age.getText()),
         (male.isSelected() ? Product.MALE : 0)
            + (female.isSelected() ? Product.FEMALE : 0),
         hobbyStrings);
      callWarehouse(c);
   }

   private void initUI()
   {  setTitle("WarehouseClient");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      getContentPane().setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.weightx = 100;
      gbc.weighty = 0;

      add(new JLabel("Age:"), gbc, 0, 0, 1, 1);
      age = new JTextField(4);
      age.setText("20");
      add(age, gbc, 1, 0, 1, 1);

      male = new JCheckBox("Male", true);
      female = new JCheckBox("Female", true);
      add(male, gbc, 0, 1, 1, 1);
      add(female, gbc, 1, 1, 1, 1);

      gbc.weighty = 100;
      add(new JLabel("Hobbies"), gbc, 0, 2, 1, 1);
      String[] choices = { "Gardening", "Beauty",
         "Computers", "Household", "Sports" };
      gbc.fill = GridBagConstraints.BOTH;
      hobbies = new JList(choices);
      add(new JScrollPane(hobbies), gbc, 1, 2, 1, 1);

      gbc.weighty = 0;
      gbc.fill = GridBagConstraints.NONE;
      JButton submitButton = new JButton("Submit");
      add(submitButton, gbc, 0, 3, 2, 1);
      submitButton.addActionListener(this);

      gbc.weighty = 100;
      gbc.fill = GridBagConstraints.BOTH;
      result = new JTextArea(4, 40);
      result.setEditable(false);
      add(result, gbc, 0, 4, 2, 1);
   }

   private void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   private Warehouse centralWarehouse;
   private JTextField age;
   private JCheckBox male;
   private JCheckBox female;
   private JList hobbies;
   private JTextArea result;
}

