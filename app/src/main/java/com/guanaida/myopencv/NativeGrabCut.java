package com.guanaida.myopencv;

import android.graphics.Bitmap;

import org.opencv.core.Mat;

/**
 * Created by Admin on 2018/1/31.
 */

public class NativeGrabCut {
    static {
         System.loadLibrary("opencv");
    }
//    public static native long initGrabCut(long image);
//    public static native void moveGrabCut(int event, int x, int y, int flags,long gcapp);
//    public static native void reset(long gcapp);
//    public static native boolean grabCut(long gcapp);
//    public static native void grabCutOver(long gcapp);
    public static native long grabCutFromJNI(long m,int l,int t,int r,int b);


}
