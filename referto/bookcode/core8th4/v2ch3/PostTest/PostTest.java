/**
 * @version 1.00 1999-08-28
 * @author Cay Horstmann
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class PostTest
{  public static void main(String[] args)
   {  try
      {  String fileName;
         if (args.length > 0)
            fileName = args[0];
         else
            fileName = "PostTest.properties";
         Properties props = new Properties();
         FileInputStream in = new FileInputStream(fileName);
         props.load(in);

         URL url = new URL(props.getProperty("URL"));
         props.remove("URL");
         String r = doPost(url, props);
         System.out.println(r);
      }
      catch (IOException exception)
      {  System.out.println("Error: " + exception);
      }
   }

   public static String doPost(URL url,
      Properties nameValuePairs) throws IOException
   {  URLConnection connection = url.openConnection();
      connection.setDoOutput(true);

      PrintWriter out
         = new PrintWriter(connection.getOutputStream());

      Enumeration enum = nameValuePairs.keys();

      while (enum.hasMoreElements())
      {  String name = (String)enum.nextElement();
         String value = nameValuePairs.getProperty(name);
         char ch;
         if (enum.hasMoreElements()) ch = '&'; else ch = '\n';
         out.print(name + "="
            + URLEncoder.encode(value) + ch);
      }

      out.close();

      BufferedReader in;
      try
      {  in = new BufferedReader(new
            InputStreamReader(connection.getInputStream()));
      }
      catch (FileNotFoundException exception)
      {  InputStream err
            = ((HttpURLConnection)connection).getErrorStream();
         if (err == null) throw exception;
         in = new BufferedReader(new InputStreamReader(err));
      }
      StringBuffer response = new StringBuffer();
      String line;

      while ((line = in.readLine()) != null)
         response.append(line + "\n");

      in.close();
      return response.toString();
   }
}

