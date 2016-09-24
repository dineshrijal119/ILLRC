package com.technocurl.www.parsejson.kanun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technocurl.www.parsejson.HttpUrlConnectionJson;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dinesh on 9/13/16.
 */
public class Tathyadetails extends AppCompatActivity {
    String id;
    ProgressDialog progressDialog;
    RecyclerView recyclerView,recyclerView_najir;
    kanunitathyaadapter kanunlistAdapter;
    Toolbar toolbar;
    ArrayList<NajirNepalimodel> najirNepalimodels = new ArrayList<>();
    ReleatednajirAdapter releatednajirAdapter;
    LinearLayout visibility,visibility_show;
    TextView details_najir_tathya;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tathya_details);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("तथ्थयगत खोज ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        id=getIntent().getStringExtra("id");
        final ArrayList<Tathya> myList = (ArrayList<Tathya>) getIntent().getSerializableExtra("mylist");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView_najir=(RecyclerView)findViewById(R.id.related_najir);
        visibility = (LinearLayout)findViewById(R.id.visibility);
        visibility_show= (LinearLayout)findViewById(R.id.visibility_show);
        details_najir_tathya = (TextView)findViewById(R.id.details_najir_tathya);
        recyclerView_najir.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kanunlistAdapter = new kanunitathyaadapter(this, myList);
        recyclerView.setAdapter(kanunlistAdapter);
        new getThyadetails(id).execute();
    }



    public class getThyadetails extends AsyncTask<String, String, String> {
        String id;
        public getThyadetails(String id){
            this.id=id;
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            JSONObject jsonObject = new JSONObject();
            String suchi_list = null;
            try {
                jsonObject.put("Tathaya", id);
                Log.d("dinesh", "thya najir post  : " + jsonObject);
                suchi_list = httpUrlConnectionJson.sendHTTPData(Constants.TATHYA_CLICK_NAJIR, jsonObject);
                Log.d("dinesh", "thya najir response : " + suchi_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return suchi_list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Tathyadetails.this);
            progressDialog.setMessage("Loading..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                progressDialog.dismiss();
                JSONObject jsonObject = new JSONObject(s);
                boolean success = jsonObject.getBoolean("success");
                if (success == true) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        NajirNepalimodel najirNepalimodel  = new NajirNepalimodel();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String SN = jsonObject1.getString("SN");
                        String Publication = jsonObject1.getString("Publication");
                        String PageNumber = jsonObject1.getString("PageNumber");
                        String NirnayeNumber = jsonObject1.getString("NirnayeNumber");
                        String RegisterId = jsonObject1.getString("RegisterId");
                        String Adalat = jsonObject1.getString("Adalat");
                        String Month = jsonObject1.getString("Month");
                        String Pubyear = jsonObject1.getString("Pubyear");
                        String CaseType = jsonObject1.getString("CaseType");
                        String Ijlash = jsonObject1.getString("Ijlash");
                        String File = jsonObject1.getString("File");
                        najirNepalimodel.setPubyear(Pubyear);
                        najirNepalimodel.setSN(SN);
                        najirNepalimodel.setCaseType(CaseType);
                        najirNepalimodel.setMonth(Month);
                        najirNepalimodel.setIjlash(Ijlash);
                        najirNepalimodel.setFile(File);
                        najirNepalimodels.add(najirNepalimodel);
                    }
                    releatednajirAdapter = new ReleatednajirAdapter(Tathyadetails.this, najirNepalimodels);
                    recyclerView_najir.setAdapter(releatednajirAdapter);
                    recyclerView_najir.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView_najir, new ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            String sn= najirNepalimodels.get(position).getSN();
                            visibility_show.setVisibility(View.GONE);
                            visibility.setVisibility(View.VISIBLE);

                            details_najir_tathya.setText(najirNepalimodels.get(position).getFile());
                            Log.d(Tags.TAG,najirNepalimodels.get(position).getFile());
                        }
                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
        private Tathyadetails.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Tathyadetails.ClickListener clickListener) {
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
