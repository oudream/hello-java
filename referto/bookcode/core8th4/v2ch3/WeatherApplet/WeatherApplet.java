/**
 * version 1.10 1999-08-27
 * author Cay Horstmann
 * swingetized Dan Gordon
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class WeatherApplet extends JApplet
   implements ActionListener
{  public void init()
   {  Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());

      // Set up the lists of choices for states and reports
      JPanel listPanel = new JPanel();
      state = makeList(states, 6, listPanel);
      report = makeList(reports, 6, listPanel);
      contentPane.add(listPanel, "North");

      // Add the text area
      weather = new JTextArea(20, 80);
      weather.setFont(new Font("Courier", Font.PLAIN, 12));

      // Add the report button
      contentPane.add(new JScrollPane(weather), "Center");
      JPanel buttonPanel = new JPanel();
      JButton reportButton = new JButton("Get report");
      reportButton.addActionListener(this);
      buttonPanel.add(reportButton);
      contentPane.add(buttonPanel, "South");
   }

   public JList makeList(final String[][] items, int visibleRows,
      Container parent)
   {  JList list = new JList(new AbstractListModel()
         {  public Object getElementAt(int i)
            {  return items[i][0];
            }

            public int getSize()
            {  return items.length;
            }
         });
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      list.setVisibleRowCount(visibleRows);
      parent.add(new JScrollPane(list));
      return list;
   }


   public String getItem(JList list, String[][] items)
   {  return items[list.getSelectedIndex()][1];
   }

   public void actionPerformed(ActionEvent evt)
   {  weather.setText("");
      getWeather(getItem(state, states), getItem(report, reports));
   }

   // Put together the URL query and go get the data from it
   public void getWeather(String state, String report)
   {  String r = new String();
      try
      {  String queryBase = getParameter("queryBase");
         String query
            = queryBase + state + "/" + report + ".html";
         URL url = new URL(query);
         BufferedReader in = new BufferedReader(new
            InputStreamReader(url.openStream()));

         String line;
         while ((line = in.readLine()) != null)
            weather.append(removeTags(line) + "\n");
      }
      catch(IOException e)
      {  showStatus("Error " + e);
      }
   }

   public static String removeTags(String s)
   {  while (true)
      {  int lb = s.indexOf('<');
         if (lb < 0) return s;
         int rb = s.indexOf('>', lb);
         if (rb < 0) return s;
         s = s.substring(0, lb) + " " + s.substring(rb + 1);
      }
   }

   private JTextArea weather;
   private JList state;
   private JList report;

   private String[][] states =
      {  { "Alabama", "al" },
         { "Alaska", "ak" },
         { "Arizona", "az" },
         { "Arkansas", "ar" },
         { "California", "ca" },
         { "Colorado", "co" },
         { "Connecticut", "ct" },
         { "Delaware", "de" },
         { "Florida", "fl" },
         { "Georgia", "ga" },
         { "Hawaii", "hi" },
         { "Idaho", "id" },
         { "Illinois", "il" },
         { "Indiana", "in" },
         { "Iowa", "ia" },
         { "Kansas", "ks" },
         { "Kentucky", "ky" },
         { "Lousisiana", "la" },
         { "Maine", "me" },
         { "Maryland", "md" },
         { "Massachusetts", "ma" },
         { "Michigan", "mi" },
         { "Minnesota", "mn" },
         { "Mississippi", "ms" },
         { "Missouri", "mo" },
         { "Montana", "mt" },
         { "Nebraska", "ne" },
         { "Nevada", "nv" },
         { "New Hampshire", "nh" },
         { "New Jersey", "nj" },
         { "New Mexico", "nm" },
         { "New York", "ny" },
         { "North Carolina", "nc" },
         { "North Dakota", "nd" },
         { "Ohio", "oh" },
         { "Oklahoma", "ok" },
         { "Oregon", "or" },
         { "Pennsylvania", "pa" },
         { "Rhode Island", "ri" },
         { "South Carolina", "sc" },
         { "South Dakota", "sd" },
         { "Tennessee", "tn" },
         { "Texas", "tx" },
         { "Utah", "ut" },
         { "Vermont", "vt" },
         { "Virginia", "va" },
         { "Washington", "wa" },
         { "West Virginia", "wv" },
         { "Wisconsin", "wi" },
         { "Wyoming", "wy" }
      };

   private String[][] reports =
      {  { "Hourly (State Weather Roundup)", "hourly" },
         { "State Forecast", "state" },
         { "Zone Forecast", "zone" },
         { "Short Term (NOWCASTS)", "shortterm" },
         { "Forecast Discussion", "discussion" },
         { "Weather Summary", "summary" },
         { "Public Information", "public" },
         { "Climate Data", "climate" },
         { "Hydrological Data", "hydro" },
         { "Watches", "watches" },
         { "Special Weather Statements", "special" },
         { "Warnings and Advisories", "allwarnings" }
      };
}



