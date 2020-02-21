/**
 * @version 1.20 1999-08-27
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import javax.swing.*;

public class WarehouseApplet extends JApplet
   implements ActionListener
{  public void init()
   {  initUI();
      String url = getCodeBase().getHost();
      url = "rmi://" + url;
      try
      {  centralWarehouse = (Warehouse)Naming.lookup(url
            + "/central_warehouse");
      }
      catch(Exception e)
      {  showStatus("Error: Can't connect to warehouse. " + e);
      }
   }

   private void callWarehouse(Customer c)
   {  try
      {  products = centralWarehouse.find(c);
         descriptionListModel.clear();
         for (int i = 0; i < products.size(); i++)
         {  Product p = (Product)products.get(i);
            Image productImage
               = getImage(getCodeBase(), p.getImageFile());
            Icon productIcon = new ImageIcon(productImage);
            descriptionListModel.addElement(productIcon);
         }
      }
      catch(Exception e)
      {  showStatus("Error: " + e);
      }
   }

   private void initUI()
   {  getContentPane().setLayout(new GridBagLayout());

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
      submitButton = new JButton("Submit");
      add(submitButton, gbc, 0, 3, 2, 1);
      submitButton.addActionListener(this);


      gbc.weighty = 100;
      gbc.fill = GridBagConstraints.BOTH;
      descriptionListModel = new DefaultListModel();
      descriptions = new JList(descriptionListModel);
      add(new JScrollPane(descriptions), gbc, 0, 4, 2, 1);
   }

   private void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   public void actionPerformed(ActionEvent event)
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

   private Warehouse centralWarehouse;
   private JTextField age;
   private JCheckBox male;
   private JCheckBox female;
   private JList hobbies;
   private JButton submitButton;
   private JList descriptions;
   private DefaultListModel descriptionListModel;
   private Vector products;
}