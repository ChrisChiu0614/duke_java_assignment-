package src.batchGrayscaleAndImageInversion;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class Part1 {
    public static void selectMultipleImage(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            doGrayScale(f);
        }

    }
    public static void doGrayScale(File f){
        ImageResource ir = new ImageResource(f);
        ImageResource output = new ImageResource(ir.getWidth(),ir.getHeight());
        for(Pixel pixel:output.pixels()){
            int x = pixel.getX();
            int y = pixel.getY();
            Pixel irPixel = ir.getPixel(x,y);
            int grayScaleRGB = (irPixel.getRed()+irPixel.getGreen()+irPixel.getBlue())/3;
            pixel.setRed(grayScaleRGB);
            pixel.setGreen(grayScaleRGB);
            pixel.setBlue(grayScaleRGB);
        }
        String newName = String.format("gray-%s",f.getName());
        output.setFileName(newName);
        System.out.println(output.getFileName());
        output.draw();
        output.save();

    }

    public static void main(String[] args) {
        selectMultipleImage();
    }
}
