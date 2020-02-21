/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderTest
{  public static void main(String[] args)
   {  JFrame frame = new SliderTestFrame();
      frame.show();
   }
}

class SliderTestFrame extends JFrame
{  public SliderTestFrame()
   {  setTitle("SliderTest");
      setSize(400, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // set up grid bag layout and constraints

      getContentPane().setLayout(new GridBagLayout());
      constraints = new GridBagConstraints();
      constraints.weighty = 100;
      constraints.gridwidth = 1;
      constraints.gridheight = 1;
      constraints.gridx = 0;
      constraints.gridy = 0;

      // add sliders with various decorations

      JSlider slider = new JSlider();
      addSlider(slider, "Plain");

      slider = new JSlider();
      slider.setPaintTicks(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(5);
      addSlider(slider, "Ticks");

      slider = new JSlider();
      slider.setPaintTicks(true);
      slider.setSnapToTicks(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(5);
      addSlider(slider, "Snap to ticks");

      slider = new JSlider();
      slider.setPaintTicks(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(5);
      slider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
      addSlider(slider, "Filled");

      slider = new JSlider();
      slider.setPaintTicks(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(5);
      slider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
      slider.setInverted(true);
      addSlider(slider, "Inverted");

      slider = new JSlider();
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(5);
      addSlider(slider, "Labels");

      slider = new JSlider();
      slider.setPaintLabels(true);
      slider.setPaintTicks(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(5);

      Hashtable labelTable = new Hashtable();
      labelTable.put(new Integer(0), new JLabel("A"));
      labelTable.put(new Integer(20), new JLabel("B"));
      labelTable.put(new Integer(40), new JLabel("C"));
      labelTable.put(new Integer(60), new JLabel("D"));
      labelTable.put(new Integer(80), new JLabel("E"));
      labelTable.put(new Integer(100), new JLabel("F"));

      slider.setLabelTable(labelTable);
      addSlider(slider, "Custom labels");

      slider = new JSlider();
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      slider.setSnapToTicks(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(20);

      labelTable = new Hashtable();

      // add card images

      labelTable.put(new Integer(0),
         new JLabel(new ImageIcon("9h.gif")));
      labelTable.put(new Integer(20),
         new JLabel(new ImageIcon("10h.gif")));
      labelTable.put(new Integer(40),
         new JLabel(new ImageIcon("jh.gif")));
      labelTable.put(new Integer(60),
         new JLabel(new ImageIcon("qh.gif")));
      labelTable.put(new Integer(80),
         new JLabel(new ImageIcon("kh.gif")));
      labelTable.put(new Integer(100),
         new JLabel(new ImageIcon("ah.gif")));

      slider.setLabelTable(labelTable);
      addSlider(slider, "Icon labels");
   }

   public void addSlider(JSlider s, String description)
   {  // create text field that is shown next to slider
      final TextField textField = new TextField(4);

      // update text field when the slider value changes
      s.addChangeListener(new ChangeListener()
         {  public void stateChanged(ChangeEvent event)
            {  JSlider source = (JSlider)event.getSource();
               textField.setText("" + source.getValue());
            }
         });

      // add three components into the next row

      constraints.gridx = 0;
      constraints.anchor = GridBagConstraints.WEST;
      constraints.fill = GridBagConstraints.NONE;
      constraints.weightx = 0;
      getContentPane().add(new JLabel(description), constraints);

      constraints.gridx++;
      constraints.anchor = GridBagConstraints.CENTER;
      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.weightx = 100;
      getContentPane().add(s, constraints);

      constraints.gridx++;
      constraints.anchor = GridBagConstraints.WEST;
      constraints.fill = GridBagConstraints.NONE;
      constraints.weightx = 0;
      getContentPane().add(textField, constraints);

      // advance row
      constraints.gridy++;
   }

   private GridBagConstraints constraints;
}