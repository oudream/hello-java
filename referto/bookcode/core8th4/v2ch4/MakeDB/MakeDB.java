/**
 * @version 1.20 1999-08-16
 * @author Cay Horstmann
 */

import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

class MakeDB
{  public static void main (String args[])
   {  try
      {  Connection con = getConnection();
         Statement stmt = con.createStatement();

         String tableName = "";
         if (args.length > 0)
            tableName = args[0];
         else
         {  System.out.println("Usage: MakeDB TableName");
            System.exit(0);
         }

         BufferedReader in = new BufferedReader(new
            FileReader(tableName + ".dat"));

         createTable(tableName, in, stmt);
         showTable(tableName, stmt);

         in.close();
         stmt.close();
         con.close();
      }
      catch (SQLException ex)
      {  System.out.println ("SQLException:");
         while (ex != null)
         {  System.out.println ("SQLState: "
               + ex.getSQLState());
            System.out.println ("Message:  "
               + ex.getMessage());
            System.out.println ("Vendor:   "
               + ex.getErrorCode());
            ex = ex.getNextException();
            System.out.println ("");
          }
      }
      catch (IOException ex)
      {  System.out.println("Exception: " + ex);
         ex.printStackTrace ();
      }
   }

   public static Connection getConnection()
      throws SQLException, IOException
   {  Properties props = new Properties();
      String fileName = "MakeDB.properties";
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

   public static void createTable(String tableName,
      BufferedReader in, Statement stmt)
      throws SQLException, IOException
   {  String line = in.readLine();
      String command = "CREATE TABLE " + tableName
         + "(" + line + ")";
      stmt.executeUpdate(command);

      while ((line = in.readLine()) != null)
      {  command = "INSERT INTO " + tableName
            + " VALUES (" + line + ")";
         stmt.executeUpdate(command);
      }
   }

   public static void showTable(String tableName,
      Statement stmt) throws SQLException
   {  String query = "SELECT * FROM " + tableName;
      ResultSet rs = stmt.executeQuery(query);
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      while (rs.next())
      {  for (int i = 1; i <= columnCount; i++)
         {  if (i > 1) System.out.print(", ");
            System.out.print(rs.getString(i));
         }
         System.out.println();
      }
      rs.close();
   }
}



