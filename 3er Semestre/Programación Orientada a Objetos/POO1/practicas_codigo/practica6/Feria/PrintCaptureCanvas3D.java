import com.sun.j3d.utils.applet.MainFrame;import com.sun.j3d.utils.image.*;
import java.awt.print.*;import java.awt.print.PrinterJob;
import java.awt.print.PrinterGraphics;import java.awt.*;
import java.awt.image.BufferedImage;import java.awt.Image;
import java.awt.image.ImageObserver;import java.io.*;import java.net.*;
import javax.media.j3d.*;import javax.vecmath.*;
import java.awt.geom.AffineTransform;import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;
/** Class PrintCaptureCanvas3D, using the instructions from the Java3D
    FAQ pages on how to capture a still image in jpeg format.

    A capture button would call a method that looks like

     public static void captureImage(PrintCaptureCanvas3D c)          {
          c.writeJPEG_ = true;          c.saveJPEG_ = true;
          c.repaint();          };

    A print button would call a method that looks like

     public static void printJPGImage(PrintCaptureCanvas3D c)         {
        c.writeJPEG_ = true;        c.saveJPEG_ = false;        c.repaint();
         };



    Peter Z. Kunszt    Johns Hopkins University    Dept of Physics and Astronomy
    Baltimore MD

Working from Mr. Kunszt's posted class, the following enhancements were added:

BufferedImage img;  was made global to the class.
The image width was made dynamic using this.getWidth() & this.getHeight() methods.
Printing capability was added (this is still buggy however)

Don Casteel 10/24/1999Dcasteel@usit.net*/

public class PrintCaptureCanvas3D extends Canvas3D  implements Printable, ImageObserver
     {

        public boolean writeJPEG_;     private int postSwapCount_;
        public boolean saveJPEG_;     BufferedImage img;
        FileDialog fileDialog;        File file;        File directory;

public PrintCaptureCanvas3D(GraphicsConfiguration gc)        {        super(gc);
        postSwapCount_ = 0;        }

    public void postSwap()         {
        if(writeJPEG_)               {
               System.out.println("Writing JPEG");
               GraphicsContext3D  ctx = getGraphicsContext3D();
               // The raster components need all be set!
               Raster ras = new Raster                    (
                    new Point3f(-1.0f,-1.0f,-1.0f),
                    javax.media.j3d.Raster.RASTER_COLOR,                    0,0,
                    this.getWidth(),this.getHeight(),
                    new ImageComponent2D                         (
                         ImageComponent.FORMAT_RGB,
                         new BufferedImage                              (
                              this.getWidth(),
                              this.getHeight(),
                              BufferedImage.TYPE_INT_RGB
                              )                         ),
                    null                    );
               ctx.readRaster(ras);
               // Now strip out the image info
               img = ras.getImage().getImage();
               // write that to disk....
               if(true || saveJPEG_)
                    {
                        try                         {
                         System.out.println("Capture"+postSwapCount_+".jpg");
                         FileOutputStream out = new FileOutputStream("Capture"+postSwapCount_+".jpg");
                         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                         JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(img);
                         param.setQuality(0.9f,false); // 90% quality JPEG
                         encoder.setJPEGEncodeParam(param);
                         encoder.encode(img);
                       //GMH changed from  writeJPEG_ = false;
                         writeJPEG_ = true;
                         out.close();                         }
                    catch ( IOException e )                   {
                         System.out.println("I/O exception!");
                         };

                         }
                         else
                    {                    printMethod();
                    writeJPEG_ = false;                    };
               postSwapCount_++;               }          };

          public int print(Graphics g, PageFormat pf, int pi)
               throws PrinterException               {
               if (pi >= 1)                    {
                    return Printable.NO_SUCH_PAGE;                    }
               Graphics2D g2d = (Graphics2D)g;
               g2d.translate(pf.getImageableX(), pf.getImageableY());
               drawObject(g2d);
               return Printable.PAGE_EXISTS;
               };

          public void drawObject(Graphics2D g2d)               {
               try                    {
                    g2d.drawImage(img,new AffineTransform(), this);
                    }               catch (Exception ex)                    {
                    ex.printStackTrace();                    };               };

        public void printMethod()                {
                System.out.println("Print Method Called");
                         PrinterJob printJob = PrinterJob.getPrinterJob();
                PageFormat pageFormat = printJob.defaultPage();
                         pageFormat = printJob.validatePage(pageFormat);
                         printJob.setPrintable(this);
                         if (printJob.printDialog())
                              {                              try
                                   {
                                   printJob.print();
                                   }
                              catch (Exception ex)
                                   {
                                   System.out.println("Exception Caught");
                                   ex.printStackTrace();
                                   };                              };
                    };          }


