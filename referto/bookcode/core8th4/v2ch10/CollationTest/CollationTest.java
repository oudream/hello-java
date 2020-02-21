/**
 * @version 1.00 1999-10-15
 * @author Cay Horstmann
 */

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class CollationTest
{  public static void main(String[] args)
   {  JFrame frame = new CollationFrame();
      frame.show();
   }
}

class CollationFrame extends JFrame
{  public CollationFrame()
   {  setSize(400, 400);
      setTitle("CollationTest");

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
      add(new JLabel("Strength"), gbc, 0, 1, 1, 1);
      add(new JLabel("Decomposition"), gbc, 0, 2, 1, 1);
      add(addButton, gbc, 0, 3, 1, 1);
      gbc.anchor = GridBagConstraints.WEST;
      add(localeCombo, gbc, 1, 0, 1, 1);
      add(strengthCombo, gbc, 1, 1, 1, 1);
      add(decompositionCombo, gbc, 1, 2, 1, 1);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      add(newWord, gbc, 1, 3, 1, 1);
      gbc.fill = GridBagConstraints.BOTH;
      add(new JScrollPane(sortedWords), gbc, 1, 4, 1, 1);

      locales = Collator.getAvailableLocales();
      for (int i = 0; i < locales.length; i++)
         localeCombo.addItem(locales[i].getDisplayName());
      localeCombo.setSelectedItem(
         Locale.getDefault().getDisplayName());

      strings.add("America");
      strings.add("ant");
      strings.add("Zulu");
      strings.add("zebra");
      strings.add("Ångstrom");
      strings.add("Angstrom");
      strings.add("Ant");
      updateDisplay();

      addButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  strings.add(newWord.getText());
               updateDisplay();
            }
         });

      ActionListener listener =
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  updateDisplay();
            }
         };

      localeCombo.addActionListener(listener);
      strengthCombo.addActionListener(listener);
      decompositionCombo.addActionListener(listener);
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

      currentCollator
         = Collator.getInstance(currentLocale);
      currentCollator.setStrength(strengthCombo.getValue());
      currentCollator.setDecomposition(
         decompositionCombo.getValue());

      Collections.sort(strings, currentCollator);

      sortedWords.setText("");
      for (int i = 0; i < strings.size(); i++)
      {  String s = (String)strings.get(i);
         if (i > 0
            && currentCollator.compare(s, strings.get(i - 1)) == 0)
         {  sortedWords.append("= ");
         }
         sortedWords.append(s + "\n");
      }
   }

   private Locale[] locales;
   private List strings = new ArrayList();
   private Collator currentCollator;

   private JComboBox localeCombo = new JComboBox();
   private EnumCombo strengthCombo
      = new EnumCombo(Collator.class,
        new String[] { "Primary", "Secondary", "Tertiary" });
   private EnumCombo decompositionCombo
      = new EnumCombo(Collator.class,
        new String[] { "Canonical Decomposition",
        "Full Decomposition", "No Decomposition" });
   private JTextField newWord = new JTextField(20);
   private JTextArea sortedWords = new JTextArea(10, 20);
   private JButton addButton = new JButton("Add");
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