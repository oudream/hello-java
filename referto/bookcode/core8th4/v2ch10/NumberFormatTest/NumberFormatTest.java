/**
 * @version 1.10 1999-10-14
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class NumberFormatTest
{  public static void main(String[] args)
   {  JFrame frame = new NumberFormatFrame();
      frame.show();
   }
}

class NumberFormatFrame extends JFrame
{  public NumberFormatFrame()
   {  setSize(400, 200);
      setTitle("NumberFormatTest");

      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      getContentPane().setLayout(new GridBagLayout());

      ActionListener listener =
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  updateDisplay();
            }
         };

      JPanel p = new JPanel();
      addCheckBox(p, numberCheckBox, cbGroup, listener, true);
      addCheckBox(p, currencyCheckBox, cbGroup, listener, false);
      addCheckBox(p, percentCheckBox, cbGroup, listener, false);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      add(new JLabel("Locale"), gbc, 0, 0, 1, 1);
      add(p, gbc, 1, 1, 1, 1);
      add(parseButton, gbc, 0, 2, 1, 1);
      gbc.anchor = GridBagConstraints.WEST;
      add(localeCombo, gbc, 1, 0, 1, 1);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      add(numberText, gbc, 1, 2, 1, 1);

      locales = NumberFormat.getAvailableLocales();
      for (int i = 0; i < locales.length; i++)
         localeCombo.addItem(locales[i].getDisplayName());
      localeCombo.setSelectedItem(
         Locale.getDefault().getDisplayName());
      currentNumber = 123456.78;
      updateDisplay();

      localeCombo.addActionListener(listener);

      parseButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  String s = numberText.getText();
               try
               {  Number n = currentNumberFormat.parse(s);
                  if (n != null)
                  {  currentNumber = n.doubleValue();
                     updateDisplay();
                  }
                  else
                  {  numberText.setText("Parse error: " + s);
                  }
               }
               catch(ParseException e)
               {  numberText.setText("Parse error: " + s);
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

   public void addCheckBox(Container p, JCheckBox checkBox,
      ButtonGroup g, ActionListener listener, boolean v)
   {  checkBox.setSelected(v);
      checkBox.addActionListener(listener);
      g.add(checkBox);
      p.add(checkBox);
   }

   public void updateDisplay()
   {  Locale currentLocale = locales[
         localeCombo.getSelectedIndex()];
      currentNumberFormat = null;
      if (numberCheckBox.isSelected())
         currentNumberFormat
            = NumberFormat.getNumberInstance(currentLocale);
      else if (currencyCheckBox.isSelected())
         currentNumberFormat
            = NumberFormat.getCurrencyInstance(currentLocale);
      else if (percentCheckBox.isSelected())
         currentNumberFormat
            = NumberFormat.getPercentInstance(currentLocale);
      String n = currentNumberFormat.format(currentNumber);
      numberText.setText(n);
   }

   private Locale[] locales;

   private double currentNumber;

   private JComboBox localeCombo = new JComboBox();
   private JButton parseButton = new JButton("Parse");
   private JTextField numberText = new JTextField(30);
   private JCheckBox numberCheckBox = new JCheckBox("Number");
   private JCheckBox currencyCheckBox = new JCheckBox("Currency");
   private JCheckBox percentCheckBox = new JCheckBox("Percent");
   private ButtonGroup cbGroup = new ButtonGroup();
   private NumberFormat currentNumberFormat;
}