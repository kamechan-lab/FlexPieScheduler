package com.example.app_test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class baseCircle extends View {
    private Paint p;
//コンストラクタとりあえずこれ書いとけ
    public baseCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
    }
    public boolean touchchecker = false;




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //変数定義
        int width = getWidth();
        int height = getHeight();
        float centerX = width/2;
        float centerY = height/2;
        double radius = (width - centerX) * 0.9;

        //背景色
        canvas.drawColor(Color.rgb(216,191,216));
        //塗りつぶしの円
        p.setColor(Color.rgb(192,192,192));
        p.setAntiAlias(true);
        canvas.drawCircle(centerX,centerY, (float) radius,p);
//        canvas.drawCircle(165*3,165*3,150*3,p); // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
        //枠のみの円
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        p.setColor(Color.rgb(128,128,128));
        canvas.drawCircle(centerX,centerY, (float) radius,p); // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径










    }
}

