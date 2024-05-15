package com.ahmadsapplication.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String namaUser;
    private ImageView backButtonSecond;
    private Button btnChooseUser;
    private TextView txtUserName, txtSelectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Init();

    }

    @Override
    protected void onStart() {
        super.onStart();

        namaUser = getIntent().getStringExtra("userName");
        if (namaUser != null){
            txtSelectedName.setText(namaUser);
        }
        String contain = MainActivity.sharedPref.getString(getString(R.string.preference_file_key),"");
        txtUserName.setText(contain);

        backButtonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SecondActivity.this, MainActivity.class);
                SecondActivity.this.startActivity(myIntent);

            }
        });

        btnChooseUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                SecondActivity.this.startActivity(myIntent);


            }
        });
    }

    private void Init(){
        backButtonSecond = findViewById(R.id.backSecondScreen);
        txtUserName = findViewById(R.id.txtUserName);
        txtSelectedName = findViewById(R.id.txtSelectedUserN);
        btnChooseUser = findViewById(R.id.btnChooseAUser);
    }
}