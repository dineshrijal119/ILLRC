package com.technocurl.www.parsejson.nepali;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.model.NajirNepalimodel;

import java.util.ArrayList;

/**
 * Created by dinesh on 8/30/16.
 */
public class Detailsrowenglish  extends AppCompatActivity {
    int position;
    Toolbar toolbar;
    TextView description;
    TextView publication,Month,pub_year,RegisterId,ijlash,nirnayenumber,CaseType,Miti,Judge,Purnawedan,Partyashi,PurnawedanTarf,partyashiTarf,AttravtiveSubject,sn,page_no,adalat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_details_english);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Najir English ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        final ArrayList<NajirNepalimodel> myList = (ArrayList<NajirNepalimodel>) getIntent().getSerializableExtra("mylist");
        position=getIntent().getIntExtra("position",0);
        Log.d("dinesh","position: " +position);
        final NajirNepalimodel getdealData = myList.get(position);
        Judge=(TextView)findViewById(R.id.Judge);
        Judge.setText(getdealData.getJudge());
        Purnawedan=(TextView)findViewById(R.id.Purnawedan);
        Purnawedan.setText(getdealData.getPurnawedan());
        Partyashi=(TextView)findViewById(R.id.Partyashi);
        Partyashi.setText(getdealData.getPartyashi());
        PurnawedanTarf=(TextView)findViewById(R.id.PurnawedanTarf);
        PurnawedanTarf.setText(getdealData.getPurnawedanTarf());
        partyashiTarf=(TextView)findViewById(R.id.partyashiTarf);
        partyashiTarf.setText(getdealData.getPartyashiTarf());
        AttravtiveSubject=(TextView)findViewById(R.id.AttravtiveSubject);
        AttravtiveSubject.setText(getdealData.getAttravtiveSubject());

        description=(TextView)findViewById(R.id.description);
        description.setText(getdealData.getFile());


    }
}
