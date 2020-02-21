/**
 * @version 1.10 1999-10-14
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class DateFormatTest
{  public static void main(String[] args)
   {  JFrame frame = new DateFormatFrame();
      frame.show();
   }
}

class DateFormatFrame extends JFrame
{  public DateFormatFrame()
   {  setSize(400, 200);
      setTitle("DateFormatTest");

      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      getContentPane().setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      add(new JLabel("Locale"), gbc, 0, 0, 1, 1);
      add(new JLabel("Date style"), gbc, 0, 1, 1, 1);
      add(new JLabel("Time style"), gbc, 2, 1, 1, 1);
      add(new JLabel("Date"), gbc, 0, 2, 1, 1);
      add(new JLabel("Time"), gbc, 0, 3, 1, 1);
      gbc.anchor = GridBagConstraints.WEST;
      add(localeCombo, gbc, 1, 0, 2, 1);
      add(dateStyleCombo, gbc, 1, 1, 1, 1);
      add(timeStyleCombo, gbc, 3, 1, 1, 1);
      add(dateParseButton, gbc, 3, 2, 1, 1);
      add(timeParseButton, gbc, 3, 3, 1, 1);
      add(lenientCheckbox, gbc, 0, 4, 2, 1);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      add(dateText, gbc, 1, 2, 2, 1);
      add(timeText, gbc, 1, 3, 2, 1);

      locales = DateFormat.getAvailableLocales();
      for (int i = 0; i < locales.length; i++)
         localeCombo.addItem(locales[i].getDisplayName());
      localeCombo.setSelectedItem(
         Locale.getDefault().getDisplayName());
      currentDate = new Date();
      currentTime = new Date();
      updateDisplay();

      ActionListener listener =
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  updateDisplay();
            }
         };

      localeCombo.addActionListener(listener);
      dateStyleCombo.addActionListener(listener);
      timeStyleCombo.addActionListener(listener);

      dateParseButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  String d = dateText.getText();
               try
               {  currentDateFormat.setLenient
                     (lenientCheckbox.isSelected());
                  Date date = currentDateFormat.parse(d);
                  currentDate = date;
                  updateDisplay();
               }
               catch(ParseException e)
               {  dateText.setText("Parse error: " + d);
               }
               catch(IllegalArgumentException e)
               {  dateText.setText("Argument error: " + d);
               }
            }
         });

      timeParseButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  String t = timeText.getText();
               try
               {  currentDateFormat.setLenient
                     (lenientCheckbox.isSelected());
                  Date date = currentTimeFormat.parse(t);
                  currentTime = date;
                  updateDisplay();
               }
               catch(ParseException e)
               {  timeText.setText("Parse error: " + t);
               }
               catch(IllegalArgumentException e)
               {  timeText.setText("Argument error: " + t);
               }
            }
         });
   }

   public void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   public void updateDisplay()
   {  Locale currentLocale = locales[
         localeCombo.getSelectedIndex()];
      int dateStyle = dateStyleCombo.getValue();
      currentDateFormat
         = DateFormat.getDateInstance(dateStyle,
         currentLocale);
      String d = currentDateFormat.format(currentDate);
      dateText.setText(d);
      int timeStyle = timeStyleCombo.getValue();
      currentTimeFormat
         = DateFormat.getTimeInstance(timeStyle,
         currentLocale);
      String t = currentTimeFormat.format(currentTime);
      timeText.setText(t);
   }

   private Locale[] locales;

   private Date currentDate;
   private Date currentTime;
   private DateFormat currentDateFormat;
   private DateFormat currentTimeFormat;

   private JComboBox localeCombo = new JComboBox();
   private EnumCombo dateStyleCombo
      = new EnumCombo(DateFormat.class,
        new String[] { "Default", "Full", "Long",
        "Medium", "Short" });
   private EnumCombo timeStyleCombo
      = new EnumCombo(DateFormat.class,
        new String[] { "Default", "Full", "Long",
        "Medium", "Short" });
   private JButton dateParseButton = new JButton("Parse date");
   private JButton timeParseButton = new JButton("Parse time");
   private JTextField dateText = new JTextField(30);
   private JTextField timeText = new JTextField(30);
   private JTextField parseText = new JTextField(30);
   private JCheckBox lenientCheckbox
      = new JCheckBox("Parse lenient", true);
}

class EnumCombo extends JComboBox
{  public EnumCombo(Class cl, String[] labels)
   {  for (int i = 0; i < labels.length; i++)
      {  String label = labels[i];
         String name = label.toUpperCase().replace(' ', '_');
         int value = 0;
         try
         {  java.lang.reflect.Field f = cl.getField(name);
            value = f.getInt(cl);
         }
         catch(Exception e)
         {  label = "(" + label + ")";
         }
         table.put(label, new Integer(value));
         addItem(label);
      }
      setSelectedItem(labels[0]);
   }

   public int getValue()
   {  return ((Integer)table.get(getSelectedItem())).intValue();
   }

   private Map table = new HashMap();
}