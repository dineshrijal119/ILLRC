package com.technocurl.www.parsejson.nepali;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deadlydragger on 7/9/16.
 */
public class NajirNepaliSamanya extends Fragment implements View.OnClickListener {
    Toolbar toolbar;
    String selectedItem;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;
    //    private AdapterFish mAdapter;
    public static final String BASE_URL = "http://legalinfonepal.com:105/api/entry/searchasync/?";
    String publication = "", pageno = "", adalat = "", month = "", ijlash = "", darta_no = "", bisaya = "", nirnayeno = "", nirnayemiti = "", pubyear = "";


    String category="",sabdhaanusar="";
    EditText pub_year,subject,nirnaya_no,page;
    IllrcDatabases illrcDatabases;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.najir_nepali_samanya, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.search);
        pub_year=(EditText)view.findViewById(R.id.pub_year);
        subject=(EditText)view.findViewById(R.id.subject);
        nirnaya_no=(EditText)view.findViewById(R.id.nirnayeno);
        page=(EditText)view.findViewById(R.id.page);
        button.setOnClickListener(this);

        illrcDatabases= new IllrcDatabases(getActivity());
        illrcDatabases.getReadableDatabase();


        // Spinner Drop down elements
        ArrayList<String> lables = illrcDatabases.getnajirPublication();


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
                publication = "नेकाप";

            }
        });


        Spinner adalat_main = (Spinner) view.findViewById(R.id.adalat);
        ArrayList<String> spineer_adalat = new ArrayList<>();
        spineer_adalat.add("सर्वोच्च");
        spineer_adalat.add("पुनराबेदन");
        spineer_adalat.add("जिल्ला");


        SpinnerAdapter adapter_adalat = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_adalat);
        adalat_main.setAdapter(adapter_adalat);
        adalat_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adalat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adalat = "सर्वोच्च";

            }
        });


        Spinner mahina = (Spinner) view.findViewById(R.id.mahina);
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
        SpinnerAdapter adapter_mahina = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_mahina);
        mahina.setAdapter(adapter_mahina);
        mahina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem = "बैषाख";

            }
        });

        Spinner ijlash_main = (Spinner) view.findViewById(R.id.ijlash);
        ArrayList<String> spineer_ijlash = new ArrayList<>();
        spineer_ijlash.add("संयुक्त इजलास");
        spineer_ijlash.add("पूर्ण इजलास ");
        spineer_ijlash.add("विशेष इजलास");
        spineer_ijlash.add("एकल इजलास");

        SpinnerAdapter adapter_ijlash = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_ijlash);
        ijlash_main.setAdapter(adapter_ijlash);
        ijlash_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ijlash = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ijlash = "संयुक्त इजलास";
            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                try {
                    pubyear=pub_year.getText().toString();
                    nirnayeno=nirnaya_no.getText().toString();
                    bisaya=subject.getText().toString();
                    pageno=page.getText().toString();
                    new Callnepalinajirsearct().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
        }
    }
    public class Callnepalinajirsearct extends AsyncTask<String, String, String> {
        Progressillrc progressDialog = new Progressillrc(getActivity());
        ArrayList<NajirNepalimodel> najirNepalimodels = new ArrayList<>();
        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            String najir = "";
           JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put(Tags.PUBLICATION,publication);
                jsonObject.put(Tags.ADALAT,adalat);
                jsonObject.put(Tags.MONTH,month);
                jsonObject.put(Tags.IJLASH,ijlash);
                jsonObject.put(Tags.PUBYEAR,pubyear);
                jsonObject.put(Tags.BISAYA,bisaya);
                jsonObject.put(Tags.PAGENUMBER,pageno);
                jsonObject.put(Tags.NIRNAYANUMBER,nirnayeno);
                jsonObject.put(Tags.JUDGE,"");
                jsonObject.put(Tags.PACHYE_BIPACHYA,"");
                jsonObject.put(Tags.SABDHA,"");
                jsonObject.put(Tags.KANUNBEBASAHI,"");

                Log.d(Tags.TAG,"najir post : " + jsonObject);
                najir= httpUrlConnectionJson.sendHTTPData(Constants.GET_NAJIR_MAIN,jsonObject);
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
                if (success==true) {
                    JSONArray jsonArray = jsonObject_first.getJSONArray("data");
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
                        najirNepalimodel.setNirnayeNumber(NirnayeNumber);
                        najirNepalimodel.setFile(File);
                        najirNepalimodel.setSubjectCaseType(SubjectCaseType);
                        najirNepalimodel.setPageNumber(PageNumber);
                        najirNepalimodel.setPubyear(Pubyear);
                        najirNepalimodel.setMonth(Month);
                        najirNepalimodels.add(najirNepalimodel);
                    }
                    if (jsonArray.length() > 0) {
                        Intent intent = new Intent(getActivity(), DetailsrowlistActivity.class);
                        intent.putExtra("mylist", najirNepalimodels);
                        intent.putExtra("sabdha",sabdhaanusar);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_LONG).show();
                    }
                }
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
