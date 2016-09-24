package com.technocurl.www.parsejson.kanun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;

/**
 * Created by dinesh on 9/12/16.
 */
public class RelatednajirActivity extends AppCompatActivity {
    TextView details;
    String dt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.related_najir_kanun);
        details=(TextView)findViewById(R.id.details);
        dt=getIntent().getStringExtra("dt");
        try {
            details.setText(dt);
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }
}
