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

public class BookTest
{  public static void main(String[] args)
   {  JFrame frame = new BookTestFrame();
      frame.show();
   }
}

class BookTestFrame extends JFrame
   implements ActionListener
{  public BookTestFrame()
   {  setTitle("BookTest");
      setSize(300, 100);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      text = new JTextField();
      contentPane.add(text, "South");

      JPanel buttonPanel = new JPanel();

      printButton = new JButton("Print");
      buttonPanel.add(printButton);
      printButton.addActionListener(this);

      pageSetupButton = new JButton("Page setup");
      buttonPanel.add(pageSetupButton);
      pageSetupButton.addActionListener(this);

      printPreviewButton = new JButton("Print preview");
      buttonPanel.add(printPreviewButton);
      printPreviewButton.addActionListener(this);

      contentPane.add(buttonPanel, "North");
   }

   public Book makeBook()
   {  if (pageFormat == null)
      {  PrinterJob printJob = PrinterJob.getPrinterJob();
         pageFormat = printJob.defaultPage();
      }
      Book book = new Book();
      String message = text.getText();
      Banner banner = new Banner(message);
      int pageCount
         = banner.getPageCount((Graphics2D)getGraphics(),
            pageFormat);
      book.append(new CoverPage(message + " (" + pageCount
         + " pages)"), pageFormat);
      book.append(banner, pageFormat, pageCount);
      return book;
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == printButton)
      {  PrinterJob printJob = PrinterJob.getPrinterJob();
         printJob.setPageable(makeBook());
         if (printJob.printDialog())
         {  try
            {  printJob.print();
            }
            catch (Exception exception)
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
      else if (source == printPreviewButton)
      {  PrintPreviewDialog dialog
            = new PrintPreviewDialog(makeBook());
         dialog.show();
      }
   }

   private JButton printButton;
   private JButton pageSetupButton;
   private JButton printPreviewButton;

   private JTextField text;
   private PageFormat pageFormat;
}

class Banner implements Printable
{  public Banner(String m)
   {  message = m;
   }

   public int getPageCount(Graphics2D g2, PageFormat pf)
   {  if (message.equals("")) return 0;
      layoutPages(g2, pf);
      float width = scale * layout.getAdvance();
      int pages = (int)Math.ceil(width / pf.getImageableWidth());
      return pages;
   }

   public int print(Graphics g, PageFormat pf, int page)
      throws PrinterException
   {  Graphics2D g2 = (Graphics2D)g;
      g2.setPaint(Color.black);
      if (page > getPageCount(g2, pf))
         return Printable.NO_SUCH_PAGE;
      g2.translate(pf.getImageableX(), pf.getImageableY());

      drawPage(g2, pf, page);
      return Printable.PAGE_EXISTS;
   }

   public void layoutPages(Graphics2D g2, PageFormat pf)
   {  if (message.equals("")) return;
      FontRenderContext context = g2.getFontRenderContext();
      Font f = new Font("Serif", Font.PLAIN, 72);
      layout = new TextLayout(message, f, context);
      float ascent = layout.getAscent();
      float descent = layout.getDescent();
      float height = ascent + descent;
      scale = (float)pf.getImageableHeight() / height;
   }

   public void drawPage(Graphics2D g2, PageFormat pf, int page)
   {  if (message.equals("")) return;
      page--; // account for cover page
      layoutPages(g2, pf);

      drawCropMarks(g2, pf);
      g2.clip(new Rectangle2D.Double(0, 0,
         pf.getImageableWidth(), pf.getImageableHeight()));
      g2.translate(-page * pf.getImageableWidth(), 0);
      g2.scale(scale, scale);
      AffineTransform transform
         = AffineTransform.getTranslateInstance(0,
            layout.getAscent());
      Shape outline = layout.getOutline(transform);
      g2.draw(outline);
   }

   public void drawCropMarks(Graphics2D g2, PageFormat pf)
   {  final double C = 36; // crop mark length = 1/2 inch
      double w = pf.getImageableWidth();
      double h = pf.getImageableHeight();
      g2.draw(new Line2D.Double(0, 0, 0, C));
      g2.draw(new Line2D.Double(0, 0, C, 0));
      g2.draw(new Line2D.Double(w, 0, w, C));
      g2.draw(new Line2D.Double(w, 0, w - C, 0));
      g2.draw(new Line2D.Double(0, h, 0, h - C));
      g2.draw(new Line2D.Double(0, h, C, h));
      g2.draw(new Line2D.Double(w, h, w, h - C));
      g2.draw(new Line2D.Double(w, h, w - C, h));
   }

   private String message;
   private float scale;
   private float width;
   private TextLayout layout;
}

class CoverPage implements Printable
{  public CoverPage(String t)
   {  title = t;
   }

   public int print(Graphics g, PageFormat pf, int page)
      throws PrinterException
   {  if (page >= 1) return Printable.NO_SUCH_PAGE;
      Graphics2D g2 = (Graphics2D)g;
      g2.setPaint(Color.black);
      g2.translate(pf.getImageableX(), pf.getImageableY());
      FontRenderContext context = g2.getFontRenderContext();
      Font f = g2.getFont();
      TextLayout layout = new TextLayout(title, f, context);
      float ascent = layout.getAscent();
      g2.drawString(title, 0, ascent);
      return Printable.PAGE_EXISTS;
   }

   private String title;
}

class PrintPreviewDialog extends JDialog
   implements ActionListener
{  public PrintPreviewDialog(Printable p, PageFormat pf,
      int pages)
   {  Book book = new Book();
      book.append(p, pf, pages);
      layoutUI(book);
   }

   public PrintPreviewDialog(Book b)
   {  layoutUI(b);
   }

   public void layoutUI(Book book)
   {  setSize(200, 200);

      Container contentPane = getContentPane();
      canvas = new PrintPreviewCanvas(book);
      contentPane.add(canvas, "Center");

      JPanel buttonPanel = new JPanel();

      nextButton = new JButton("Next");
      buttonPanel.add(nextButton);
      nextButton.addActionListener(this);

      previousButton = new JButton("Previous");
      buttonPanel.add(previousButton);
      previousButton.addActionListener(this);

      closeButton = new JButton("Close");
      buttonPanel.add(closeButton);
      closeButton.addActionListener(this);

      contentPane.add(buttonPanel, "South");
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == nextButton)
      {  canvas.flipPage(1);
      }
      else if (source == previousButton)
      {  canvas.flipPage(-1);
      }
      else if (source == closeButton)
      {  setVisible(false);
      }
   }

   private JButton nextButton;
   private JButton previousButton;
   private JButton closeButton;
   private PrintPreviewCanvas canvas;
}

class PrintPreviewCanvas extends JPanel
{  public PrintPreviewCanvas(Book b)
   {  book = b;
      currentPage = 0;
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      PageFormat pageFormat = book.getPageFormat(currentPage);

      double xoff; // x offset of page start in window
      double yoff; // y offset of page start in window
      double scale; // scale factor to fit page in window
      double px = pageFormat.getWidth();
      double py = pageFormat.getHeight();
      double sx = getWidth() - 1;
      double sy = getHeight() - 1;
      if (px / py < sx / sy) // center horizontally
      {  scale = sy / py;
         xoff = 0.5 * (sx - scale * px);
         yoff = 0;
      }
      else // center vertically
      {  scale = sx / px;
         xoff = 0;
         yoff = 0.5 * (sy - scale * py);
      }
      g2.translate((float)xoff, (float)yoff);
      g2.scale((float)scale, (float)scale);

      // draw page outline (ignoring margins)
      Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
      g2.setPaint(Color.white);
      g2.fill(page);
      g2.setPaint(Color.black);
      g2.draw(page);

      Printable printable = book.getPrintable(currentPage);
      try
      {  printable.print(g2, pageFormat, currentPage);
      }
      catch (PrinterException exception)
      {  g2.draw(new Line2D.Double(0, 0, px, py));
         g2.draw(new Line2D.Double(0, px, 0, py));
      }
   }

   public void flipPage(int by)
   {  int newPage = currentPage + by;
      if (0 <= newPage && newPage < book.getNumberOfPages())
      {  currentPage = newPage;
         repaint();
      }
   }

   private Book book;
   private int currentPage;
}