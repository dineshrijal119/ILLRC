package com.technocurl.www.parsejson.najiarenglish;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.utility.Tags;

import java.util.ArrayList;

/**
 * Created by dinesh on 8/30/16.
 */
public class DetailsrowlistActivityenglish extends AppCompatActivity {
    RecyclerView recyclerView;
    NajirrecycleviewAdapterenglish najirrecycleviewAdapter;
    Toolbar toolbar;
    String sabdha="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.najir_rsult_general_eng);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Najir English List");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsrowlistActivityenglish.this,EnglishMain.class));
            }
        });
        final ArrayList<NajirNepalimodel> myList = (ArrayList<NajirNepalimodel>) getIntent().getSerializableExtra("mylist");
        sabdha=getIntent().getStringExtra("sabdha");
        Log.d(Tags.TAG,"sabdha : "+sabdha);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        najirrecycleviewAdapter=new NajirrecycleviewAdapterenglish(this,myList);
        recyclerView.setAdapter(najirrecycleviewAdapter);
        recyclerView.setSelected(true);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                najirrecycleviewAdapter.setSelected(position);
                Intent intent = new Intent(DetailsrowlistActivityenglish.this, Detailsrowenglish.class);
                intent.putExtra("mylist", myList);
                intent.putExtra("position",position);
                intent.putExtra("sabdha",sabdha);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private DetailsrowlistActivityenglish.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final DetailsrowlistActivityenglish.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}