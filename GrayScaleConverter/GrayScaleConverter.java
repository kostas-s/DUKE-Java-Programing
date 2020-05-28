import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage){
        
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel inPixel : inImage.pixels()){
         Pixel outPixel = outImage.getPixel(inPixel.getX(), inPixel.getY());
         
         int red = inPixel.getRed();
         int green = inPixel.getGreen();
         int blue = inPixel.getBlue();
         int average = (red+green+blue)/3;
         
         outPixel.setRed(average);
         outPixel.setGreen(average);
         outPixel.setBlue(average);            
        }
        
        return(outImage);
    
    }
    
    public void batchGrayscaleConversion(){
       DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
        ImageResource ir = new ImageResource(f);
        ImageResource gray = makeGray(ir);
        gray.draw();
        String outFileName = "gray-" + f.getName();
        gray.setFileName(outFileName);
        gray.save();
    }
    }
}
