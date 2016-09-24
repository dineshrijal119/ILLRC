package com.technocurl.www.parsejson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by dinesh on 8/10/16.
 */
public class Testactivity extends AppCompatActivity  {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.layout.test_fragment);
       /* Button click = (Button)findViewById(R.id.click);
        click.setOnClickListener(this);*/
    }

  /*  @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            cases R.id.click:
                startActivity(new Intent(Testactivity.this,NajirEnglishGeneral.class));
                break;

        }
    }*/
}
