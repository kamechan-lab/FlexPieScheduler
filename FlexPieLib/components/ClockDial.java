package com.test.schedule2;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.lang.Math;
import java.time.LocalDateTime;

/**
ListenerにOnGlobalLayoutListenerを使っているので、ほとんどの変更がすぐに反映されます。
個別のページのDialにはClockDial2を使ってください。
**/
public class ClockDial extends RelativeLayout {

    float radius1_rate = 0.8f;
    float radius2_rate = 0.65f;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ClockDial clockDial = this;
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                clockDial.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ClockDial(Context context){
        super(context);

        ClockDialView clockDialView = new ClockDialView(context, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(clockDialView, layoutParams);

        OnGlobalLayoutListener1 onGlobalLayoutListener1 = new OnGlobalLayoutListener1(context, this);

        ClockDial clockDial = this;
        this.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener1);
        ViewTreeObserver.OnGlobalLayoutListener testOnGlobalListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("Another onGlobalLayout is here");
                ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener2 = this;
                System.out.println("Inner access by this testListener:" + this);
                System.out.println("Inner access by variable testListner:" + onGlobalLayoutListener2);
            }
        };
        this.getViewTreeObserver().addOnGlobalLayoutListener(testOnGlobalListener);
        System.out.println("Outer access testListener:" + testOnGlobalListener);
        System.out.println("Outer access onGlobalLayoutListener:" + onGlobalLayoutListener1);
        Sector sector1 = new Sector(context, LocalDateTime.of(2022, 9, 9, 17, 22), LocalDateTime.of(2022, 9, 9, 22, 0));
        this.addView(sector1, layoutParams);
    }

    public class OnGlobalLayoutListener1 implements ViewTreeObserver.OnGlobalLayoutListener{
        ClockDial clockDial;
        Context context;
        int count = 0;
        OnGlobalLayoutListener1(Context context, ClockDial clockDial){
            this.clockDial = clockDial;
            this.context = context;
        }

        @Override
        public void onGlobalLayout() {
            this.clockDial.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            System.out.println("Inner access by this onGlobalLayoutListner1" + this);
            this.count = 5;
            System.out.println("onGlobalLayout is called!");
            int width = clockDial.getWidth();
            float radius1 = (width * clockDial.radius1_rate) / 2;
            float radius2 = (width * clockDial.radius2_rate) / 2;
            System.out.println(radius2);
            float cx = width / 2;
            float cy = width / 2;
            int txBox_width = 35;

            for (int i = 0; i < 24 ; i++) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(this.context);
                textView.setTextColor(Color.BLACK);
                textView.setText(String.valueOf(i));
                float radius_sp = (radius1 + radius2) / 2;
                int left = (int) (cx + radius_sp * Math.cos(Math.PI / 2 - (i * 2*Math.PI / 24)) - (txBox_width / 2));
                int top = (int) (cy - radius_sp * Math.sin(Math.PI / 2 - (i * 2*Math.PI / 24)) - (txBox_width / 2));
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                this.clockDial.addView(textView, layoutParams);
            }

            this.clockDial.invalidate();
            System.out.println("count in OnGlobalLayout :" + String.valueOf(this.count));

        }
    }

    class ClockDialView extends View{
        ClockDial clockDial;

        public ClockDialView(Context context, ClockDial clockDial){
            super(context);
            this.clockDial = clockDial;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int width = this.getWidth();
            float cx = (float) width / 2;
            System.out.println(cx);
            float cy = (float) width / 2;
            float outer_radius = (float) (width / 2 * clockDial.radius1_rate);
            float inside_radius = (float) (width / 2 * clockDial.radius2_rate);
            System.out.println("onDraw in ClockDialView"+String.valueOf(inside_radius));
            Paint paint = new Paint();
            paint.setColor(Color.rgb(255, 237, 179));
            canvas.drawCircle(cx, cy, outer_radius, paint);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(cx, cy, inside_radius, paint);
        }
    }

}
