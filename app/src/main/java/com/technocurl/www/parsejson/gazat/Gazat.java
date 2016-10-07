package com.technocurl.www.parsejson.gazat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.technocurl.www.parsejson.HttpUrlConnectionJson;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.ServiceHandler;
import com.technocurl.www.parsejson.SpinnerAdapter;
import com.technocurl.www.parsejson.custumclasses.Progressillrc;
import com.technocurl.www.parsejson.databases.IllrcDatabases;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.nepali.DetailsrowlistActivity;
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.Globalvariable;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by dinesh on 8/3/16.
 */
public class Gazat extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button search_gazet;
    String bargikaran_mantralagat, bargikaran_bisayegat, month, atirikta_sankhya, barsa;
    EditText pub_year;
    IllrcDatabases databases;
    String phone,security;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_search);
        databases= new IllrcDatabases(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setTitle("राजपत्र");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        search_gazet = (Button) findViewById(R.id.search_gazet);
        pub_year = (EditText) findViewById(R.id.pub_year);
        search_gazet.setOnClickListener(this);
        phone=((Globalvariable)this.getApplication()).getCell_phone();
        security=((Globalvariable)this.getApplication()).getUniqueid();
        ArrayList<String> gazet_mantralagat= databases.getgazetmantralayagat();
        ArrayList<String> spinnerData_bisayegat_bargikaran = databases.getgazetbisayagat();
        Spinner mantralagat_bargikaran = (Spinner) findViewById(R.id.mantralagat_bargikaran);
        SpinnerAdapter adapter_ntc = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, gazet_mantralagat);
        mantralagat_bargikaran.setAdapter(adapter_ntc);
        mantralagat_bargikaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bargikaran_mantralagat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                bargikaran_mantralagat = "भुमि";

            }
        });
        Spinner bisayegat_bargikaran = (Spinner) findViewById(R.id.bisayegat_bargikaran);
        SpinnerAdapter adapter_spinnerData_bisayegat_bargikaran = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, spinnerData_bisayegat_bargikaran);
        bisayegat_bargikaran.setAdapter(adapter_spinnerData_bisayegat_bargikaran);
        bisayegat_bargikaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bargikaran_bisayegat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                bargikaran_bisayegat = "नियुक्ति";

            }
        });

        Spinner sankh_ati = (Spinner) findViewById(R.id.sankh_ati);
        ArrayList<String> spinnerData_sankh_ati = new ArrayList<>();
        spinnerData_sankh_ati.add("संख्या");
        spinnerData_sankh_ati.add("अति");
        SpinnerAdapter adapter_spinnerData_sankh_ati = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, spinnerData_sankh_ati);
        sankh_ati.setAdapter(adapter_spinnerData_sankh_ati);
        sankh_ati.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atirikta_sankhya = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                atirikta_sankhya = "संख्या";

            }
        });

        Spinner mahina = (Spinner) findViewById(R.id.mahina);
        ArrayList<String> spineer_mahina = new ArrayList<>();
        spineer_mahina.add("बैषाख");
        spineer_mahina.add("जेष्ठ");
        spineer_mahina.add("आषाढ");
        spineer_mahina.add("श्रावण");
        spineer_mahina.add("भाद्र");
        spineer_mahina.add("आश्विन");
        spineer_mahina.add("कार्तिक");
        spineer_mahina.add("मंसीर");
        spineer_mahina.add("पौष");
        spineer_mahina.add("माघ");
        spineer_mahina.add("फाल्गुण");
        spineer_mahina.add("चैत्र");
        SpinnerAdapter adapter_mahina = new SpinnerAdapter(this, R.layout.top_off_spinner_layout, spineer_mahina);
        mahina.setAdapter(adapter_mahina);
        mahina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                month = "बैषाख";

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_gazet:
                try {
                    barsa = pub_year.getText().toString();
                    new CallGazet().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public class CallGazet extends AsyncTask<String, String, String> {
        Progressillrc progressDialog = new Progressillrc(Gazat.this);
        ArrayList<Gazetmodel> gazetmodels = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection conn = null;
            String gazet = null;
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Tags.PHONE,phone);
                jsonObject.put(Tags.SECURITY,security);
                jsonObject.put(Tags.GAZAAT_MANTRALAGAT,bargikaran_mantralagat);
                jsonObject.put(Tags.GAZAT_BISAYAGAT,bargikaran_bisayegat);
                jsonObject.put(Tags.GAZAT_SANKHYA,atirikta_sankhya);
                jsonObject.put(Tags.GAZAT_BARSHA,barsa);
                jsonObject.put(Tags.GAZAT_MONTH,month);
                Log.d(Tags.TAG,"json post gazet : " + jsonObject);
                gazet=httpUrlConnectionJson.sendHTTPData(Constants.GET_GAZET_MAIN,jsonObject);
                Log.d(Tags.TAG,"json post response gazet : " + gazet);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gazet;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                progressDialog.hide();
                progressDialog.dismiss();
                JSONObject jsonObject_gazet = new JSONObject(s);
                boolean sucess = jsonObject_gazet.getBoolean("success");
                String message = jsonObject_gazet.getString("message");
                if (sucess==true){
                    JSONArray jsonArray = jsonObject_gazet.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Gazetmodel najirNepalimodel = new Gazetmodel();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String barsa = jsonObject.getString("barsha");
                        String sankhya = jsonObject.getString("sankhya");
                        String mantralaya = jsonObject.getString("mantralaya");
                        String bisaya = jsonObject.getString("bishaya");
                        String barema = jsonObject.getString("barema");
                        String miti = jsonObject.getString("miti");

                        najirNepalimodel.setId(id);
                        najirNepalimodel.setBarsha(barsa);
                        najirNepalimodel.setMantralaya(mantralaya);
                        najirNepalimodel.setSankhya(sankhya);
                        najirNepalimodel.setMiti(miti);
                        najirNepalimodel.setBishaya(bisaya);
                        najirNepalimodel.setBarema(barema);
                        gazetmodels.add(najirNepalimodel);
                    }
                    if (jsonArray.length() > 0) {
                        Intent intent = new Intent(Gazat.this, Detailslistgazet.class);
                        intent.putExtra("mylist", gazetmodels);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Gazat.this, message, Toast.LENGTH_LONG).show();
                    }
                }


            } catch (Exception e) {

                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"No data found please make sure your payment.", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
    }
}
