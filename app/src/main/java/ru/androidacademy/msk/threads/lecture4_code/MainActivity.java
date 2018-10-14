package ru.androidacademy.msk.threads.lecture4_code;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button threads = findViewById(R.id.threads_button);
        Button executors = findViewById(R.id.executors_button);
        Button asyncTasks = findViewById(R.id.async_task_button);
        Button rx = findViewById(R.id.rx_button);

        threads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ru.androidacademy.msk.threads.lecture4_code.ThreadsActivity.startActivity(MainActivity.this);
            }
        });

        executors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorsActivity.startActivity(MainActivity.this);
            }
        });

        asyncTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskActivity.startActivity(MainActivity.this);
            }
        });

        // same as previous, but written as lambda
        rx.setOnClickListener(v -> RxActivity.startActivity(MainActivity.this));
    }
}
