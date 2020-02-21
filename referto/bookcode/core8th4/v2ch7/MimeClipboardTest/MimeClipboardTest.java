/**
 * @version 1.10 1999-09-20
 * @author Cay Horstmann
 */

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;

public class MimeClipboardTest
{  public static void main(String [] args)
   {  JFrame frame = new MimeClipboardFrame();
      frame.show();
   }
}

class MimeClipboardFrame extends JFrame
   implements ActionListener
{  public MimeClipboardFrame()
   {  setSize(300, 300);
      setTitle("MimeClipboardTest");
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      Container contentPane = getContentPane();
      label = new JLabel();
      contentPane.add(label, "Center");

      JMenu fileMenu = new JMenu("File");
      openItem = new JMenuItem("Open");
      openItem.addActionListener(this);
      fileMenu.add(openItem);

      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(this);
      fileMenu.add(exitItem);

      JMenu editMenu = new JMenu("Edit");
      copyItem = new JMenuItem("Copy");
      copyItem.addActionListener(this);
      editMenu.add(copyItem);

      pasteItem = new JMenuItem("Paste");
      pasteItem.addActionListener(this);
      editMenu.add(pasteItem);

      JMenuBar menuBar = new JMenuBar();
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      setJMenuBar(menuBar);
   }

   public void actionPerformed(ActionEvent evt)
   {  Object source = evt.getSource();
      if (source == openItem)
      {  JFileChooser chooser = new JFileChooser();
         chooser.setCurrentDirectory(new File("."));

         chooser.setFileFilter(new
            javax.swing.filechooser.FileFilter()
            {  public boolean accept(File f)
               {  String name = f.getName().toLowerCase();
                  return name.endsWith(".gif")
                     || name.endsWith(".jpg")
                     || name.endsWith(".jpeg")
                     || f.isDirectory();
               }
               public String getDescription()
               {  return "Image files";
               }
            });

         int r = chooser.showOpenDialog(this);
         if(r == JFileChooser.APPROVE_OPTION)
         {  String name
               = chooser.getSelectedFile().getAbsolutePath();
            setImage(Toolkit.getDefaultToolkit().getImage(name));
         }
      }
      else if (source == exitItem) System.exit(0);
      else if (source == copyItem) copy();
      else if (source == pasteItem) paste();
   }

   private void copy()
   {  MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(theImage, 0);
      try { tracker.waitForID(0); }
      catch (InterruptedException e) {}
      BufferedImage image
         = new BufferedImage(theImage.getWidth(null),
            theImage.getHeight(null),
            BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = image.createGraphics();
      g2.drawImage(theImage, 0, 0, null);

      Bitmap bitmap = new Bitmap(image);
      SerializableSelection selection
         = new SerializableSelection(bitmap);
      mimeClipboard.setContents(selection, null);
   }

   private void paste()
   {  Transferable selection
         = mimeClipboard.getContents(this);
      try
      {  Bitmap bitmap = (Bitmap)selection.getTransferData
            (SerializableSelection.serializableFlavor);
         setImage(bitmap.getImage());
      }
      catch(Exception e) {}
   }

   public void setImage(Image image)
   {  theImage = image;
      label.setIcon(new ImageIcon(image));
   }

   private static Clipboard mimeClipboard
      = new MimeClipboard
         (Toolkit.getDefaultToolkit().getSystemClipboard());
   private Image theImage;
   private JLabel label;
   private JMenuItem openItem;
   private JMenuItem exitItem;
   private JMenuItem copyItem;
   private JMenuItem pasteItem;
}

class Bitmap implements Serializable
{  public Bitmap(BufferedImage image)
   {  type = image.getType();
      width = image.getWidth();
      height = image.getHeight();
      WritableRaster raster = image.getRaster();
      data = raster.getDataElements(0, 0, width, height, null);
   }

   public BufferedImage getImage()
   {  BufferedImage image
         = new BufferedImage(width, height, type);
      WritableRaster raster = image.getRaster();
      raster.setDataElements(0, 0, width, height, data);
      return image;
   }

   private int type;
   private int width;
   private int height;
   private Object data;
}

class SerializableSelection implements Transferable
{  public SerializableSelection(Serializable object)
   {  theObject = object;
   }

   public boolean isDataFlavorSupported(DataFlavor flavor)
   {  return flavor.equals(serializableFlavor);
   }

   public synchronized Object getTransferData
      (DataFlavor flavor)
      throws UnsupportedFlavorException
   {  if(flavor.equals(serializableFlavor))
      {  return theObject;
      }
      else
      {  throw new UnsupportedFlavorException(flavor);
      }
   }

   public DataFlavor[] getTransferDataFlavors()
   {  return flavors;
   }

   public static final DataFlavor serializableFlavor
      = new DataFlavor(java.io.Serializable.class,
      "Serializable Object");

   private static DataFlavor[] flavors
      = { serializableFlavor };

   private Serializable theObject;
}

class MimeClipboard extends Clipboard
{  public MimeClipboard(Clipboard cb)
   {  super("MIME/" + cb.getName());
      clip = cb;
   }

   public synchronized void setContents(Transferable contents,
      ClipboardOwner owner)
   {  if (contents instanceof SerializableSelection)
      {  try
         {  DataFlavor flavor
               = SerializableSelection.serializableFlavor;
            Serializable obj = (Serializable)
               contents.getTransferData(flavor);
            String enc = encode(obj);
            String header = "Content-type: "
               + flavor.getMimeType()
               + "\nContent-length: "
               + enc.length() + "\n\n";
            StringSelection selection
               = new StringSelection(header + enc);
            clip.setContents(selection, owner);
         }
         catch(UnsupportedFlavorException e)
         {}
         catch(IOException e)
         {}
      }
      else clip.setContents(contents, owner);
   }

   public synchronized Transferable getContents
      (Object requestor)
   {  Transferable contents = clip.getContents(requestor);

      if (contents instanceof StringSelection)
      {  String data = null;
         try
         {  data = (String)contents.getTransferData
               (DataFlavor.stringFlavor);
         }
         catch(UnsupportedFlavorException e)
         { return contents; }
         catch(IOException e)
         { return contents; }

         if (!data.startsWith("Content-type: "))
            return contents;
         int start = -1;
         // skip three newlines
         for (int i = 0; i < 3; i++)
         {  start = data.indexOf('\n', start + 1);
            if (start < 0) return contents;
          }
         Serializable obj = decode(data.substring(start));
         SerializableSelection selection
            = new SerializableSelection(obj);
         return selection;
      }
      else return contents;
   }

   public static String encode(Serializable obj)
   {  ByteArrayOutputStream bOut
         = new ByteArrayOutputStream();
      try
      {  Base64OutputStream b64Out
            = new Base64OutputStream(bOut);
         ObjectOutputStream out
            = new ObjectOutputStream(b64Out);
         out.writeObject(obj);
         out.close();
         return bOut.toString("8859_1");
      }
      catch (IOException exception)
      {  return null;
      }
   }

   public static Serializable decode(String s)
   {  try
      {  byte[] bytes = s.getBytes("8859_1");
         ByteArrayInputStream bIn
            = new ByteArrayInputStream(bytes);
         Base64InputStream b64In
            = new Base64InputStream(bIn);
         ObjectInputStream in
            = new ObjectInputStream(b64In);
         Object obj = in.readObject();
         in.close();
         return (Serializable)obj;
      }
      catch(Exception e)
      {  return null;
      }
   }

   private Clipboard clip;
}

/* BASE64 encoding encodes 3 bytes into 4 characters.
   |11111122|22223333|33444444|
   Each set of 6 bits is encoded according to the
   toBase64 map. If the number of input bytes is not
   a multiple of 3, then the last group of 4 characters
   is padded with one or two = signs. Each output line
   is at most 76 characters.
*/

class Base64OutputStream extends FilterOutputStream
{  public Base64OutputStream(OutputStream out)
   {  super(out);
   }

   public void write(int c) throws IOException
   {  inbuf[i] = c;
      i++;
      if (i == 3)
      {  super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
         super.write(toBase64[((inbuf[0] & 0x03) << 4) |
            ((inbuf[1] & 0xF0) >> 4)]);
         super.write(toBase64[((inbuf[1] & 0x0F) << 2) |
            ((inbuf[2] & 0xC0) >> 6)]);
         super.write(toBase64[inbuf[2] & 0x3F]);
         col += 4;
         i = 0;
         if (col >= 76)
         {  super.write('\n');
            col = 0;
         }
      }
   }

   public void flush() throws IOException
   {  if (i == 1)
      {  super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
         super.write(toBase64[(inbuf[0] & 0x03) << 4]);
         super.write('=');
         super.write('=');
      }
      else if (i == 2)
      {  super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
         super.write(toBase64[((inbuf[0] & 0x03) << 4) |
            ((inbuf[1] & 0xF0) >> 4)]);
         super.write(toBase64[(inbuf[1] & 0x0F) << 2]);
         super.write('=');
      }
      i = 0;
   }

   private static char[] toBase64 =
   {  'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
      'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
      'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
      'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
      'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
      'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
      'w', 'x', 'y', 'z', '0', '1', '2', '3',
      '4', '5', '6', '7', '8', '9', '+', '/'
   };

   private int col = 0;
   private int i = 0;
   private int[] inbuf = new int[3];
}


class Base64InputStream extends FilterInputStream
{  public Base64InputStream(InputStream in)
   {  super(in);
   }

   public int read(byte[] b, int off, int len) throws IOException
   {  if (len > b.length - off) len = b.length - off;
      for (int i = 0; i < len; i++)
      {  int ch = read();
         if (ch == -1) return i;
         b[i + off] = (byte)ch;
      }
      return len;

   }

   public int read(byte[] b) throws IOException
   {  return read(b, 0, b.length);
   }

   public int read() throws IOException
   {  int r;
      if (i == 0)
      {  // skip whitespace
         do
         {  ch[0] = super.read();
            if (ch[0] == -1) return -1;
         }
         while (Character.isWhitespace((char)ch[0]));
         ch[1] = super.read();
         if (ch[1] == -1) return -1;
         i++;
         r = (fromBase64[ch[0]] << 2)
            | (fromBase64[ch[1]] >> 4);
      }
      else if (i == 1)
      {  ch[2] = super.read();
         if (ch[2] == '=' || ch[2] == -1) return -1;
         i++;
         r = ((fromBase64[ch[1]] & 0x0F) << 4)
            | (fromBase64[ch[2]] >> 2);
      }
      else
      {  ch[3] = super.read();
         if (ch[3] == '=' || ch[3] == -1) return -1;
         i = 0;
         r = ((fromBase64[ch[2]] & 0x03) << 6)
            | fromBase64[ch[3]];
      }
      return r;
   }

   private static int[] fromBase64 =
   {  -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, 62, -1, -1, -1, 63,
      52, 53, 54, 55, 56, 57, 58, 59,
      60, 61, -1, -1, -1, -1, -1, -1,
      -1,  0,  1,  2,  3,  4,  5,  6,
       7,  8,  9, 10, 11, 12, 13, 14,
      15, 16, 17, 18, 19, 20, 21, 22,
      23, 24, 25, -1, -1, -1, -1, -1,
      -1, 26, 27, 28, 29, 30, 31, 32,
      33, 34, 35, 36, 37, 38, 39, 40,
      41, 42, 43, 44, 45, 46, 47, 48,
      49, 50, 51, -1, -1, -1, -1, -1
   };

   int i = 0;
   int[] ch = new int[4];
}


