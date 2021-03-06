/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class ObjectInspectorTest
{  public static void main(String[] args)
   {  JFrame frame = new ObjectInspectorFrame();
      frame.show();
   }
}

class ObjectInspectorFrame extends JFrame
{  public ObjectInspectorFrame()
   {  setTitle("ObjectInspectorTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // we inspect this frame object

      Variable v = new Variable(getClass(), "this", this);
      ObjectTreeModel model = new ObjectTreeModel();
      model.setRoot(v);

      // construct and show tree

      tree = new JTree(model);
      JScrollPane scrollPane = new JScrollPane(tree);
      Container contentPane = getContentPane();
      contentPane.add(scrollPane, "Center");
   }

   private JTree tree;
}

class ObjectTreeModel implements TreeModel
{  public ObjectTreeModel()
   {  root = null;
   }

   public void setRoot(Variable v)
   {  Variable oldRoot = v;
      root = v;
      fireTreeStructureChanged(oldRoot);
   }

   public Object getRoot()
   {  return root;
   }

   public int getChildCount(Object parent)
   {  return ((Variable)parent).getFields().size();
   }

   public Object getChild(Object parent, int index)
   {  ArrayList fields = ((Variable)parent).getFields();
      Field f = (Field)fields.get(index);
      Object parentValue = ((Variable)parent).getValue();
      try
      {  return new Variable(f.getType(), f.getName(),
            f.get(parentValue));
      }
      catch(IllegalAccessException e)
      {  return null;
      }
   }

   public int getIndexOfChild(Object parent, Object child)
   {  int n = getChildCount(parent);
      for (int i = 0; i < n; i++)
         if (getChild(parent, i).equals(child))
            return i;
      return -1;
   }

   public boolean isLeaf(Object node)
   {  return getChildCount(node) == 0;
   }

   public void valueForPathChanged(TreePath path, Object newValue)
   {}

   public void addTreeModelListener(TreeModelListener l)
   {  listenerList.add(TreeModelListener.class, l);
   }

   public void removeTreeModelListener(TreeModelListener l)
   {  listenerList.remove(TreeModelListener.class, l);
   }

   protected void fireTreeStructureChanged(Object oldRoot)
   {  TreeModelEvent event
         = new TreeModelEvent(this, new Object[] {oldRoot});
      Object[] listeners = listenerList.getListenerList();
      for (int i = listeners.length - 2; i >= 0; i -= 2)
         ((TreeModelListener)listeners[i+1]).
               treeStructureChanged(event);
   }

   private Variable root;
   private EventListenerList listenerList
      = new EventListenerList();
}

class Variable
{  public Variable(Class aType, String aName, Object aValue)
   {  type = aType;
      name = aName;
      value = aValue;
      fields = new ArrayList();

      /* find all fields if we have a class type
         except we don't expand strings and null values
      */

      if (!type.isPrimitive() && !type.isArray() &&
         !type.equals(String.class) && value != null)
      {  // get fields from the class and all superclasses
         for (Class c = value.getClass(); c != null;
            c = c.getSuperclass())
         {  Field[] f = c.getDeclaredFields();
            AccessibleObject.setAccessible(f, true);

            // get all nonstatic fields
            for (int i = 0; i < f.length; i++)
               if ((f[i].getModifiers() & Modifier.STATIC) == 0)
                  fields.add(f[i]);
         }
      }
   }

   public Object getValue()
   {  return value;
   }

   public ArrayList getFields()
   {  return fields;
   }

   public String toString()
   {  String r = type + " " + name;
      if (type.isPrimitive())
         r += "=" + value;
      else if (type.equals(String.class))
         r += "=" + value;
      else if (value == null)
         r += "=null";
      return r;
   }

   private Class type;
   private String name;
   private Object value;
   private ArrayList fields;
}