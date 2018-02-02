package com.guanaida.myopencv;

/**
 * Created by Admin on 2018/1/31.
 */

public class NativeGrabCut {
    static {
        System.loadLibrary("OpenCV");
    }
    public static native long grabCut(long img);
}
