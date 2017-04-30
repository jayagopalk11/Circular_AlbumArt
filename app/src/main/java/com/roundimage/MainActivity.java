package com.roundimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.roundimage.SeekArc;
import com.roundimage.SeekArc.OnSeekArcChangeListener;


public class MainActivity extends AppCompatActivity {

    public SeekArc seekarc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekarc = (SeekArc) findViewById(R.id.seekArc);
        seekarc.setTouchInSide(true);

        ImageView myImage = (ImageView) findViewById(R.id.myImage);

        Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.nature);

        Bitmap myBitmap = getCircleBitmap(image);
        myImage.setImageBitmap(myBitmap);

        seekarc.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                Toast.makeText(getApplicationContext(),"test", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap sbmp;
        int radius = (bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth():bitmap.getHeight());


        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius) {
            float smallest = Math.min(bitmap.getWidth(), bitmap.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth() / factor), (int)(bitmap.getHeight() / factor), false);
        } else {
            sbmp = bitmap;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 + 0.7f,
                radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }
}
