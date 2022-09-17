package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView todoList;
    Button addActivity;

    ArrayList<ActivityModel> activityList;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        activityList = dbHelper.getAllActivities();
        todoList = findViewById(R.id.todoList);
        addActivity = (Button) findViewById(R.id.addActivity);


        addActivity.setOnClickListener(this);

        todoList.setAdapter(new ActivityAdapter(this,activityList,dbHelper));
    }
    public void goToAddActivity() {
        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        startActivity(intent);
    }

    private String createReport(){
        StringBuilder str = new StringBuilder();
        for (ActivityModel activity: activityList
             ) {
            str.append("Info: ").append(activity.getActivityInfo());
            str.append(" Due-Date: ").append(activity.getDate());
            str.append(" Status: ").append((activity.getStaus())?"Done":"Pending");
            str.append("\n");
        }
        return str.toString();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addActivity:
                goToAddActivity();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}