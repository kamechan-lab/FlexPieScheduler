package com.example.app_test1;


import android.view.MotionEvent;
import android.view.View;


public class DragAngleListener implements View.OnTouchListener {
    // ドラッグ対象のView
    private View dragView;
    // ドラッグ中に移動量を取得するための変数

    double sinA;
    double cosA;
    float argA;
    float nomalizationFactor1;
    float nomalizationFactor2;
    double mouseFirstX;
    double mouseFirstY;
    double mouseAnchorX;
    double mouseAnchorY;

    public DragAngleListener(View dragView) {

        this.dragView = dragView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int width = view.getWidth();
        double center = width / 2;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //初期座標獲得
                mouseFirstX = (int) event.getX() - center;
                mouseFirstY = (int) event.getY() - center;

                nomalizationFactor1 = (float) Math.sqrt(mouseFirstX * mouseFirstX + mouseFirstY * mouseFirstY);
                break;
            case MotionEvent.ACTION_MOVE:

                // タッチしている位置取得
                mouseAnchorX = (int) event.getX() - center;
                mouseAnchorY = (int) event.getY() - center;

                nomalizationFactor2 = (float) Math.sqrt(mouseAnchorX * mouseAnchorX + mouseAnchorY * mouseAnchorY);

                //規格化
                double x =mouseFirstX/nomalizationFactor1;
                double y =mouseFirstY/nomalizationFactor1;
                double X =mouseAnchorX/nomalizationFactor1;
                double Y =mouseAnchorY/nomalizationFactor1;

                //回転行列の成分計算
                sinA = (Y*x - X*y);
                cosA = (X*x + Y*y);


                //sin,cosの値から象限数を判別し0度～360度に変換
                if (sinA >= 0 && cosA >= 0) {
                    argA = (float) Math.toDegrees(Math.asin(sinA));
                } else if (sinA > 0 && cosA < 0) {
                    argA = (float) (180 - Math.toDegrees(Math.asin(sinA)));
                } else if (sinA <= 0 && cosA <= 0) {
                    argA = (float) (180 - Math.toDegrees(Math.asin(sinA)));
                } else if (sinA < 0 && cosA > 0) {
                    argA = (float) (360 + Math.toDegrees(Math.asin(sinA)));
                }
                dragView.setRotation(argA);
                break;



        }
        return true;

    }
}

