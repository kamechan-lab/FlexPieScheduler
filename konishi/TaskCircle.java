package com.example.app_test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TaskCircle extends View {

    public Paint p;
    int counter ;
    //コンストラクタとりあえずこれ書いとけ
    public double startAngle = 270+30*counter;
    public double sweepAngle = 30;

    public TaskCircle(Context context,int bt_counter ) {
        super(context);
        counter = bt_counter;
        p = new Paint();
        double startAngle = 270+30*counter;
        double sweepAngle = 30;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float centerX = width/2;
        double radius = (width - centerX) * 0.85;
        double left = centerX - radius;
        double top = centerX - radius;
        double right = width - left;
        double bottom = height - top;

        RectF rectf = new RectF((float) left, (float) top,(float) right,(float) bottom);


        //背景色
        canvas.drawColor(Color.TRANSPARENT);

        p.setColor(Color.rgb(30*counter, 255-30*counter, 255));
        canvas.drawArc(rectf, 270+30*counter, 30, true, p);
    }

}
