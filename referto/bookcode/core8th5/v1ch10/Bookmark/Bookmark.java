/**
 * @version 1.10 27 Jun 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.applet.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;

public class Bookmark extends JApplet 
   implements ListSelectionListener
{  public void init()
   {  int i = 1;
      String s;
      Vector v = new Vector();
      while ((s = getParameter("link_" + i)) != null)
      {  v.add(s);
         i++;
      }
      JList links = new JList(v);
      Container contentPane = getContentPane();
      contentPane.add(links);
      links.addListSelectionListener(this);
   }
   
   public void valueChanged(ListSelectionEvent evt)
   {  if (evt.getValueIsAdjusting()) return;
      JList source = (JList)evt.getSource();
      String arg = (String)source.getSelectedValue();
      try
      {  AppletContext context = getAppletContext();
         URL u = new URL(arg);
         context.showDocument(u, "right");
      } catch(Exception e)
      {  showStatus("Error " + e);
      }
   }
}

