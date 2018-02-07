package com.guanaida.myopencv;

import android.graphics.Bitmap;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Admin on 2018/2/5.
 */

public class GrabCutUtil {
    Mat hsvImg;
    public Bitmap grabCut(String url){
        Mat img= Imgcodecs.imread(url);
        Mat roi=img.submat(new Rect(20,20,20,20));
        Imgproc.cvtColor(img, hsvImg, 6); //将图像转换到HSV颜色空间
        return null;
    }
}
