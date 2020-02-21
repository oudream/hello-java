/**
 * @version 1.10 25 Mar 1998
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuTest extends JFrame
   implements ActionListener, MenuListener
{  public MenuTest()
   {  setTitle("MenuTest");
      setSize(400, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      JMenuBar mbar = new JMenuBar();
      setJMenuBar(mbar);

      // demonstrate enabled/disabled items

      JMenu fileMenu = new JMenu("File");
      fileMenu.addMenuListener(this);

      // demonstrate accelerators

      JMenuItem openItem = new JMenuItem("Open");
      openItem.setAccelerator
         (KeyStroke.getKeyStroke(KeyEvent.VK_O,
         InputEvent.CTRL_MASK));
      saveItem = new JMenuItem("Save");
      saveItem.setAccelerator
         (KeyStroke.getKeyStroke(KeyEvent.VK_S,
         InputEvent.CTRL_MASK));
      saveAsItem = new JMenuItem("Save As");

      mbar.add(makeMenu(fileMenu,
         new Object[]
         {  "New",
            openItem,
            null,
            saveItem,
            saveAsItem,
            null,
            "Exit"
         },
         this));

      // demonstrate check box and radio button menus

      readonlyItem = new JCheckBoxMenuItem("Read-only");
      ButtonGroup group = new ButtonGroup();
      JRadioButtonMenuItem insertItem
         = new JRadioButtonMenuItem("Insert");
      insertItem.setSelected(true);
      JRadioButtonMenuItem overtypeItem
         = new JRadioButtonMenuItem("Overtype");
      group.add(insertItem);
      group.add(overtypeItem);

      // demonstrate icons and nested menus

      mbar.add(makeMenu("Edit",
         new Object[]
         {  new JMenuItem("Cut",
               new ImageIcon("cut.gif")),
            new JMenuItem("Copy",
               new ImageIcon("copy.gif")),
            new JMenuItem("Paste",
               new ImageIcon("paste.gif")),
            null,
            makeMenu("Options",
               new Object[]
               {  readonlyItem,
                  null,
                  insertItem,
                  overtypeItem
               },
               this)
         },
         this));

      // demonstrate mnemonics

      JMenu helpMenu = new JMenu("Help");
      helpMenu.setMnemonic('H');

      mbar.add(makeMenu(helpMenu,
         new Object[]
         {  new JMenuItem("Index", 'I'),
            new JMenuItem("About", 'A')
         },
         this));

      // demonstrate pop-ups

      popup = makePopupMenu(
         new Object[]
         {  "Cut",
            "Copy",
            "Paste"
         },
         this);

      getContentPane().addMouseListener(new MouseAdapter()
         {  public void mouseReleased(MouseEvent evt)         
            {  if (evt.isPopupTrigger())
                  popup.show(evt.getComponent(),
                     evt.getX(), evt.getY());
            }
         });
   }

   public void actionPerformed(ActionEvent evt)
   {  String arg = evt.getActionCommand();
      System.out.println(arg);
      if(arg.equals("Exit"))
         System.exit(0);
   }

   public void menuSelected(MenuEvent evt)
   {  saveItem.setEnabled(!readonlyItem.isSelected());
      saveAsItem.setEnabled(!readonlyItem.isSelected());
   }

   public void menuDeselected(MenuEvent evt)
   {
   }

   public void menuCanceled(MenuEvent evt)
   {
   }

   public static JMenu makeMenu(Object parent,
      Object[] items, Object target)
   {  JMenu m = null;
      if (parent instanceof JMenu)
         m = (JMenu)parent;
      else if (parent instanceof String)
         m = new JMenu((String)parent);
      else
         return null;

      for (int i = 0; i < items.length; i++)
      {  if (items[i] == null)
            m.addSeparator();
         else
            m.add(makeMenuItem(items[i], target));
      }

      return m;
   }

   public static JMenuItem makeMenuItem(Object item,
      Object target)
   {  JMenuItem r = null;
      if (item instanceof String)
         r = new JMenuItem((String)item);
      else if (item instanceof JMenuItem)
         r = (JMenuItem)item;
      else return null;

      if (target instanceof ActionListener)
         r.addActionListener((ActionListener)target);
      return r;
   }

   public static JPopupMenu makePopupMenu
      (Object[] items, Object target)
   {  JPopupMenu m = new JPopupMenu();

      for (int i = 0; i < items.length; i++)
      {  if (items[i] == null)
            m.addSeparator();
         else
            m.add(makeMenuItem(items[i], target));
      }

      return m;
   }

   public static void main(String[] args)
   {  Frame f = new MenuTest();
      f.show();
   }

   private JMenuItem saveItem;
   private JMenuItem saveAsItem;
   private JCheckBoxMenuItem readonlyItem;
   private JPopupMenu popup;
}