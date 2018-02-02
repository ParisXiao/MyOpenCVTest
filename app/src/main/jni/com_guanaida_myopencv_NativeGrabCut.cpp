//
// Created by Admin on 2018/1/31.
//

#include "com_guanaida_myopencv_NativeGrabCut.h"
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <opencv2/opencv.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;
using namespace std;
extern "C" {
JNIEXPORT jlong  JNICALL
Java_com_guanaida_myopencv_NativeGrabCut_grabCut
        (JNIEnv *env, jclass instance, jlong img) {
    Mat *image = (Mat) img ;
    Mat roi=image(Rect(20,20,20,20));
    Mat hsvImg;
    cvtColor(image, hsvImg, CV_BGR2HSV); //将图像转换到HSV颜色空间
    //分离HSV空间，v[0]为H色调，v[1]为S饱和度，v[2]为v灰度
    vector<Mat> v;
    split(hsvImg,v);
    Mat roiH=v[0](Rect(20,20,20,20));
    Mat roiS=v[1](Rect(20,20,20,20));
    int SumH=0;
    int SumS=0;
    int avgH, avgS;//蓝底的平均色调和平均饱和度
    //取一块蓝色背景，计算出它的平均色调和平均饱和度
    for(int i=0; i<20; i++)
    {
        for(int j=0; j<20; j++)
        {
            /*SumH=SumH+roiH(i,j);*/
            SumH=int(roiH.at<uchar>(j,i))+SumH;
            SumS=int(roiS.at<uchar>(j,i))+SumS;
        }
    }
    avgH=SumH/400;
    avgS=SumS/400;
    //遍历整个图像
    int nl=hsvImg.rows;
    int nc=hsvImg.cols;
    int step=10;
    for(int j=0; j<nl; j++)
    {
        for(int i=0; i<nc; i++)
        {
            //以H.S两个通道做阈值分割，把蓝色替换成红色
            if((v[0].at<uchar>(j,i))<=(avgH+5) && v[0].at<uchar>(j,i)>=(avgH-5)
               &&(v[1].at<uchar>(j,i))<=(avgS+40) && v[1].at<uchar>(j,i)>=(avgS-40))
            {
                //cout<<int(v[0].at<uchar>(j,i))<<endl;
                v[0].at<uchar>(j,i)=0;
                /*cout<<int(v[0].at<uchar>(j,i))<<endl;*/
            }
        }
    }
    Mat finImg;
    merge(v,finImg);
    Mat rgbImg;
    cvtColor(finImg,rgbImg, CV_HSV2BGR); //将图像转换回RGB空间
    return rgbImg;
}

}