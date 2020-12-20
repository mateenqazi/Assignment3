package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivityAss2_page3 extends AppCompatActivity {

    TextView text;
    int score;
    int totalMCQs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ass2_page3);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        totalMCQs = intent.getIntExtra("total", 0);

        text = findViewById(R.id.score);
        text.setText(String.valueOf(score));

        String remarks = "";
        int per1 = score/totalMCQs*100;
        String per= "Percentage is "+ Integer.toString(per1)+"%";


        remarks = "You have answered " + score + " MCQs correctly out of " + totalMCQs + " MCQs ";


        text = findViewById(R.id.remarks);
        text.setText(remarks);

        text = findViewById(R.id.percentage);
        text.setText(per);
    }

    public void Start_Again(View view)
    {
        finish();
        return;
    }

    public  void ReturnHome(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}