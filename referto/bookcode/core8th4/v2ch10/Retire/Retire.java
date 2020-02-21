/**
 * @version 1.2 1999-10-13
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.*;

public class Retire extends JApplet
{  public void init()
   {  GridBagLayout gbl = new GridBagLayout();
      getContentPane().setLayout(gbl);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.weightx = 100;
      gbc.weighty = 0;

      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      add(languageLabel, gbc, 0, 0, 1, 1);
      add(savingsLabel, gbc, 0, 1, 1, 1);
      add(contribLabel, gbc, 2, 1, 1, 1);
      add(incomeLabel, gbc, 4, 1, 1, 1);
      add(currentAgeLabel, gbc, 0, 2, 1, 1);
      add(retireAgeLabel, gbc, 2, 2, 1, 1);
      add(deathAgeLabel, gbc, 4, 2, 1, 1);
      add(inflationPercentLabel, gbc, 0, 3, 1, 1);
      add(investPercentLabel, gbc, 2, 3, 1, 1);

      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.WEST;
      add(localeCombo, gbc, 1, 0, 2, 1);
      add(savingsField, gbc, 1, 1, 1, 1);
      add(contribField, gbc, 3, 1, 1, 1);
      add(incomeField, gbc, 5, 1, 1, 1);
      add(currentAgeField, gbc, 1, 2, 1, 1);
      add(retireAgeField, gbc, 3, 2, 1, 1);
      add(deathAgeField, gbc, 5, 2, 1, 1);
      add(inflationPercentField, gbc, 1, 3, 1, 1);
      add(investPercentField, gbc, 3, 3, 1, 1);

      computeButton.setName("computeButton");
      computeButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  getInfo();
               updateData();
               updateGraph();
            }
         });
      add(computeButton, gbc, 5, 3, 1, 1);

      gbc.weighty = 100;
      gbc.fill = GridBagConstraints.BOTH;
      add(retireCanvas, gbc, 0, 4, 4, 1);
      add(new JScrollPane(retireText), gbc, 4, 4, 2, 1);
      retireText.setEditable(false);
      retireText.setFont(new Font("Monospaced", Font.PLAIN, 10));

      info.setSavings(0);
      info.setContrib(9000);
      info.setIncome(60000);
      info.setCurrentAge(35);
      info.setRetireAge(65);
      info.setDeathAge(85);
      info.setInvestPercent(0.1);
      info.setInflationPercent(0.05);

      localeCombo.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  setCurrentLocale(localeCombo.getSelectedIndex());
            }
         });

      locales = new Locale[]
         { Locale.US, Locale.CHINA, Locale.GERMANY };

      int localeIndex = 0; // US locale is default selection

      for (int i = 0; i < locales.length; i++)
         // if current locale one of the choices, we'll select it
         if (getLocale().equals(locales[i])) localeIndex = i;

      setCurrentLocale(localeIndex);
   }

   public void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   public void setCurrentLocale(int localeIndex)
   {  currentLocale
         = locales[localeIndex];

      localeCombo.removeAllItems();
      for (int i = 0; i < locales.length; i++)
      {  String language = locales[i].getDisplayLanguage(currentLocale);
         localeCombo.addItem(language);
      }
      localeCombo.setSelectedIndex(localeIndex);

      res = ResourceBundle.getBundle("RetireResources",
         currentLocale);
      currencyFmt
         = NumberFormat.getCurrencyInstance(currentLocale);
      numberFmt
         = NumberFormat.getNumberInstance(currentLocale);
      percentFmt
         = NumberFormat.getPercentInstance(currentLocale);

      updateDisplay();
      updateInfo();
      updateData();
      updateGraph();
   }

   public void updateDisplay()
   {  languageLabel.setText(res.getString("language"));
      savingsLabel.setText(res.getString("savings"));
      contribLabel.setText(res.getString("contrib"));
      incomeLabel.setText(res.getString("income"));
      currentAgeLabel.setText(res.getString("currentAge"));
      retireAgeLabel.setText(res.getString("retireAge"));
      deathAgeLabel.setText(res.getString("deathAge"));
      inflationPercentLabel.setText
         (res.getString("inflationPercent"));
      investPercentLabel.setText
         (res.getString("investPercent"));
      computeButton.setText(res.getString("computeButton"));

      validate();
   }

   public void updateInfo()
   {  savingsField.setText(currencyFmt.format(info.getSavings()));
      contribField.setText(currencyFmt.format(info.getContrib()));
      incomeField.setText(currencyFmt.format(info.getIncome()));
      currentAgeField.setText(numberFmt.format(info.getCurrentAge()));
      retireAgeField.setText(numberFmt.format(info.getRetireAge()));
      deathAgeField.setText(numberFmt.format(info.getDeathAge()));
      investPercentField.setText(percentFmt.format(info.getInvestPercent()));
      inflationPercentField.setText(percentFmt.format(info.getInflationPercent()));
   }

   public void updateData()
   {  retireText.setText("");
      MessageFormat retireMsg = new MessageFormat("");
      retireMsg.setLocale(currentLocale);
      retireMsg.applyPattern(res.getString("retire"));

      for (int i = info.getCurrentAge(); i <= info.getDeathAge(); i++)
      {  Object[] args = { new Integer(i),
            new Double(info.getBalance(i)) };
         retireText.append(retireMsg.format(args) + "\n");
      }
   }

   public void updateGraph()
   {  retireCanvas.setColorPre((Color)res.getObject("colorPre"));
      retireCanvas.setColorGain((Color)res.getObject("colorGain"));
      retireCanvas.setColorLoss((Color)res.getObject("colorLoss"));
      retireCanvas.setInfo(info);
      repaint();
   }

   public void getInfo()
   {  try
      {  info.setSavings(currencyFmt.parse(savingsField.getText()).doubleValue());
         info.setContrib(currencyFmt.parse(contribField.getText()).doubleValue());
         info.setIncome(currencyFmt.parse(incomeField.getText()).doubleValue());
         info.setCurrentAge(numberFmt.parse(currentAgeField.getText()).intValue());
         info.setRetireAge(numberFmt.parse(retireAgeField.getText()).intValue());
         info.setDeathAge(numberFmt.parse(deathAgeField.getText()).intValue());
         info.setInvestPercent(percentFmt.parse(investPercentField.getText()).doubleValue());
         info.setInflationPercent(percentFmt.parse(inflationPercentField.getText()).doubleValue());
      }
      catch (ParseException exception)
      {
      }
   }

   private JTextField savingsField = new JTextField(10);
   private JTextField contribField = new JTextField(10);
   private JTextField incomeField = new JTextField(10);
   private JTextField currentAgeField = new JTextField(4);
   private JTextField retireAgeField = new JTextField(4);
   private JTextField deathAgeField = new JTextField(4);
   private JTextField inflationPercentField = new JTextField(6);
   private JTextField investPercentField = new JTextField(6);
   private JTextArea retireText = new JTextArea(10, 25);
   private RetireCanvas retireCanvas = new RetireCanvas();
   private JButton computeButton = new JButton();
   private JLabel languageLabel = new JLabel();
   private JLabel savingsLabel = new JLabel();
   private JLabel contribLabel = new JLabel();
   private JLabel incomeLabel = new JLabel();
   private JLabel currentAgeLabel = new JLabel();
   private JLabel retireAgeLabel = new JLabel();
   private JLabel deathAgeLabel = new JLabel();
   private JLabel inflationPercentLabel = new JLabel();
   private JLabel investPercentLabel = new JLabel();

   private RetireInfo info = new RetireInfo();

   private Locale[] locales;
   private Locale currentLocale;
   private JComboBox localeCombo = new JComboBox();
   private ResourceBundle res;
   private NumberFormat currencyFmt;
   private NumberFormat numberFmt;
   private NumberFormat percentFmt;
}

class RetireInfo
{  public double getBalance(int year)
   {  if (year < currentAge) return 0;
      else if (year == currentAge)
      {  age = year;
         balance = savings;
         return balance;
      }
      else if (year == age)
         return balance;
      if (year != age + 1)
         getBalance(year - 1);
      age = year;
      if (age < retireAge)
         balance += contrib;
      else
         balance -= income;
      balance = balance
         * (1 + (investPercent - inflationPercent));
      return balance;
   }

   public double getSavings()
   {  return savings;
   }

   public double getContrib()
   {  return contrib;
   }

   public double getIncome()
   {  return income;
   }

   public int getCurrentAge()
   {  return currentAge;
   }

   public int getRetireAge()
   {  return retireAge;
   }

   public int getDeathAge()
   {  return deathAge;
   }

   public double getInflationPercent()
   {  return inflationPercent;
   }

   public double getInvestPercent()
   {  return investPercent;
   }

   public void setSavings(double x)
   {  savings = x;
   }

   public void setContrib(double x)
   {  contrib = x;
   }

   public void setIncome(double x)
   {  income = x;
   }

   public void setCurrentAge(int x)
   {  currentAge = x;
   }

   public void setRetireAge(int x)
   {  retireAge = x;
   }

   public void setDeathAge(int x)
   {  deathAge = x;
   }

   public void setInflationPercent(double x)
   {  inflationPercent = x;
   }

   public void setInvestPercent(double x)
   {  investPercent = x;
   }

   private double savings;
   private double contrib;
   private double income;
   private int currentAge;
   private int retireAge;
   private int deathAge;
   private double inflationPercent;
   private double investPercent;

   private int age;
   private double balance;
}

class RetireCanvas extends JPanel
{  public RetireCanvas()
   {  setSize(400, 200);
   }

   public void setInfo(RetireInfo newInfo)
   {  info = newInfo;
   }

   public void paint(Graphics g)
   {  if (info == null) return;

      double minValue = 0;
      double maxValue = 0;
      int i;
      for (i = info.getCurrentAge(); i <= info.getDeathAge(); i++)
      {  double v = info.getBalance(i);
         if (minValue > v) minValue = v;
         if (maxValue < v) maxValue = v;
      }
      if (maxValue == minValue) return;

      int barWidth = getWidth() / (info.getDeathAge()
         - info.getCurrentAge() + 1);
      double scale = getHeight() / (maxValue - minValue);

      for (i = info.getCurrentAge(); i <= info.getDeathAge(); i++)
      {  int x1 = (i - info.getCurrentAge()) * barWidth + 1;
         int y1;
         double v = info.getBalance(i);
         int height;
         int yOrigin = (int)(maxValue * scale);

         if (v >= 0)
         {  y1 = (int)((maxValue - v) * scale);
            height = yOrigin - y1;
         }
         else
         {  y1 = yOrigin;
            height = (int)(-v * scale);
         }

         if (i < info.getRetireAge())
            g.setColor(colorPre);
         else if (v >= 0)
            g.setColor(colorGain);
         else
            g.setColor(colorLoss);
         g.fillRect(x1, y1, barWidth - 2, height);
         g.setColor(Color.black);
         g.drawRect(x1, y1, barWidth - 2, height);
      }
   }

   public void setColorPre(Color color)
   {  colorPre = color;
   }

   public void setColorGain(Color color)
   {  colorGain = color;
   }

   public void setColorLoss(Color color)
   {  colorLoss = color;
   }

   private RetireInfo info = null;


   private Color colorPre;
   private Color colorGain;
   private Color colorLoss;
}