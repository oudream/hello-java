/**
 * @version 1.10 1999-10-04
 * @author Cay Horstmann
 */

import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassLoaderTest
{  public static void main(String[] args)
   {  Frame f = new ClassLoaderFrame();
      f.show();
   }
}

class ClassLoaderFrame extends JFrame
{  public ClassLoaderFrame()
   {  setTitle("ClassLoaderTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      getContentPane().setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.weightx = 0;
      gbc.weighty = 100;
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      add(new JLabel("Class"), gbc, 0, 0, 1, 1);
      add(new JLabel("Key"), gbc, 0, 1, 1, 1);
      gbc.weightx = 100;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.WEST;
      add(nameField, gbc, 1, 0, 1, 1);
      add(keyField, gbc, 1, 1, 1, 1);
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.CENTER;
      JButton loadButton = new JButton("Load");
      add(loadButton, gbc, 0, 2, 2, 1);
      loadButton.addActionListener(
         new ActionListener()
         {  public void actionPerformed(ActionEvent event)
            {  runClass(nameField.getText(), keyField.getText());
            }
         });
   }

   public void add(Component c, GridBagConstraints gbc,
      int x, int y, int w, int h)
   {  gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      getContentPane().add(c, gbc);
   }

   public void runClass(String name, String key)
   {  try
      {  ClassLoader loader
            = new CryptoClassLoader(Integer.parseInt(key));
         Class c = loader.loadClass(name);
         String[] args = new String[] {};

         Method m = c.getMethod("main",
            new Class[] { args.getClass() });
         m.invoke(null, new Object[] { args });
      }
      catch (Throwable e)
      {  JOptionPane.showMessageDialog(this, e);
      }
   }

   private JTextField keyField = new JTextField("3", 4);
   private JTextField nameField = new JTextField(30);
}

class CryptoClassLoader extends ClassLoader
{  public CryptoClassLoader(int k)
   {  key = k;
   }

   protected synchronized Class loadClass(String name,
      boolean resolve) throws ClassNotFoundException
   {  // check if class already loaded
      Class cl = (Class)classes.get(name);

      if (cl == null) // new class
      {  try
         {  // check if system class
            return findSystemClass(name);
         }
         catch (ClassNotFoundException e) {}
         catch (NoClassDefFoundError e) {}

         // load class bytes--details depend on class loader

         byte[] classBytes = loadClassBytes(name);
         if (classBytes == null)
            throw new ClassNotFoundException(name);

         cl = defineClass(name, classBytes,
            0, classBytes.length);
         if (cl == null)
            throw new ClassNotFoundException(name);

         classes.put(name, cl); // remember class
      }

      if (resolve) resolveClass(cl);

      return cl;
   }

   private byte[] loadClassBytes(String name)
   {  String cname = name.replace('.', '/') + ".caesar";
      FileInputStream in = null;
      try
      {  in = new FileInputStream(cname);
         ByteArrayOutputStream buffer
            = new ByteArrayOutputStream();
         int ch;
         while ((ch = in.read()) != -1)
         {  byte b = (byte)(ch - key);
            buffer.write(b);
         }
         in.close();
         return buffer.toByteArray();
      }
      catch (IOException e)
      {  if (in != null)
         {  try { in.close(); } catch (IOException e2) { }
         }
         return null;
      }
   }

   private Map classes = new HashMap();
   private int key;
}
