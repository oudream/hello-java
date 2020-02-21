/**
 * @version 1.20 1999-09-28
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.beans.beancontext.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class SpinBeanCustomizer extends JPanel
   implements Customizer
{  public SpinBeanCustomizer()
   {  setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.weightx = 0;
      gbc.weighty = 100;
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      add(new JLabel("Buddy"), gbc, 0, 0, 1, 1);
      add(new JLabel("Property"), gbc, 0, 1, 1, 1);
      gbc.weightx = 100;
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.BOTH;
      buddyModel = new DefaultListModel();
      propModel = new DefaultListModel();
      buddyList = new JList(buddyModel);
      propList = new JList(propModel);
      add(new JScrollPane(buddyList), gbc, 1, 0, 1, 1);
      add(new JScrollPane(propList), gbc, 1, 1, 1, 1);
      JButton setButton = new JButton("Set Buddy");
      JPanel p = new JPanel();
      p.add(setButton);
      add(p, gbc, 0, 2, 2, 1);

      buddyList.addListSelectionListener(
         new ListSelectionListener()
         {  public void valueChanged(ListSelectionEvent event)
            {  findBuddyMethods();
            }
         });

      setButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  int buddyIndex = buddyList.getSelectedIndex();
               if (buddyIndex < 0) return;
               int propIndex = propList.getSelectedIndex();
               if (propIndex < 0) return;
               bean.setBuddy(buddies[buddyIndex], props[propIndex]);
            }
         });
   }

   public void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      add(c, gbc);
   }

   public void findBuddyMethods()
   {  int buddyIndex = buddyList.getSelectedIndex();
      if (buddyIndex < 0) return;
      Component buddy = buddies[buddyIndex];
      propModel.removeAllElements();
      try
      {  BeanInfo info
            = Introspector.getBeanInfo(buddy.getClass());
         props = info.getPropertyDescriptors();
         int j = 0;
         for (int i = 0; i < props.length; i++)
         {  Class propertyType = props[i].getPropertyType();
            if (int.class.equals(propertyType))
            {  String name = props[i].getName();
               propModel.addElement(name);
               props[j++] = props[i];
            }
         }
      }
      catch(IntrospectionException e){}
   }

   public Dimension getPreferredSize()
   {  return new Dimension(300, 200);
   }

   public void setObject(Object obj)
   {  bean = (SpinBean)obj;
      BeanContext context = bean.getBeanContextProxy().getBeanContext();
      buddies = new Component[context.size()];
      buddyModel.removeAllElements();
      Iterator iter = context.iterator();
      int i = 0;
      while (iter.hasNext())
      {  Object buddy = iter.next();
         if (buddy instanceof Component)
         {  buddies[i] = (Component)buddy;
            String className = buddies[i].getClass().getName();
            buddyModel.addElement(className);
            i++;
         }
      }
   }

   public void addPropertyChangeListener
      (PropertyChangeListener l)
   {  support.addPropertyChangeListener(l);
   }

   public void removePropertyChangeListener
      (PropertyChangeListener l)
   {  support.removePropertyChangeListener(l);
   }

   private SpinBean bean;
   private PropertyChangeSupport support
      = new PropertyChangeSupport(this);
   private JList buddyList;
   private JList propList;
   private DefaultListModel buddyModel;
   private DefaultListModel propModel;
   private PropertyDescriptor[] props;
   private Component[] buddies;
}

