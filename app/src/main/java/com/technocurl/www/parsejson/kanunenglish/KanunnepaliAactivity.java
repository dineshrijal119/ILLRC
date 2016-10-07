package com.technocurl.www.parsejson.kanunenglish;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.technocurl.www.parsejson.HttpUrlConnectionJson;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.SpinnerAdapter;
import com.technocurl.www.parsejson.custumclasses.Progressillrc;
import com.technocurl.www.parsejson.databases.IllrcDatabases;
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.Globalvariable;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dinesh on 9/6/16.
 */
public class KanunnepaliAactivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    String chetragat_bargikaran, kanuni_bargikaran;
    Progressillrc progressDialog;
    int id_b, id_k;
    String suchi_get;
    Button kanuni_khoj,thya;

    ArrayList<String> suchilist =  new ArrayList<>();

    IllrcDatabases illrcDatabases;
    Cursor chetragat_cursor;
    private SQLiteDatabase db;
    String phone,security;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanun_nepali_main);
        illrcDatabases= new IllrcDatabases(this);
        illrcDatabases.getReadableDatabase();
        illrcDatabases.getReadableDatabase();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setTitle("कानून");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        kanuni_khoj = (Button) findViewById(R.id.kanuni_khoj);
        kanuni_khoj.setOnClickListener(this);
        thya=(Button)findViewById(R.id.thya);
        thya.setOnClickListener(this);
        Spinner chetragat = (Spinner) findViewById(R.id.category_chetragat);
       ArrayList<String> spinnerData_chetragat = illrcDatabases.getkanunchetragat();
        ArrayList<String> spinnerData_kanuni = illrcDatabases.getkanunbisayagat();
        phone=((Globalvariable)this.getApplication()).getCell_phone();
        security=((Globalvariable)this.getApplication()).getUniqueid();
        SpinnerAdapter adapter_ntc = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, spinnerData_chetragat);
        chetragat.setAdapter(adapter_ntc);
        chetragat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chetragat_bargikaran = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                chetragat_bargikaran = "सिक्षा";

            }
        });
        Spinner kanuni = (Spinner) findViewById(R.id.category_kanuni);
       /* ArrayList<String> spinnerData_kanuni = new ArrayList<>();
        spinnerData_kanuni.add("ऐन");
        spinnerData_kanuni.add("नियमावलि");
        spinnerData_kanuni.add("संबिधान");
        spinnerData_kanuni.add("बिनियम");
        spinnerData_kanuni.add("मुलुकि ऐन");
        spinnerData_kanuni.add("अन्तराष्टिय सन्धि तथा महासन्धि");*/
        SpinnerAdapter adapter_spinnerData_kanuni = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, spinnerData_kanuni);
        kanuni.setAdapter(adapter_spinnerData_kanuni);
        kanuni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kanuni_bargikaran = parent.getSelectedItem().toString();
                if (kanuni_bargikaran.length() > 1) {
                    new getSuch().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                chetragat_bargikaran = "ऐन";

            }
        });
       /* Spinner suchi = (Spinner) findViewById(R.id.suchi);
        ArrayList<String> spinnerData_suchi = new ArrayList<>();
        spinnerData_suchi.add("अख्तियार दुरुपयोग अनुसन्धान आयोग ऐन, २०४८");
        SpinnerAdapter adapter_spinnerData_suchi = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, spinnerData_suchi);
        suchi.setAdapter(adapter_spinnerData_suchi);
        suchi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kanuni_bargikaran = parent.getSelectedItem().toString();
                if (kanuni_bargikaran.length() > 1) {
//                    new getSuch().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                chetragat_bargikaran = "ऐन";

            }
        });*/


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kanuni_khoj:

                new getLawall().execute();
                break;

            case R.id.thya:

                new  getTathyaall().execute();
                break;
        }
    }

    public class getSuch extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            JSONObject jsonObject = new JSONObject();
            String suchi_list = null;
            try {

                jsonObject.put("LawMainGrp", kanuni_bargikaran);
                jsonObject.put("LawGroup", chetragat_bargikaran);
                Log.d("dinesh", "post : " + jsonObject);
                suchi_list = httpUrlConnectionJson.sendHTTPData(Constants.GET_SUCHI, jsonObject);
                Log.d("dinesh", "response : " + suchi_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return suchi_list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new Progressillrc(KanunnepaliAactivity.this);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject  jsonObject = new JSONObject(s);
                boolean success = jsonObject.getBoolean("success");
                if (success == true){
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for ( int i = 0;i < jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String Name = jsonObject1.getString("Name");
                        suchilist.add(Name);


                    }
                    Spinner suchi = (Spinner) findViewById(R.id.suchi);
                    ArrayAdapter<String> spinerAdapter= new ArrayAdapter<String>(KanunnepaliAactivity.this,android.R.layout.simple_spinner_item, suchilist);
                    spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    suchi.setAdapter(spinerAdapter);
                    suchi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            suchi_get = parent.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            suchi_get = suchilist.get(0);

                        }
                    });
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }

    public class getLawall extends AsyncTask<String, String, String> {
        ArrayList<Kanunimodel> kanunimodelArrayList = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            JSONObject jsonObject = new JSONObject();
            String suchi_list = null;
            try {
                jsonObject.put(Tags.PHONE,phone);
                jsonObject.put(Tags.SECURITY,security);
                jsonObject.put("LawSubGrp", suchi_get);
                Log.d("dinesh", "law post  : " + jsonObject);
                suchi_list = httpUrlConnectionJson.sendHTTPData(Constants.GET_KANUNI_KHOJ, jsonObject);
                Log.d("dinesh", "law response : " + suchi_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return suchi_list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new Progressillrc(KanunnepaliAactivity.this);
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
                String message = jsonObject.getString("message");
                if (success == true) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Kanunimodel kanunimodel = new Kanunimodel();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String Sn = jsonObject1.getString("Sn");
                        String Dapha = jsonObject1.getString("Dapha");
                        String Detailnow = jsonObject1.getString("Detailnow");
                        String id = jsonObject1.getString("id");
                        String Detailold = jsonObject1.getString("Detailold");
                        kanunimodel.setSn(Sn);
                        kanunimodel.setDapha(Dapha);
                        kanunimodel.setDetailnow(Detailnow);
                        kanunimodel.setId(id);
                        kanunimodel.setDetailold(Detailold);
                        kanunimodelArrayList.add(kanunimodel);
                    }
                    if (jsonArray.length() > 0) {
                        Intent intent = new Intent(KanunnepaliAactivity.this, Kanunsearchlist.class);
                        intent.putExtra("mylist", kanunimodelArrayList);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }

                }

            } catch (Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "No data found. Please make sure your payment.", Toast.LENGTH_LONG).show();
            }

        }
    }


    public class getTathyaall extends AsyncTask<String, String, String> {
        ArrayList<Tathya> tathyas = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            JSONObject jsonObject = new JSONObject();
            String suchi_list = null;
            try {
                jsonObject.put(Tags.PHONE,phone);
                jsonObject.put(Tags.SECURITY,security);
                jsonObject.put("LawSubGrp", suchi_get);
                Log.d("dinesh", "law post  : " + jsonObject);
                suchi_list = httpUrlConnectionJson.sendHTTPData(Constants.GET_TATHYA, jsonObject);
                Log.d("dinesh", "law response : " + suchi_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return suchi_list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new Progressillrc(KanunnepaliAactivity.this);
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
                String message = jsonObject.getString("message");
                if (success == true) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Tathya kanunimodel = new Tathya();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String Sn = jsonObject1.getString("Sn");
                        String LawSubGroupId = jsonObject1.getString("LawSubGroupId");
                        String Name = jsonObject1.getString("Name");
                        kanunimodel.setSn(Sn);
                        kanunimodel.setLawSubGroupId(LawSubGroupId);
                        kanunimodel.setName(Name);
                        tathyas.add(kanunimodel);
                    }
                    if (jsonArray.length() > 0) {
                        Intent intent = new Intent(KanunnepaliAactivity.this, Tathyaserchlist.class);
                        intent.putExtra("mylist", tathyas);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"No data found please make sure your payment.", Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
