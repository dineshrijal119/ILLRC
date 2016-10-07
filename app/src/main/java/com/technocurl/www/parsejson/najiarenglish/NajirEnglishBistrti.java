package com.technocurl.www.parsejson.najiarenglish;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.Globalvariable;
import com.technocurl.www.parsejson.utility.Tags;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
/**
 * Created by deadlydragger on 7/9/16.
 */
public class NajirEnglishBistrti extends Fragment implements View.OnClickListener {
    String selectedItem;
    public static final String BASE_URL = "http://legalinfonepal.com:105/api/entry/searchasync/?";
    String publication = "", pageno = "", adalat = "", month = "", ijlash = "", darta_no = "", nirnayeno = "", judge="" ,pubyear = "",page="",subject="",sabdha="",pache_bipache="",kanunbebasahi="";


    EditText page_get,subject_get,pache_bipache_get,kanunbebasahi_get,sabdha_get,public_year,judge_get;
    int check=0;
    String phone,security;
    IllrcDatabases illrcDatabases;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.najir_main_bistritkhoj_eng, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button) view.findViewById(R.id.search);
        button.setOnClickListener(this);
        public_year=(EditText)view.findViewById(R.id.pub_year);
        page_get=(EditText)view.findViewById(R.id.page);
        subject_get=(EditText)view.findViewById(R.id.subject);
        pache_bipache_get=(EditText)view.findViewById(R.id.pache_bipache);
        kanunbebasahi_get=(EditText)view.findViewById(R.id.kanunbebasahi);
        sabdha_get=(EditText)view.findViewById(R.id.sabdha);
        judge_get=(EditText)view.findViewById(R.id.neyadhis);
        phone=((Globalvariable)getActivity().getApplication()).getCell_phone();
        security=((Globalvariable)getActivity().getApplication()).getUniqueid();

        ArrayList<String> lables = illrcDatabases.getnajirPublicationenglish();
        Spinner spinner_ntc = (Spinner) view.findViewById(R.id.spinner);

        SpinnerAdapter adapter_ntc = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, lables);
        spinner_ntc.setAdapter(adapter_ntc);
        spinner_ntc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                publication = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                publication = "Nekapa";

            }
        });
        Spinner adalat_main = (Spinner) view.findViewById(R.id.adalat);
        ArrayList<String> spineer_adalat = new ArrayList<>();
        spineer_adalat.add("Suprim");
        spineer_adalat.add("Apelleate");
        spineer_adalat.add("District");


        SpinnerAdapter adapter_adalat = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_adalat);
        adalat_main.setAdapter(adapter_adalat);
        adalat_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                adalat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adalat = "Suprim";

            }
        });


        Spinner mahina = (Spinner) view.findViewById(R.id.mahina);
        ArrayList<String> spineer_mahina = new ArrayList<>();
        spineer_mahina.add("Baishak");
        spineer_mahina.add("Jestha");
        spineer_mahina.add("Aashad");
        spineer_mahina.add("Shwran");
        spineer_mahina.add("Bhadra");
        spineer_mahina.add("Aswin");
        spineer_mahina.add("Kartik");
        spineer_mahina.add("Mansir");
        spineer_mahina.add("Push");
        spineer_mahina.add("Magh");
        spineer_mahina.add("Falgun");
        spineer_mahina.add("Chaitra");
        SpinnerAdapter adapter_mahina = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_mahina);
        mahina.setAdapter(adapter_mahina);
        mahina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                month = parent.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                month = "";

            }
        });

        Spinner ijlash_main = (Spinner) view.findViewById(R.id.ijlash);
        ArrayList<String> spineer_ijlash = new ArrayList<>();
        spineer_ijlash.add("Joint Bench");
        spineer_ijlash.add("Complete Bench ");
        spineer_ijlash.add("Special Bench");
        spineer_ijlash.add("Single Bench");

        SpinnerAdapter adapter_ijlash = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_ijlash);
        ijlash_main.setAdapter(adapter_ijlash);
        ijlash_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ijlash = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ijlash = "";
            }
        });

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illrcDatabases = new IllrcDatabases(getActivity());
        illrcDatabases.getReadableDatabase();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                try {
                    page=page_get.getText().toString();
                    subject=subject_get.getText().toString();
                    sabdha=sabdha_get.getText().toString();
                    pache_bipache=pache_bipache_get.getText().toString();
                    pubyear=public_year.getText().toString();
                    judge=judge_get.getText().toString();
                    kanunbebasahi=kanunbebasahi_get.getText().toString();
                    new Callnepalinajirsearct().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
        }
    }   public class Callnepalinajirsearct extends AsyncTask<String, String, String> {
        Progressillrc progressDialog = new Progressillrc(getActivity());
        ArrayList<NajirNepalimodel> najirNepalimodels = new ArrayList<>();
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection conn = null;
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            String najir = "";
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put(Tags.PHONE,phone);
                jsonObject.put(Tags.SECURITY,security);
                jsonObject.put(Tags.PUBLICATION,publication);
                jsonObject.put(Tags.ADALAT,adalat);
                jsonObject.put(Tags.MONTH,month);
                jsonObject.put(Tags.IJLASH,ijlash);
                jsonObject.put(Tags.PUBYEAR,pubyear);
                jsonObject.put(Tags.BISAYA,subject);
                jsonObject.put(Tags.PAGENUMBER,pageno);
                jsonObject.put(Tags.NIRNAYANUMBER,nirnayeno);
                jsonObject.put(Tags.JUDGE,judge);
                jsonObject.put(Tags.PACHYE_BIPACHYA,pache_bipache);
                jsonObject.put(Tags.SABDHA,sabdha);
                jsonObject.put(Tags.KANUNBEBASAHI,kanunbebasahi);

                Log.d(Tags.TAG,"najir post : " + jsonObject);
                najir= httpUrlConnectionJson.sendHTTPData(Constants.NAJIR_ENGLISH,jsonObject);
                Log.d(Tags.TAG,"najir response : " + najir);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return najir;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                progressDialog.dismiss();
                JSONObject jsonObject_first = new JSONObject(s);
                boolean success = jsonObject_first.getBoolean("success");
                String message = jsonObject_first.getString("message");
                if (success==true) {
                    JSONArray jsonArray = jsonObject_first.getJSONArray("data");
              /*  }
                JSONArray jsonArray = new JSONArray(s);*/
                    for (int i = 0; i < jsonArray.length(); i++) {
                        NajirNepalimodel najirNepalimodel = new NajirNepalimodel();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String SN = jsonObject.getString("SN");
                        String Publication = jsonObject.getString("Publication");
                        String Adalat = jsonObject.getString("Adalat");
                        String PageNumber = jsonObject.getString("PageNumber");
                        String Pubyear = jsonObject.getString("Pubyear");
                        String Month = jsonObject.getString("Month");
                        String SubjectCaseType = jsonObject.getString("SubjectCaseType");
                        String NirnayeNumber = jsonObject.getString("NirnayeNumber");
                        String File = jsonObject.getString("File");
                        najirNepalimodel.setSN(SN);
                        najirNepalimodel.setPublication(Publication);
                        najirNepalimodel.setAdalat(Adalat);
                        najirNepalimodel.setPageNumber(PageNumber);
                        najirNepalimodel.setPubyear(Pubyear);
                        najirNepalimodel.setMonth(Month);
                        najirNepalimodel.setSubjectCaseType(SubjectCaseType);
                        najirNepalimodel.setNirnayeNumber(NirnayeNumber);
                        najirNepalimodel.setFile(File);
                        najirNepalimodels.add(najirNepalimodel);
                    }
                    if (jsonArray.length() > 0) {
                        Intent intent = new Intent(getActivity(), DetailsrowlistActivityenglish.class);
                        intent.putExtra("mylist", najirNepalimodels);
                        intent.putExtra("sabdha",sabdha);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
/*
                Intent intent = new Intent(getActivity(), DetailsrowlistActivity.class);
                intent.putExtra("mylist",najirNepalimodels);
                startActivity(intent);*/
            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
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
