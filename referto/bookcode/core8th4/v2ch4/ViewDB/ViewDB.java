/**
 * @version 1.20 1999-07-26
 * @author Cay Horstmann
 */

import java.net.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ViewDB
{  public static void main(String[] args)
   {  JFrame frame = new ViewDBFrame();
      frame.show();
   }
}

class ViewDBFrame extends JFrame
   implements ActionListener
{  public ViewDBFrame()
   {  setTitle("ViewDB");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();

      tableNames = new JComboBox();
      tableNames.addActionListener(this);

      dataPanel = new JPanel();
      contentPane.add(dataPanel, "Center");

      nextButton = new JButton("Next");
      nextButton.addActionListener(this);
      JPanel p = new JPanel();
      p.add(nextButton);
      contentPane.add(p, "South");

      fields = new ArrayList();

      try
      {  con = getConnection();
         stmt = con.createStatement();
         md = con.getMetaData();
         ResultSet mrs = md.getTables(null, null, null,
            new String[] { "TABLE" });
         while (mrs.next())
            tableNames.addItem(mrs.getString(3));
          mrs.close();
      }
      catch(Exception e)
      {  JOptionPane.showMessageDialog(this, e);
      }

      contentPane.add(tableNames, "North");
   }

   public static Connection getConnection()
      throws SQLException, IOException
   {  Properties props = new Properties();
      String fileName = "ViewDB.properties";
      FileInputStream in = new FileInputStream(fileName);
      props.load(in);

      String drivers = props.getProperty("jdbc.drivers");
      if (drivers != null)
         System.setProperty("jdbc.drivers", drivers);
      String url = props.getProperty("jdbc.url");
      String username = props.getProperty("jdbc.username");
      String password = props.getProperty("jdbc.password");

      return
         DriverManager.getConnection(url, username, password);
   }

   private void add(Container p, Component c,
      GridBagConstraints gbc, int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      p.add(c, gbc);
   }

   public void actionPerformed(ActionEvent evt)
   {  if (evt.getSource() == nextButton)
      {  showNextRow();
      }
      else if (evt.getSource() == tableNames)
      {  remove(dataPanel);
         dataPanel = new JPanel();
         fields.clear();
         dataPanel.setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.weighty = 100;

         try
         {  String tableName
               = (String)tableNames.getSelectedItem();
            if (rs != null) rs.close();
            rs = stmt.executeQuery("SELECT * FROM "
               + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {  String columnName = rsmd.getColumnLabel(i);
               int columnWidth = rsmd.getColumnDisplaySize(i);
               JTextField tb = new JTextField(columnWidth);
               fields.add(tb);

               gbc.weightx = 0;
               gbc.anchor = GridBagConstraints.EAST;
               gbc.fill = GridBagConstraints.NONE;
               add(dataPanel, new JLabel(columnName),
                  gbc, 0, i - 1, 1, 1);

               gbc.weightx = 100;
               gbc.anchor = GridBagConstraints.WEST;
               gbc.fill = GridBagConstraints.HORIZONTAL;
               add(dataPanel, tb, gbc, 1, i - 1, 1, 1);
            }
         }
         catch(Exception e)
         {  JOptionPane.showMessageDialog(this, e);
         }
         getContentPane().add(dataPanel, "Center");
         doLayout();
         pack();

         showNextRow();
      }
   }

   public void showNextRow()
   {  if (rs == null) return;
      {  try
         {  if (rs.next())
            {  for (int i = 1; i <= fields.size(); i++)
               {  String field = rs.getString(i);
                  JTextField tb
                     = (JTextField)fields.get(i - 1);
                  tb.setText(field);
               }
            }
            else
            {  rs.close();
               rs = null;
            }
         }
         catch(Exception e)
         {  System.out.println("Error " + e);
         }
      }
   }

   private JButton nextButton;
   private JPanel dataPanel;
   private JComboBox tableNames;
   private ArrayList fields;

   private Connection con;
   private Statement stmt;
   private DatabaseMetaData md;
   private ResultSet rs;
}
