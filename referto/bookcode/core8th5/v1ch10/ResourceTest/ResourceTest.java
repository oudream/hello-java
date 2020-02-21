/**
 * @version 1.20 2 Oct 1998
 * @author Cay Horstmann
 */

import java.io.*;
import java.awt.*;
import javax.swing.*;

public class ResourceTest extends JApplet
{  public void init()
   {  Container contentPane = getContentPane();
      contentPane.add(new AboutPanel());
   }
}

class AboutPanel extends JPanel
{  public AboutPanel()
   {  JTextArea ta = new JTextArea();
      add(ta);
                
      try
      {  InputStream in = AboutPanel.class.
            getResourceAsStream("about.txt");
         BufferedReader br = new BufferedReader(new
            InputStreamReader(in));
         String line;
         while ((line = br.readLine()) != null)
            ta.append(line + "\n");
      } catch(IOException e) {}
   }
}

