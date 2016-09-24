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
import android.widget.Toast;

import com.technocurl.www.parsejson.HttpUrlConnectionJson;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.ServiceHandler;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.nepali.DetailsrowlistActivity;
import com.technocurl.www.parsejson.nepali.NajirrecycleviewAdapter;
import com.technocurl.www.parsejson.utility.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by dinesh on 9/11/16.
 */
public class Kanunalldetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView today,old;
    int position;
    ProgressDialog progressDialog;
    ArrayList<NajirNepalimodel> najirNepalimodels = new ArrayList<>();
    RecyclerView recyclerView;
    ReleatednajirAdapter kanunlistAdapter;
    LinearLayout visibility,show_visibility;
    TextView details_najir_kanun;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanun_all_details);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setTitle("कानूनको खोज बिस्तृत");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        old=(TextView)findViewById(R.id.old_situation);
        today=(TextView)findViewById(R.id.today_situation);
        visibility=(LinearLayout)findViewById(R.id.visibility);
        show_visibility=(LinearLayout)findViewById(R.id.visibility_show);
        details_najir_kanun = (TextView)findViewById(R.id.details_najir_kanun);
        final ArrayList<Kanunimodel> myList = (ArrayList<Kanunimodel>) getIntent().getSerializableExtra("mylist");
        position = getIntent().getIntExtra("position", 0);
        today.setText(myList.get(position).getDetailnow());
        new getnearbynajir(myList.get(position).getId()).execute();
        Log.d("dinesh","Id : "+myList.get(position).getId()+"position : " + position);
        recyclerView = (RecyclerView) findViewById(R.id.related_najir);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public class getnearbynajir extends AsyncTask<String, String, String> {
        String id;
        public getnearbynajir(String id){
            this.id=id;
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            JSONObject jsonObject = new JSONObject();
            String suchi_list = null;
            try {
                jsonObject.put("LawSubGrPId", id);
                Log.d("dinesh", "law post  : " + jsonObject);
                suchi_list = httpUrlConnectionJson.sendHTTPData(Constants.GET_NEARBY_NAJIR, jsonObject);
                Log.d("dinesh", "law response : " + suchi_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return suchi_list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Kanunalldetails.this);
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

                    kanunlistAdapter = new ReleatednajirAdapter(Kanunalldetails.this, najirNepalimodels);
                    recyclerView.setAdapter(kanunlistAdapter);
                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                                   /* String sn= najirNepalimodels.get(position).getSN();
                            new Callreletednajir(sn).execute();*/
                            show_visibility.setVisibility(View.GONE);
                            visibility.setVisibility(View.VISIBLE);
                            details_najir_kanun.setText(najirNepalimodels.get(position).getFile());
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
        private Kanunalldetails.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Kanunalldetails.ClickListener clickListener) {
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

    public class Callreletednajir extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(Kanunalldetails.this);
        String sn;
        public Callreletednajir(String sn){
            this.sn=sn;
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection conn = null;
            String url = null;
            String jsonStr = "";
            try {
                url = "http://43.245.238.134:105/api/entry/getentry/"+sn;
                        ServiceHandler sh = new ServiceHandler();
                Log.d("dinesh",url);
                // Making a request to url and getting response
                jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonStr;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.hide();
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    NajirNepalimodel najirNepalimodel = new NajirNepalimodel();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String SN = jsonObject.getString("SN");
                    String Publication = jsonObject.getString("Publication");
                    String Adalat = jsonObject.getString("Adalat");
                    String RegisterId = jsonObject.getString("RegisterId");
                    String Miti = jsonObject.getString("Miti");
                    String Judge = jsonObject.getString("Judge");
                    String Purnawedan = jsonObject.getString("Purnawedan");
                    String Partyashi = jsonObject.getString("Partyashi");
                    String PurnawedanTarf = jsonObject.getString("PurnawedanTarf");
                    String partyashiTarf = jsonObject.getString("partyashiTarf");
                    String AttravtiveSubject = jsonObject.getString("AttravtiveSubject");
                    String PageNumber = jsonObject.getString("PageNumber");
                    String Pubyear=jsonObject.getString("Pubyear");
                    String Month = jsonObject.getString("Month");
                    String SubjectCaseType = jsonObject.getString("SubjectCaseType");
                    String NirnayeNumber = jsonObject.getString("NirnayeNumber");
                    String CaseType = jsonObject.getString("CaseType");
                    String File = jsonObject.getString("File");
                    try {
                        if (File.length() > 5){
                       Intent intent = new Intent(Kanunalldetails.this,RelatednajirActivity.class);
                            intent.putExtra("dt",File);
                            startActivity(intent);
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }


                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("loading..");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
    }

}
