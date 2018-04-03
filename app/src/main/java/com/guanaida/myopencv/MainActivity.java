package com.guanaida.myopencv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static com.guanaida.myopencv.NativeGrabCut.grabCutFromJNI;

public class MainActivity extends AppCompatActivity {


    // Used to load the 'native-lib' library on application startup.
    private long gcapp;

    private int flags = 0; // 0范围，1前景，2背景

    private Bitmap bitmap;
    private ImageView imageView;
    private Button grabCut;

    private Bitmap bm;

    private float s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method

        imageView = (ImageView) findViewById(R.id.image_view);
        grabCut = (Button) findViewById(R.id.grabCut);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.temp);
        imageView.setImageBitmap(bitmap);
        bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this,
                mOpenCVCallBack)) {
            Log.e("Opencv", "Cannot connect to OpenCV Manager");
        }
        grabCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGrabCut(v);
            }
        });

    }
    public void onGrabCut(View view){
        Mat img = new Mat();
        Bitmap bm = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()/10,bitmap.getHeight()/10,true);
        Utils.bitmapToMat(bm, img);
        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGBA2RGB);
        Mat dst = new Mat(grabCutFromJNI(img.nativeObj,64,10,230,300));
        Bitmap b = Bitmap.createBitmap(bitmap.getWidth()/10,bitmap.getHeight()/10, Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(dst,b);
        imageView.setImageBitmap(b);
    }
    private BaseLoaderCallback mOpenCVCallBack=new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("Opencv", "OpenCV loaded successfully");
// Create and set View
// setContentView(R.layout.color_blob_detection_surface_view);
//loadimage();
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };
}