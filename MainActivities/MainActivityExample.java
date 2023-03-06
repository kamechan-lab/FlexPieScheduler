package com.test.schedule2;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
亀井がテスト用に使っているMainActivityです。いつも亀井がアップロードしているような状態まで持っていく場合に使ってください。
**/
public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text_date = findViewById(R.id.text_date);
        text_date.setText(LocalDateTime.of(2023, 1, 17, 1, 1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ConstraintLayout root = findViewById(R.id.container);
        View.inflate(this, R.layout.clockdial2, root);
    }
}
