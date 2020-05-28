import edu.duke.*;
import java.io.*;


public class InvertImages {
    
    public ImageResource invertImage(ImageResource origImage){
        ImageResource invertedImage = new ImageResource(origImage.getWidth(), origImage.getHeight());
        for (Pixel outPixel : invertedImage.pixels()){
            Pixel origPixel = origImage.getPixel(outPixel.getX(), outPixel.getY());
            int red = 255 - origPixel.getRed();
            int green = 255 - origPixel.getGreen();
            int blue = 255 - origPixel.getBlue();
            
            outPixel.setRed(red);
            outPixel.setGreen(green);
            outPixel.setBlue(blue);
        }

        return invertedImage;
    }
    
    public void batchImageInversion(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
         String outName = "inverted-" + f.getName();
         ImageResource origImage = new ImageResource(f);
         ImageResource invertedImage = invertImage(origImage);
         
         invertedImage.draw();
         
         invertedImage.setFileName(outName);
         invertedImage.save();
        }
    }
    
    
}
