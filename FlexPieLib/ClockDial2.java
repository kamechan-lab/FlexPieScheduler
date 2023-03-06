package com.test.schedule2.ui;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.test.schedule2.Sector;
import java.time.LocalDateTime;

/**
本クラスにSectorクラスをaddViewしてください。addViewしたSectorがDial上に表示されます。
addViewする際にはコンストラクタのlayoutParamsを使ってください。
**/
public class ClockDial2 extends ConstraintLayout {
    Paint paint;
    Context context;
    public static final float rad_ratio_in = 0.725f;
    public static final float rad_ratio_out = 0.875f;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ClockDial2(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.context = context;
        this.setWillNotDraw(false);
        paint = new Paint();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        float cx = (float) width / 2;
        float cy = (float) width / 2;
        float outer_radius = (float) (width / 2 * rad_ratio_out);
        float inside_radius = (float) (width / 2 * rad_ratio_in);
        System.out.println("onDraw in ClockDialView"+String.valueOf(inside_radius));
        this.paint.setColor(Color.rgb(255, 237, 179));
        canvas.drawCircle(cx, cy, outer_radius, this.paint);
        this.paint.setColor(Color.WHITE);
        canvas.drawCircle(cx, cy, inside_radius, this.paint);
    }
}
