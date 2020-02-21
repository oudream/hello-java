/**
 * version 1.00 1999-08-27
 * author Cay Horstmann
 */

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProxySvr extends HttpServlet
{  public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException
   {  String query = null;

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      query = request.getParameter("URL");
      if (query == null)
      {  response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Missing URL parameter");
         return;
      }

      try
      {  query = URLDecoder.decode(query);
      }
      catch(Exception exception)
      {  response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "URL decode error " + exception);
         return;
      }

      try
      {  URL url = new URL(query);
         BufferedReader in = new BufferedReader(new
            InputStreamReader(url.openStream()));

         String line;
         while ((line = in.readLine()) != null)
            out.println(line);
         out.flush();
      }
      catch(IOException exception)
      {  response.sendError(HttpServletResponse.SC_NOT_FOUND,
            "Exception: " + exception);
      }
   }
}
