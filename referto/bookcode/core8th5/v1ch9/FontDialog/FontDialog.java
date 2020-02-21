/**
 * @version 1.20 21 Aug 1997
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FontDialog extends JFrame
   implements ActionListener, ListSelectionListener
{  public FontDialog()
   {  setTitle("FontDialog");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
   
      Container contentPane = getContentPane();
      GridBagLayout gbl = new GridBagLayout();
      contentPane.setLayout(gbl);
      
      style = new JList(new String[] 
         {  "Serif", "SansSerif", "Monospaced", 
            "Dialog", "DialogInput" 
         });
      style.setSelectedIndex(0);
                        
      bold = new JCheckBox("Bold");
      italic = new JCheckBox("Italic");
      JLabel label = new JLabel("Size: ");
      size = new JTextField("10", 2);
      sample = new JTextField();
      sample.setEditable(false);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = 0;
      gbc.weighty = 100;
      add(style, gbc, 0, 0, 1, 3);
      gbc.weightx = 100;
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.CENTER;
      add(bold, gbc, 1, 0, 2, 1);
      add(italic, gbc, 1, 1, 2, 1);
      add(label, gbc, 1, 2, 1, 1);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      add(size, gbc, 2, 2, 1, 1);
      gbc.anchor = GridBagConstraints.SOUTH;
      gbc.weighty = 0;
      add(sample, gbc, 0, 3, 4, 1);
      sample.setText("The quick brown fox");

      bold.addActionListener(this);
      italic.addActionListener(this);
      style.addListSelectionListener(this);
      size.addActionListener(this);
   }
   
   public void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   public void  valueChanged(ListSelectionEvent evt)
   {  updateFont();
   }

   public void actionPerformed(ActionEvent evt)
   {  updateFont();
   }
   
   public void updateFont()
   {  Font font = 
         new Font((String)style.getSelectedValue(),
            (bold.isSelected() ? Font.BOLD : 0)
               + (italic.isSelected() ? Font.ITALIC : 0),
            Integer.parseInt(size.getText()));
      sample.setFont(font);
      repaint();
   }
 
   public static void main(String[] args)
   {  Frame f = new FontDialog();
      f.show();  
   }
   
   private JList style;
   private JCheckBox bold;
   private JCheckBox italic;
   private JTextField size;
   private JTextField sample;
}
