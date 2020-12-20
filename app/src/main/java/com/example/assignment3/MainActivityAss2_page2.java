package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivityAss2_page2 extends AppCompatActivity {
    Button nextQuestion;
    RadioGroup radioGroup;
    List<RadioButton> options;
    String correctOption;
    int toggle = 0;
    ArrayList<List<String>> arrayList;
    int current_MCQ;
    final int maxMCQNo = 10;
    final int totalMCQs = 5;
    List<Integer> randomList;
    int score;
    TextView textViewForQuestions;
    TextView question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ass2_page2);

        textViewForQuestions = findViewById(R.id.question_no);
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radioGroup);
        nextQuestion = findViewById(R.id.nextButton);

        int count = radioGroup.getChildCount();
        options = new ArrayList<RadioButton>();
        for (int i = 0; i < count; i++)
        {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton)
                options.add((RadioButton)o);
        }

        randomList = new ArrayList<Integer>();

        for (int i = 1; i <= 4; i++)
            randomList.add(i);

        arrayList = new ArrayList<List<String>>();
        String []data;
        int mcqID;

        for (int i = 0; i <= 9; i++)
        {
            mcqID = this.getResources().getIdentifier("mcq"+i, "array", this.getPackageName());
            data = getResources().getStringArray(mcqID);
            arrayList.add(asList(data));
        }

        Collections.shuffle(arrayList);
        showNextMCQ();
    }

    private void showNextMCQ()
    {
        if (current_MCQ < totalMCQs)
        {
            int j = 0;
            List<String> MCQ = arrayList.get(current_MCQ);
            textViewForQuestions.setText(String.valueOf(current_MCQ + 1));
            question.setText(MCQ.get(0));
            correctOption = MCQ.get(1);
            Collections.shuffle(randomList);

            for (Iterator<RadioButton> i = options.iterator(); i.hasNext(); ) {
                RadioButton rb = i.next();
                rb.setBackgroundColor(Color.TRANSPARENT);
                rb.setEnabled(true);
                rb.setText(MCQ.get(randomList.get(j)));
                j++;
            }

            current_MCQ++;
        }
        else
        {
            Intent intent = new Intent(this, MainActivityAss2_page3.class);
            intent.putExtra("score", score);
            intent.putExtra("total", totalMCQs);
            startActivity(intent);

            finish();
        }
    }

    public void nextMCQ(View view)
    {
        if (1 - toggle  == 1)
        {
            int id = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);
            RadioButton rb1 = null;

            if (rb.getText().toString().equals(correctOption))
            {
                rb.setBackgroundColor(Color.GREEN);
                score++;
            }
            else
            {
                rb.setBackgroundColor(Color.RED);

                for (Iterator<RadioButton> i = options.iterator(); i.hasNext();)
                {
                    rb1 = i.next();
                    if(rb1.getText().toString().equals(correctOption))
                    {
                        rb1.setBackgroundColor(Color.GREEN);
                        break;
                    }
                }
            }
            for (Iterator<RadioButton> i = options.iterator(); i.hasNext();)
            {
                RadioButton rb2 = i.next();
                rb2.setEnabled(false);
            }
            toggle = 1- toggle;
            if (current_MCQ == totalMCQs)
                nextQuestion.setText("SUBMIT");
            else
                nextQuestion.setText("NEXT Question");
        }
        else
        {
            toggle = 1- toggle;
            if (current_MCQ != totalMCQs)
                nextQuestion.setText("VIEW ANSWER");
            showNextMCQ();
        }
    }
}