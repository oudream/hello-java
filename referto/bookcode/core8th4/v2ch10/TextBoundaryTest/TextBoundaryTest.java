/**
 * @version 1.10 1999-10-14
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class TextBoundaryTest
{  public static void main(String[] args)
   {  JFrame frame = new TextBoundaryFrame();
      frame.show();
   }
}

class TextBoundaryFrame extends JFrame
{  public TextBoundaryFrame()
   {  setSize(400, 400);
      setTitle("TextBoundaryTest");

      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      ActionListener listener =
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  updateDisplay();
            }
         };

      JPanel p = new JPanel();
      addCheckBox(p, characterCheckBox, cbGroup, listener, false);
      addCheckBox(p, wordCheckBox, cbGroup, listener, false);
      addCheckBox(p, lineCheckBox, cbGroup, listener, false);
      addCheckBox(p, sentenceCheckBox, cbGroup, listener, true);

      getContentPane().setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      add(new JLabel("Locale"), gbc, 0, 0, 1, 1);
      gbc.anchor = GridBagConstraints.WEST;
      add(localeCombo, gbc, 1, 0, 1, 1);
      add(p, gbc, 0, 1, 2, 1);
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weighty = 100;
      add(new JScrollPane(inputText), gbc, 0, 2, 2, 1);
      add(new JScrollPane(outputText), gbc, 0, 3, 2, 1);


      locales = BreakIterator.getAvailableLocales();
      for (int i = 0; i < locales.length; i++)
         localeCombo.addItem(locales[i].getDisplayName());
      localeCombo.setSelectedItem(
         Locale.getDefault().getDisplayName());

      localeCombo.addActionListener(listener);

      inputText.setText("The quick, brown fox jump-ed\n"
        + "over the lazy \"dog.\" And then...what happened?");
      updateDisplay();
   }

   public void addCheckBox(Container p, JCheckBox checkBox,
      ButtonGroup g, ActionListener listener, boolean v)
   {  checkBox.setSelected(v);
      checkBox.addActionListener(listener);
      g.add(checkBox);
      p.add(checkBox);
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
      BreakIterator currentBreakIterator = null;
      if (characterCheckBox.isSelected())
         currentBreakIterator
            = BreakIterator.getCharacterInstance(currentLocale);
      else if (wordCheckBox.isSelected())
         currentBreakIterator
            = BreakIterator.getWordInstance(currentLocale);
      else if (lineCheckBox.isSelected())
         currentBreakIterator
            = BreakIterator.getLineInstance(currentLocale);
      else if (sentenceCheckBox.isSelected())
         currentBreakIterator
            = BreakIterator.getSentenceInstance(currentLocale);

      String text = inputText.getText();
      currentBreakIterator.setText(text);
      outputText.setText("");

      int from = currentBreakIterator.first();
      int to;
      while ((to = currentBreakIterator.next()) !=
         BreakIterator.DONE)
      {  outputText.append(text.substring(from, to) + "|");
         from = to;
      }
      outputText.append(text.substring(from));
   }

   private Locale[] locales;
   private BreakIterator currentBreakIterator;

   private JComboBox localeCombo = new JComboBox();
   private JTextArea inputText = new JTextArea(6, 40);
   private JTextArea outputText = new JTextArea(6, 40);
   private ButtonGroup cbGroup = new ButtonGroup();
   private JCheckBox characterCheckBox = new JCheckBox("Character");
   private JCheckBox wordCheckBox = new JCheckBox("Word");
   private JCheckBox lineCheckBox = new JCheckBox("Line");
   private JCheckBox sentenceCheckBox = new JCheckBox("Sentence");
}