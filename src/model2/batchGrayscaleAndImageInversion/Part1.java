package src.model2.batchGrayscaleAndImageInversion;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class Part1 {
    public static void selectMultipleImage(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            //doGrayScale(f);
            doInverted(f);
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
        doSave(output,newName);

    }
    public static void doInverted(File f){
        ImageResource ir = new ImageResource(f);
        ImageResource output = new ImageResource(ir.getWidth(),ir.getHeight());
        for(Pixel pixel:output.pixels()){

            int x = pixel.getX();
            int y = pixel.getY();
            Pixel irPixel = ir.getPixel(x,y);
            pixel.setRed(255-irPixel.getRed());
            pixel.setGreen(255-irPixel.getGreen());
            pixel.setBlue(255-irPixel.getBlue());
        }
        String newName = String.format("inverted-%s",f.getName());
        doSave(output,newName);

    }


    public static void doSave(ImageResource output, String newName){
        output.setFileName(newName);
        System.out.println("save the file : "+output.getFileName());
        output.draw();
        output.save();
    }

    public static void main(String[] args) {
        selectMultipleImage();
    }
}
