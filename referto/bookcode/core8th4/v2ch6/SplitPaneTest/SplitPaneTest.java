/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class SplitPaneTest
{  public static void main(String[] args)
   {  JFrame frame = new SplitPaneFrame();
      frame.show();
   }
}

class SplitPaneFrame extends JFrame
   implements ListSelectionListener
{  public SplitPaneFrame()
   {  setTitle("SplitPaneTest");
      setSize(400, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // set up components for planet names, images, descriptions

      planetList = new JList(planets);
      planetList.addListSelectionListener(this);

      planetImage = new JLabel();

      description = new JTextArea();

      // set up split panes

      JSplitPane innerPane
         = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            planetList, planetImage);

      innerPane.setContinuousLayout(true);
      innerPane.setOneTouchExpandable(true);

      JSplitPane outerPane
         = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            innerPane, description);

      getContentPane().add(outerPane, "Center");
   }

   public void valueChanged(ListSelectionEvent event)
   {  JList source = (JList)event.getSource();
      Planet value = (Planet)source.getSelectedValue();

      // update image and description

      planetImage.setIcon(value.getImage());
      description.setText(value.getDescription());
   }

   private JList planetList;
   private JLabel planetImage;
   private JTextArea description;
   private Planet[] planets =
      {  new Planet("Mercury", 2440, 0),
         new Planet("Venus", 6052, 0),
         new Planet("Earth", 6378, 1),
         new Planet("Mars", 3397, 2),
         new Planet("Jupiter", 71492, 16),
         new Planet("Saturn", 60268, 18),
         new Planet("Uranus", 25559, 17),
         new Planet("Neptune", 24766, 8),
         new Planet("Pluto", 1137, 1),
      };
}

class Planet
{  public Planet(String n, double r, int m)
   {  name = n;
      radius = r;
      moons = m;
      image = new ImageIcon(name + ".gif");
   }

   public String toString()
   {  return name;
   }

   public String getDescription()
   {  return "Radius: " + radius + "\nMoons: " + moons + "\n";
   }

   public ImageIcon getImage()
   {  return image;
   }

   private String name;
   private double radius;
   private int moons;
   private ImageIcon image;
}