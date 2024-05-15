package com.ahmadsapplication.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNext, btnCheck;
    private EditText edtUserName, edtPalindrom;
    public static SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        Init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                String palindromTxt = edtPalindrom.getText().toString();
                if (CheckPalindrom(palindromTxt)){
                    builder.setMessage("isPalindrom");
                    builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
                else {
                    builder.setMessage("not palindrom");
                    builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtUserName.getText().toString().isEmpty()) {
                    sharedPref.edit().putString(getString(R.string.preference_file_key),
                            edtUserName.getText().toString()).apply();
                }
                Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }

    private void Init(){
        btnCheck = findViewById(R.id.btnCheck);
        btnNext = findViewById(R.id.btnNext);
        edtPalindrom = findViewById(R.id.edtPalindrom);
        edtUserName = findViewById(R.id.edtUserName);
        Context context = this;
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

    }

    private boolean CheckPalindrom(String txt){

        int strLength = txt.length();
        String reverseStr = "";
        for (int i = (strLength - 1); i >=0; --i) {
            reverseStr = reverseStr + txt.charAt(i);
        }

        if (txt.toLowerCase().equals(reverseStr.toLowerCase())) {
            return true;
        }
        else {
            return false;
        }
    }
}