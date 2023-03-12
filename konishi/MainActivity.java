package com.example.app_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public int bt_counter = 0;


    public List<TaskCircle> Store_Task = new ArrayList<>();

    public void btnCurrentClick(View view) {
        ConstraintLayout task_layout = (ConstraintLayout) findViewById(R.id.task_layout);
        TaskCircle task = new TaskCircle(this,bt_counter);
//        DragViewListener listener = new DragViewListener(task);
        DragAngleListener listener = new DragAngleListener(task);
        double Angle = task.startAngle;
//        TouchChecker listener = new TouchChecker(task,Angle);
        task.setOnTouchListener(listener);
        Store_Task.add(task);
        task_layout.addView(Store_Task.get(bt_counter));
        bt_counter +=1;



    }
}




