package com.example.app_test1;

import android.view.MotionEvent;
import android.view.View;

public class TouchChecker implements View.OnTouchListener {

    private View dragView;
    private double startAngle;


    public TouchChecker(View dragView, double startAngle) {
        this.dragView = dragView;
        this.startAngle = startAngle;
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        //viewのパラメーター
        int width = view.getWidth();
        double center = width / 2;
        double radius = (width - center) * 0.85;
        double mouseFirstX = 0;
        double mouseFirstY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //修正した座標獲得
                mouseFirstX = event.getX() - center;
                mouseFirstY = event.getY() - center;
        }


        //距離計算
        double distanceR = Math.sqrt((mouseFirstX) * (mouseFirstX) + (mouseFirstY) * (mouseFirstY));
        //角度計算
        double sinA = mouseFirstY / distanceR;
        double cosA = mouseFirstX / distanceR;
        double argA = 0;//x軸からの角度
        //360度変換
        if (sinA >= 0 && cosA >= 0) {
            argA = Math.toDegrees(Math.asin(sinA));
        } else if (sinA > 0 && cosA <= 0) {
            argA = 180 - Math.toDegrees(Math.asin(sinA));
        } else if (sinA <= 0 && cosA < 0) {
            argA = 180 - Math.toDegrees(Math.asin(sinA));
        } else if (sinA < 0 && cosA >= 0) {
            argA = 360 + Math.toDegrees(Math.asin(sinA));
        }

        if (0 < distanceR && distanceR < radius && startAngle < argA && argA < startAngle + 30) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:

                    // 現在タッチしている位置取得
                    double mouseAnchorX = event.getX() - center;
                    double mouseAnchorY = event.getY() - center;
                    double normalizationFactor1 = distanceR;
                    double normalizationFactor2 = Math.sqrt(mouseAnchorX * mouseAnchorX + mouseAnchorY * mouseAnchorY);

                    //回転行列の成分計算
                    double sinB = (mouseAnchorY * mouseFirstX - mouseAnchorX * mouseFirstY) / (normalizationFactor1 * normalizationFactor2);
                    double cosB = (mouseAnchorX * mouseFirstX + mouseAnchorY * mouseFirstY) / (normalizationFactor1 * normalizationFactor2);

                    double argB = 0;

                    //sin,cosの値から象限数を判別し0度～360度に変換
                    if (sinB >= 0 && cosB >= 0) {
                        argB = Math.toDegrees(Math.asin(sinB));
                    } else if (sinB >= 0 && cosB <= 0) {
                        argB = (180 - Math.toDegrees(Math.asin(sinB)));
                    } else if (sinB <= 0 && cosB <= 0) {
                        argB = (180 - Math.toDegrees(Math.asin(sinB)));
                    } else if (sinB <= 0 && cosB >= 0) {
                        argB = (360 + Math.toDegrees(Math.asin(sinB)));
                    }
                    dragView.setRotation((float) argB);
            }
            return true;
        }
        return true;
    }
}

