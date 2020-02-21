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

public class ClassTree
{  public static void main(String[] args)
   {  JFrame frame = new ClassTreeFrame();
      frame.show();
   }
}

class ClassTreeFrame extends JFrame
   implements ActionListener
{  public ClassTreeFrame()
   {  setTitle("ClassTree");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // the root of the class tree is Object
      root = new DefaultMutableTreeNode(java.lang.Object.class);
      model = new DefaultTreeModel(root);
      tree = new JTree(model);

      // add this class to populate the tree with some data
      addClass(getClass());

      // set up node icons
      ClassNameTreeCellRenderer renderer
         = new ClassNameTreeCellRenderer();
      renderer.setClosedIcon(new ImageIcon("red-ball.gif"));
      renderer.setOpenIcon(new ImageIcon("yellow-ball.gif"));
      renderer.setLeafIcon(new ImageIcon("blue-ball.gif"));
      tree.setCellRenderer(renderer);

      Container contentPane = getContentPane();
      contentPane.add(new JScrollPane(tree), "Center");

      // new class names are typed into this text field
      textField = new JTextField();
      textField.addActionListener(this);
      contentPane.add(textField, "South");
   }

   public void actionPerformed(ActionEvent event)
   {  // add the class whose name is in the text field
      try
      {  String text = textField.getText();
         addClass(Class.forName(text));
         // clear text field to indicate success
         textField.setText("");
      }
      catch (ClassNotFoundException e)
      {  Toolkit.getDefaultToolkit().beep();
      }
   }

   public DefaultMutableTreeNode findUserObject(Object obj)
   {  // find the node containing a user object
      Enumeration e = root.breadthFirstEnumeration();
      while (e.hasMoreElements())
      {  DefaultMutableTreeNode node
            = (DefaultMutableTreeNode)e.nextElement();
         if (node.getUserObject().equals(obj))
            return node;
      }
      return null;
   }

   public DefaultMutableTreeNode addClass(Class c)
   {  // add a new class to the tree

      // skip non-class types
      if (c.isInterface() || c.isPrimitive()) return null;

      // if the class is already in the tree, return its node
      DefaultMutableTreeNode node = findUserObject(c);
      if (node != null) return node;

      // class isn't present--first add class parent recursively

      Class s = c.getSuperclass();

      DefaultMutableTreeNode parent;
      if (s == null)
         parent = root;
      else
         parent = addClass(s);

      // add the class as a child to the parent
      DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
      model.insertNodeInto(newNode, parent, parent.getChildCount());

      // make node visible
      TreePath path = new TreePath(model.getPathToRoot(newNode));
      tree.makeVisible(path);

      return newNode;
   }

   private DefaultMutableTreeNode root;
   private DefaultTreeModel model;
   private JTree tree;
   private JTextField textField;
}

class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer
{  public Component getTreeCellRendererComponent(JTree tree,
      Object value, boolean selected, boolean expanded,
      boolean leaf, int row, boolean hasFocus)
   {  super.getTreeCellRendererComponent(tree, value,
         selected, expanded, leaf, row, hasFocus);
      // get the user object
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
      Class c = (Class)node.getUserObject();

      // the first time, derive italic font from plain font
      if (plainFont == null)
      {  plainFont = getFont();
         /* the tree cell renderer is sometimes called with a
            label that has a null font
         */
         if (plainFont != null)
            italicFont = plainFont.deriveFont(Font.ITALIC);
      }

      // set font to italic if the class is abstract
      if ((c.getModifiers() & Modifier.ABSTRACT) == 0)
         setFont(plainFont);
      else
         setFont(italicFont);
      return this;
   }

   private Font plainFont = null;
   private Font italicFont = null;
};
