package Flare.Modules;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Preprocessor {
    final String IMAGE_FOLDER = "tessdata/preprocessor/";
    Mat image;

    private File getProcessedImage()
    {
        OpenCV.loadLocally();
        image = Imgcodecs.imread(IMAGE_FOLDER+"screenshot.png");
        //Imgcodecs.imwrite("preprocess.png", image);

        Mat imgGray = new Mat();
        Imgproc.cvtColor(image, imgGray, Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite(IMAGE_FOLDER+"gray.png", imgGray);

        Mat imgGaussianBlur = new Mat();
        Imgproc.GaussianBlur(imgGray,imgGaussianBlur,new Size(5, 3),0);
        Imgcodecs.imwrite(IMAGE_FOLDER+"gaussian_blur.png", imgGaussianBlur);

        /*Mat imgSobel = new Mat();
        Imgproc.Sobel(imgGaussianBlur, imgSobel, -1, 1, 0);
        Imgcodecs.imwrite("sobel.png", imgSobel);*/

        Mat imgThreshold = new Mat();
        Imgproc.threshold(imgGaussianBlur,imgThreshold,250, 255, Imgproc.THRESH_BINARY);
        Imgcodecs.imwrite(IMAGE_FOLDER+"binary.png", imgThreshold);

        Mat imgMorphClose = new Mat();
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
        Imgproc.morphologyEx(imgThreshold, imgMorphClose, Imgproc.MORPH_CLOSE, element);
        Imgcodecs.imwrite(IMAGE_FOLDER+"morph_close.png", imgMorphClose);


        /*List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(imgMorphClose,contours,hierarchy,Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(image, contours,-1,new Scalar(0, 255, 0),2,Imgproc.LINE_AA);
        Imgcodecs.imwrite("preprocess.png", image);*/


        image =Imgcodecs.imread(IMAGE_FOLDER+"morph_close.png");
        Mat image2 = new Mat();
        Imgproc.threshold(Imgcodecs.imread(IMAGE_FOLDER+"gray.png"),image2,50, 255, Imgproc.THRESH_BINARY);

        for(int i = 0; i<image.height();i++)
        {
            for(int j = 0; j<image.width();j++) {
                double[] pixel = image.get(i, j);
                double[] pixel2 = image2.get(i, j);
                pixel[0] *= pixel2[0];
                pixel[1] *= pixel2[1];
                pixel[2] *= pixel2[2];
                image.put(i, j, pixel);
            }
        }
        Imgcodecs.imwrite(IMAGE_FOLDER+"preprocess.png", image);
        return new File(IMAGE_FOLDER+"preprocess.png");
    }
    public List doTesseract() throws TesseractException
    {
        Tesseract tesseract = new Tesseract();
        String text;
        List<String> lines;
        List<String> sampleList=new ArrayList<>();

            tesseract.setDatapath("tessdata");
            // the path of your tess data folder
            // inside the extracted file
            text = tesseract.doOCR(getProcessedImage());
            // path of your image file


        text=text.replace("<","").replace(">","");
        lines = Stream.of(text.split("\n")).collect(Collectors.toList());
        int samples = lines.size()/4; //Ибо строчек хим элементов 4;
        for (int i = 0; i < samples; i++) {
            text=new String();
            for (int j = 0; j < 4; j++) {
                text+= lines.get((j*samples)+i);
                text+=" ";
            }

            sampleList.add(text);
        }

        for (int i = 0; i < samples; i++) {
            lines=Stream.of(sampleList.get(i).split(" ")).collect(Collectors.toList());
            Collections.swap(lines,10,11);
            Collections.swap(lines,11,14);
            sampleList.add(i,lines.stream()
                    .limit(13)
                    .map(x->
                            {
                             if(!x.contains("."))
                             {
                                 return x.charAt(0)+"."+x.substring(1);
                             }
                             else return x;
                            })
                    .collect(Collectors.joining(" ")));
            sampleList.remove(i+1);
        }
        /*for (int i = 0; i < sampleList.size(); i++) {
            System.out.println(sampleList.get(i));
        }*/
        return sampleList;
    }

}