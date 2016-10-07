package com.technocurl.www.parsejson.najiarenglish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.utility.Tags;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dinesh on 8/30/16.
 */
public class Detailsrowenglish extends AppCompatActivity {
    int position;
    Toolbar toolbar;
    TextView description;
    Button next, preview;
    int count;
    String sabdha = "";
    TextView publication, Month, pub_year, RegisterId, ijlash, nirnayenumber, CaseType, Miti, Judge, Purnawedan, Partyashi, PurnawedanTarf, partyashiTarf, AttravtiveSubject, sn, page_no, adalat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_details_eng);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Najir Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        final ArrayList<NajirNepalimodel> myList = (ArrayList<NajirNepalimodel>) getIntent().getSerializableExtra("mylist");
        position = getIntent().getIntExtra("position", 0);
        Log.d("dinesh", "position: " + position);
        final NajirNepalimodel getdealData = myList.get(position);
        sabdha=getIntent().getStringExtra("sabdha");
        count = position;
        description = (TextView) findViewById(R.id.description);
        SpannableStringBuilder sb = new SpannableStringBuilder(getdealData.getFile());
        Pattern p = Pattern.compile(sabdha, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(getdealData.getFile());
        while (m.find()){
            sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }

        description.setText(sb);
        Log.d(Tags.TAG,"bold sabdha : " + sabdha);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                Log.d(Tags.TAG,"count positive : " + count);
                if (count > 0){
                    if (myList.size() > count) {
//                        description.setText(myList.get(count).getFile());

                        SpannableStringBuilder sb = new SpannableStringBuilder(myList.get(count).getFile());
                        Pattern p = Pattern.compile(sabdha, Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(getdealData.getFile());
                        while (m.find()){
                            sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                        }

                        description.setText(sb);

                    } else {
                    }
                }


            }
        });
        preview = (Button) findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                Log.d(Tags.TAG,"negative count : " + count);
                if (count > 0) {
//                    description.setText(myList.get(count).getFile());


                    SpannableStringBuilder sb = new SpannableStringBuilder(myList.get(count).getFile());
                    Pattern p = Pattern.compile(sabdha, Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(getdealData.getFile());
                    while (m.find()){
                        sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    }

                    description.setText(sb);

                } else {

                }
            }
        });

    }
}
