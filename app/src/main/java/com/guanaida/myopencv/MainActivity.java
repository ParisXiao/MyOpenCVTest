package com.guanaida.myopencv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
//        Mat img = new Mat();
//        Bitmap bm = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()/10,bitmap.getHeight()/10,true);
//        Utils.bitmapToMat(bm, img);
//        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGBA2RGB);
//        Mat dst = new Mat(grabCutFromJNI(img.nativeObj,64,10,230,300));
//        Bitmap b = Bitmap.createBitmap(bitmap.getWidth()/10,bitmap.getHeight()/10, Bitmap.Config.ARGB_8888);
//        Utils.matToBitmap(dst,b);
//        imageView.setImageBitmap(b);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:

                break;
        }
    }
}
