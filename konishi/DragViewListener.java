package com.example.app_test1;
import android.view.MotionEvent;
import android.view.View;


public class DragViewListener implements View.OnTouchListener {
    // ドラッグ対象のView
    private View dragView;
    // ドラッグ中に移動量を取得するための変数
    private int oldx;
    private int oldy;

    public DragViewListener(View dragView) {
        this.dragView = dragView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // タッチしている位置取得
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // 今回イベントでのView移動先の位置
                int left = dragView.getLeft() + (x - oldx);
                int top = dragView.getTop() + (y - oldy);
                // Viewを移動する
                dragView.layout(left, top, left + dragView.getWidth(), top
                        + dragView.getHeight());
                System.out.print(view.getWidth());
                break;
             case MotionEvent.ACTION_UP:

        }

        // 今回のタッチ位置を保持
        oldx = x;
        oldy = y;
        // イベント処理完了
        return true;
    }
}

