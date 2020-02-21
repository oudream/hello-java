/**
 * @version 1.10 1999-09-29
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChartBeanCustomizer extends JTabbedPane
   implements Customizer
{  public ChartBeanCustomizer()
   {  data = new JTextArea();
      JPanel dataPane = new JPanel();
      dataPane.setLayout(new BorderLayout());
      dataPane.add(new JScrollPane(data), "Center");
      JButton dataButton = new JButton("Set data");
      dataButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  setData(data.getText());
            }
         });
      JPanel p = new JPanel();
      p.add(dataButton);
      dataPane.add(p, "South");

      JPanel colorPane = new JPanel();
      colorPane.setLayout(new BorderLayout());

      normal = new JCheckBox("Normal", true);
      inverse = new JCheckBox("Inverse", false);
      p = new JPanel();
      p.add(normal);
      p.add(inverse);
      ButtonGroup g = new ButtonGroup();
      g.add(normal);
      g.add(inverse);
      normal.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  setInverse(false);
            }
         });

      inverse.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  setInverse(true);
            }
         });

      colorEditor
         = PropertyEditorManager.findEditor(Color.class);
      colorEditor.addPropertyChangeListener(
         new PropertyChangeListener()
         {  public void propertyChange(PropertyChangeEvent
               evt)
            {  setGraphColor((Color)colorEditor.getValue());
            }
         });

      colorPane.add(colorEditor.getCustomEditor(), "North");
      colorPane.add(p, "South");

      JPanel titlePane = new JPanel();
      titlePane.setLayout(new BorderLayout());

      g = new ButtonGroup();
      position = new JCheckBox[3];
      position[0] = new JCheckBox("Left", false);
      position[1] = new JCheckBox("Center", true);
      position[2] = new JCheckBox("Right", false);

      p = new JPanel();
      for (int i = 0; i < position.length; i++)
      {  final int value = i;
         p.add(position[i]);
         g.add(position[i]);
         position[i].addActionListener(
            new ActionListener()
            {  public void actionPerformed(ActionEvent event)
               {  setTitlePosition(value);
               }
            });
      }

      titleField = new JTextField();
      titleField.getDocument().addDocumentListener(
         new DocumentListener()
         {  public void changedUpdate(DocumentEvent evt)
            {  setTitle(titleField.getText());
            }
            public void insertUpdate(DocumentEvent evt)
            {  setTitle(titleField.getText());
            }
            public void removeUpdate(DocumentEvent evt)
            {  setTitle(titleField.getText());
            }
         });

      titlePane.add(titleField, "North");
      titlePane.add(p, "South");
      addTab("Color", colorPane);
      addTab("Title", titlePane);
      addTab("Data", dataPane);

      addChangeListener( // workaround for a JTabbedPane bug in JDK 1.2
         new ChangeListener()
         {  public void stateChanged(ChangeEvent event)
            {  validate();
            }
         });
   }

   public void setData(String s)
   {  StringTokenizer tokenizer = new StringTokenizer(s);

      int i = 0;
      double[] values = new double[tokenizer.countTokens()];
      while (tokenizer.hasMoreTokens())
      {  String token = tokenizer.nextToken();
         try
         {  values[i] = Double.parseDouble(token);
            i++;
         }
         catch (NumberFormatException exception)
         {
         }
      }
      setValues(values);
   }

   public void setTitle(String newValue)
   {  if (bean == null) return;
      String oldValue = bean.getTitle();
      bean.setTitle(newValue);
      firePropertyChange("title", oldValue, newValue);
   }

   public void setTitlePosition(int i)
   {  if (bean == null) return;
      Integer oldValue = new Integer(bean.getTitlePosition());
      Integer newValue = new Integer(i);
      bean.setTitlePosition(i);
      firePropertyChange("titlePosition", oldValue, newValue);
   }

   public void setInverse(boolean b)
   {  if (bean == null) return;
      Boolean oldValue = new Boolean(bean.isInverse());
      Boolean newValue = new Boolean(b);
      bean.setInverse(b);
      firePropertyChange("inverse", oldValue, newValue);
   }

   public void setValues(double[] newValue)
   {  if (bean == null) return;
      double[] oldValue = bean.getValues();
      bean.setValues(newValue);
      firePropertyChange("values", oldValue, newValue);
   }

   public void setGraphColor(Color newValue)
   {  if (bean == null) return;
      Color oldValue = bean.getGraphColor();
      bean.setGraphColor(newValue);
      firePropertyChange("graphColor", oldValue, newValue);
   }

   public void setObject(Object obj)
   {  bean = (ChartBean)obj;

      data.setText("");
      double[] values = bean.getValues();
      for (int i = 0; i < values.length; i++)
         data.append(values[i] + "\n");

      normal.setSelected(!bean.isInverse());
      inverse.setSelected(bean.isInverse());

      titleField.setText(bean.getTitle());

      for (int i = 0; i < position.length; i++)
         position[i].setSelected(i == bean.getTitlePosition());

      colorEditor.setValue(bean.getGraphColor());
   }

   public Dimension getPreferredSize()
   {  return new Dimension(200, 120);
   }

   private ChartBean bean;
   private PropertyEditor colorEditor;

   private JTextArea data;
   private JCheckBox normal;
   private JCheckBox inverse;
   private JCheckBox[] position;
   private JTextField titleField;
}

