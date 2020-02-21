/**
 * @version 1.00 1999-09-11
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.*;
import java.util.*;
import javax.swing.*;

public class PrintTest
{  public static void main(String[] args)
   {  JFrame frame = new PrintTestFrame();
      frame.show();
   }
}

class PrintTestFrame extends JFrame
   implements ActionListener
{  public PrintTestFrame()
   {  setTitle("PrintTest");
      setSize(300, 300);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      canvas = new PrintPanel();
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();
      printButton = new JButton("Print");
      buttonPanel.add(printButton);
      printButton.addActionListener(this);

      pageSetupButton = new JButton("Page setup");
      buttonPanel.add(pageSetupButton);
      pageSetupButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == printButton)
      {  PrinterJob printJob = PrinterJob.getPrinterJob();
         if (pageFormat == null)
            pageFormat = printJob.defaultPage();
         printJob.setPrintable(canvas, pageFormat);
         if (printJob.printDialog())
         {  try
            {  printJob.print();
            }
            catch (PrinterException exception)
            {  JOptionPane.showMessageDialog(this, exception);
            }
         }
      }
      else if (source == pageSetupButton)
      {  PrinterJob printJob = PrinterJob.getPrinterJob();
         if (pageFormat == null)
            pageFormat = printJob.defaultPage();
         pageFormat = printJob.pageDialog(pageFormat);
      }
   }

   private JButton printButton;
   private JButton pageSetupButton;

   private PrintPanel canvas;
   private PageFormat pageFormat;
}

class PrintPanel extends JPanel
   implements Printable
{  public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      drawPage(g2);
   }

   public int print(Graphics g, PageFormat pf, int page)
      throws PrinterException
   {  if (page >= 1) return Printable.NO_SUCH_PAGE;
      Graphics2D g2 = (Graphics2D)g;
      g2.setPaint(Color.black);
      g2.translate(pf.getImageableX(), pf.getImageableY());
      g2.draw(new Rectangle2D.Double(0, 0,
         pf.getImageableWidth(), pf.getImageableHeight()));

      drawPage(g2);
      return Printable.PAGE_EXISTS;
   }

   public void drawPage(Graphics2D g2)
   {  FontRenderContext context = g2.getFontRenderContext();
      Font f = new Font("Serif", Font.PLAIN, 72);
      GeneralPath clipShape = new GeneralPath();

      TextLayout layout = new TextLayout("Hello", f, context);
      AffineTransform transform
         = AffineTransform.getTranslateInstance(0, 72);
      Shape outline = layout.getOutline(transform);
      clipShape.append(outline, false);

      layout = new TextLayout("World", f, context);
      transform
         = AffineTransform.getTranslateInstance(0, 144);
      outline = layout.getOutline(transform);
      clipShape.append(outline, false);

      g2.draw(clipShape);
      g2.clip(clipShape);

      final int NLINES =50;
      Point2D p = new Point2D.Double(0, 0);
      for (int i = 0; i < NLINES; i++)
      {  double x = (2 * getWidth() * i) / NLINES;
         double y = (2 * getHeight() * (NLINES - 1 - i))
            / NLINES;
         Point2D q = new Point2D.Double(x, y);
         g2.draw(new Line2D.Double(p, q));
      }
   }
}


