package com.technocurl.www.parsejson.kanun;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.nepali.Detailsrow;
import com.technocurl.www.parsejson.nepali.NajirrecycleviewAdapter;

import java.util.ArrayList;

/**
 * Created by dinesh on 9/10/16.
 */
public class Kanunsearchlist extends AppCompatActivity {
    RecyclerView recyclerView;
    KanunlistAdapter kanunlistAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanun_search_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setTitle("कानूनको खोज");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        final ArrayList<Kanunimodel> myList = (ArrayList<Kanunimodel>) getIntent().getSerializableExtra("mylist");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kanunlistAdapter = new KanunlistAdapter(this, myList);
        recyclerView.setAdapter(kanunlistAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                view.setSelected(true);
//                view.setBackgroundColor(Color.BLUE);
                Intent intent = new Intent(Kanunsearchlist.this, Kanundetailspage.class);
                intent.putExtra("mylist", myList);
                intent.putExtra("position", position);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {
                Intent intent = new Intent(Kanunsearchlist.this, Kanunalldetails.class);
                intent.putExtra("mylist", myList);
                intent.putExtra("position", position);
                startActivity(intent);


            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(Kanunsearchlist.this,KanunnepaliAactivity.class));
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
        private Kanunsearchlist.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Kanunsearchlist.ClickListener clickListener) {
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
