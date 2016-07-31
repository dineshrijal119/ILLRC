package com.technocurl.www.parsejson;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    Button signup;
    EditText nam,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        /*EditText editText = (EditText) findViewById(R.id.publisher);
        editText.addTextChangedListener(new FourDigitCardFormatWatcher());*/
        imageView=(ImageView)findViewById(R.id.ilrc);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .fontSize(40) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound("ILRC", Color.parseColor("#004D40"));
        imageView.setImageDrawable(drawable);
        signup=(Button)findViewById(R.id.btn_signup);
        nam=(EditText) findViewById(R.id.input_name);
        phone=(EditText) findViewById(R.id.input_phone);
        email=(EditText)findViewById(R.id.input_email);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:

                if (!nam.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !phone.getText().toString().isEmpty() || phone.getText().toString().length() == 10 ){
                    startActivity(new Intent(MainActivity.this,MainPage.class));
                }else {
                    if (nam.getText().toString().isEmpty()){
                        nam.setError("Please Insert Name");
                    }
                    if (email.getText().toString().isEmpty()){
                        email.setError("Please Insert Email");
                    }
                    if (phone.getText().toString().isEmpty() || phone.getText().toString().length() < 10 && phone.getText().toString().length() > 10){
                        phone.setError("Insert VAlid Phone");
                    }
                }
                break;
        }
    }
}
