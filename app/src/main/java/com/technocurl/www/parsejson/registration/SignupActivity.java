package com.technocurl.www.parsejson.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.technocurl.www.parsejson.MainPage;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.databases.IllrcDatabases;
import com.technocurl.www.parsejson.utility.Tags;

public class SignupActivity extends AppCompatActivity {
    private IllrcDatabases illrcDatabases;
    String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illrcDatabases= new IllrcDatabases(SignupActivity.this);
        illrcDatabases.getWritableDatabase();
        illrcDatabases.getReadableDatabase();
        setContentView(R.layout.signup);
        try {
            status=illrcDatabases.getStatus();
            if (status.equals("1")){
               startActivity(new Intent(this,MainPage.class));
            }else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,new Checkponefragment())
                        .commit();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            try {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,new Checkponefragment())
                        .commit();
            }catch (NullPointerException e1){
                e1.printStackTrace();

            }
        }


    }


}
