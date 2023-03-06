package com.test.schedule2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.RequiresApi;
import com.test.schedule2.ui.ClockDial2;
import java.security.cert.TrustAnchor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.HOURS;

/**Dial上に表示するSectorです。コンストラクタに開始時間と終了時間を入力してください。
基本的にユーザーにはドラッグで大きさを変えてほしいので、setStartTimeはプライベートメソッドにしています。
**/
public class Sector extends View {
    float radius2_rate = 0.65f;
    int color = Color.rgb(175, 223, 228);
    private LocalDateTime start;
    private LocalDateTime end;

    public Sector(Context context, LocalDateTime start, LocalDateTime end){
        super(context);
        this.start = start;
        this.end = end;

        this.setOnTouchListener(new OnTouchListener() {
            float init_x;
            float init_y;
            float init_deg;
            LocalDateTime init_start;
            LocalDateTime init_end;

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Sector sector = (Sector) view;
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        init_deg = sector.calcDegrees(motionEvent.getX(), motionEvent.getY());//180
                        init_start = sector.start;
                        init_end = sector.end;

                    case MotionEvent.ACTION_MOVE:
                        float deg = sector.calcDegrees(motionEvent.getX(), motionEvent.getY());//178
                        float dDeg = deg - init_deg;
                        int dMin = (int) (dDeg * 4);
                        sector.setStartTime(init_start.plusMinutes(dMin));
                }
                return true;
            }
        });
    }

    public Sector(Context context, LocalDateTime start, LocalDateTime end, int color){
        this(context, start, end);
        this.color = color;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float calcDegrees(){
        System.out.println("start at calcDegrees: "+start);
        float dHours = ChronoUnit.HOURS.between(start, end);
        //+10になる。
        float dMin = ChronoUnit.MINUTES.between(start, end) % 60;
        System.out.println("dHour: " + dHours + ", dMin : " +dMin);
        System.out.println("calcDegrees: " + (float) (dHours * 360 /24 + (dMin * 360 /(24 * 60))));
        return (float) (dHours * 360 / 24 + (dMin * 360 /(24 * 60)));
    }

    public float calcDegrees(float x, float y){
        float cx = this.getWidth() / 2;
        float deg = PolarCoordUtil.calcDegrees(x, y, cx, cx);
        return deg;
    }

    public float calcR(float x, float y){
        float cx = this.getWidth() / 2;
        float r = PolarCoordUtil.calcR(x, y, cx, cx);
        return r;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float calcStartDegree(){
        float hour = this.start.getHour();
        float min = this.start.getMinute();
        return ((hour * 360 / 24) + ((min / 60) * 360 / 24));
    }

    public float getR(){
        return (this.getWidth() / 2) * ClockDial2.rad_ratio_in;
    }

    p void setStartTime(LocalDateTime start){
        this.start = start;
        this.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void shiftStartTime(float shift_deg){
        double hour = Math.floor(shift_deg / (360 / 24));
        double minute = (shift_deg % (360 / 24)) * 60;
        this.start.plusHours((long) hour);
        this.start.plusMinutes((long) minute);
        this.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isTouched(float x, float y){
        float r = this.calcR(x, y);
        float deg = this.calcDegrees(x, y);
        return ( r < this.getR()) & (deg > this.calcStartDegree()) & (deg < this.calcStartDegree() + this.calcDegrees());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isStartTouched(float x, float y){
        float r_tch = this.calcR(x, y);
        float deg_tch = this.calcDegrees(x, y);
        float start_deg = this.calcStartDegree();
        //距離5%以内、角度10度以内のずれは受け入れる。
        if(Math.abs(r_tch - this.getR()) < 0.1 * this.getR()){
            if(Math.abs(deg_tch - start_deg) < 10){
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = this.getWidth() / 2;
        float radius2 = this.getR();
        Paint paint = new Paint();
        paint.setColor(this.color);
        RectF rectf = new RectF(cx - radius2, cx -radius2, cx + radius2, cx + radius2);
        canvas.drawArc(rectf, this.calcStartDegree() - 90, this.calcDegrees(), true, paint);
    }
}
