package com.example.languagecenter.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.languagecenter.R;
import com.example.languagecenter.Students;

public class StudentActivity extends AppCompatActivity {
    private String KEY_ACCOUNT = "KEY_ACCOUNT_LANGUAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Students students = (Students) bundle.getSerializable(KEY_ACCOUNT);
        Log.d("abc", students.toString());
    }
}