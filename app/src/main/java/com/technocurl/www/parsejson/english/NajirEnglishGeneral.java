package com.technocurl.www.parsejson.english;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.ServiceHandler;
import com.technocurl.www.parsejson.SpinnerAdapter;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.nepali.DetailsrowlistActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by deadlydragger on 7/9/16.
 */
public class NajirEnglishGeneral extends Fragment implements View.OnClickListener {
    Toolbar toolbar;
    String selectedItem;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;
    //    private AdapterFish mAdapter;
    public static final String BASE_URL = "105/api/entryeng/searchasync/?";
    String publication = "", pageno = "", adalat = "", month = "", ijlash = "", darta_no = "", parche_bipache = "", nirnayeno = "", nirnayemiti = "", pubyear = "", page = "", subject = "", sabdha = "", pache_bipache = "", kanunbebasahi = "";

    ArrayList<NajirNepalimodel> najirNepalimodels = new ArrayList<>();
    EditText page_get, subject_get, pache_bipache_get, kanunbebasahi_get, sabdha_get;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page_english, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.search);
        button.setOnClickListener(this);
        page_get = (EditText) view.findViewById(R.id.page);
        subject_get = (EditText) view.findViewById(R.id.subject);
        pache_bipache_get = (EditText) view.findViewById(R.id.pache_bipache);
        kanunbebasahi_get = (EditText) view.findViewById(R.id.kanunbebasahi);
        sabdha_get = (EditText) view.findViewById(R.id.sabdha);
        page = page_get.getText().toString();
        subject = subject_get.getText().toString();
        sabdha = sabdha_get.getText().toString();
        pache_bipache = pache_bipache_get.getText().toString();
        kanunbebasahi = kanunbebasahi_get.getText().toString();


        Spinner spinner_ntc = (Spinner) view.findViewById(R.id.publication);
        ArrayList<String> spinnerData_ntc = new ArrayList<>();
        spinnerData_ntc.add("Nekapa");
        spinnerData_ntc.add("Buletin");
        spinnerData_ntc.add("Others");
        SpinnerAdapter adapter_ntc = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spinnerData_ntc);
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
        spineer_adalat.add("Supreme");
        spineer_adalat.add("Appellate");
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
                adalat = "Supreme";

            }
        });


        Spinner mahina = (Spinner) view.findViewById(R.id.mahina);
        ArrayList<String> spineer_mahina = new ArrayList<>();
        spineer_mahina.add("baishak");
        spineer_mahina.add("jestha");
        spineer_mahina.add("ashar");
        spineer_mahina.add("shawan");
        spineer_mahina.add("bhadra");
        spineer_mahina.add("aswin");
        spineer_mahina.add("kartik");
        spineer_mahina.add("mansir");
        spineer_mahina.add("push");
        spineer_mahina.add("magh");
        spineer_mahina.add("falgun");
        spineer_mahina.add("chaitra");
        SpinnerAdapter adapter_mahina = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_mahina);
        mahina.setAdapter(adapter_mahina);
        mahina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem = "baishak";

            }
        });

        Spinner ijlash_main = (Spinner) view.findViewById(R.id.ijlash);
        ArrayList<String> spineer_ijlash = new ArrayList<>();
        spineer_ijlash.add("Samyukta ijlash");
        spineer_ijlash.add("Purna ijlash");
        spineer_ijlash.add("Bisesh ijlash");
        spineer_ijlash.add("Yekal ijlash");

        SpinnerAdapter adapter_ijlash = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_ijlash);
        ijlash_main.setAdapter(adapter_ijlash);
        ijlash_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ijlash = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ijlash = "Samyukta Ijlash";
            }
        });
        Spinner pub_year = (Spinner) view.findViewById(R.id.pub_year);
        ArrayList<String> spineer_pub_year = new ArrayList<>();
        spineer_pub_year.add("2049");
        spineer_pub_year.add("2050");
        spineer_pub_year.add("2051");
        spineer_pub_year.add("2052");
        spineer_pub_year.add("2053");
        spineer_pub_year.add("2054");
        spineer_pub_year.add("2055");
        spineer_pub_year.add("2056");
        spineer_pub_year.add("2057");
        spineer_pub_year.add("2058");
        spineer_pub_year.add("2059");
        spineer_pub_year.add("2060");
        spineer_pub_year.add("2061");
        spineer_pub_year.add("2062");
        spineer_pub_year.add("2063");
        spineer_pub_year.add("2064");
        spineer_pub_year.add("2065");
        spineer_pub_year.add("2066");
        spineer_pub_year.add("2067");
        spineer_pub_year.add("2068");
        spineer_pub_year.add("2069");
        spineer_pub_year.add("2070");
        spineer_pub_year.add("2071");
        spineer_pub_year.add("2072");
        spineer_pub_year.add("2073");
        spineer_pub_year.add("2074");
        SpinnerAdapter adapter_pub_year = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_pub_year);
        pub_year.setAdapter(adapter_pub_year);
        pub_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pubyear = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pubyear = "2067";

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
                new Callnepalinajirsearct().execute();
                break;
        }
    }

    public class Callnepalinajirsearct extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection conn = null;
            String url = null;
            String jsonStr = "";
            try {
                String publication_enc = URLEncoder.encode(publication, "utf-8");
                String pageno_enc = URLEncoder.encode(page, "utf-8");
                String nirnayeno_enc = URLEncoder.encode(nirnayeno, "utf-8");
                String adalat_enc = URLEncoder.encode(adalat, "utf-8");
                String darta_no_enc = URLEncoder.encode(darta_no, "utf-8");
                String ijlash_enc = URLEncoder.encode(ijlash, "utf-8");
                String month_enc = URLEncoder.encode(month, "utf-8");
                String subject_post = URLEncoder.encode(subject, "utf-8");
                String pubyear_enc = URLEncoder.encode(pubyear, "utf-8");
                String page_post = URLEncoder.encode(page, "utf-8");
                String sabdha_post = URLEncoder.encode(sabdha, "utf-8");
                String pache_bipache_post = URLEncoder.encode(pache_bipache, "utf-8");
                String kanunbebasahi_post = URLEncoder.encode(kanunbebasahi, "utf-8");


//                url = BASE_URL + "searchPublication=" + publication_enc + "&searchPagenumber=" + pageno_enc+ "&searchNirnayemhmber=" + nirnayeno_enc + "&searchAdalat=" + adalat_enc + "&searchRegisterid=" + darta_no_enc + "&searchDartamiti=" + "&searchSubjectcasetype=" +subject_post+ "&searchIjlash=" + ijlash_enc + "&searchCasetype=" + "&searchCasetypeII=" + "&searchCasetypeIII=" + "&searchCasetypeIV=" + "&searchCasetypeV=" + "&searchMiti=" + "&searchPurnawedan=" + "&searchPartyasi=" + "&searchPurnawedantarfa=" + "&searchPartyasitarfa=" + "&searchShortsubject=" + "&searchDecisionshort=" + "&searchAttractivesubject=" + "&searchFile=" + "&searchBhag=" + "&searchAnka=" + "&searchMonth=" + month_enc + "&searchIjlashyak=" + "&searchFirstdec=" + "&searchAppl=" + "&searchPrilciple=" + "&searchApprinc=" + "&searchPubyear=" + pubyear_enc;
                url = "http://legalinfonepal.com:105/api/entryeng/searchasync/?searchPublication=%20&searchPagenumber=%20&searchNirnayemhmber=%20&searchAdalat=%20&searchRegisterid=%20&searchDartamiti=%20&searchSubjectcasetype=%20&searchIjlash=%20&searchCasetype=%20&searchCasetypeII=%20&searchCasetypeIII=%20&searchCasetypeIV=%20&searchCasetypeV=%20&searchMiti=%20&searchPurnawedan=%20&searchPartyasi=%20&searchPurnawedantarfa=%20&searchPartyasitarfa=%20&searchShortsubject=%20&searchDecisionshort=%20&searchAttractivesubject=%20&searchFile=%20&searchBhag=%20&searchAnka=%20&searchMonth=%20&searchIjlashyak=%20&searchFirstdec=%20&searchAppl=%20&searchPrilciple=%20&searchApprinc=%20&searchPubyear=%20&";
                ServiceHandler sh = new ServiceHandler();
                Log.d("dinesh", url);
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

            try {
                progressDialog.dismiss();
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
                    String Pubyear = jsonObject.getString("Pubyear");
                    String Month = jsonObject.getString("Month");
                    String SubjectCaseType = jsonObject.getString("SubjectCaseType");
                    String NirnayeNumber = jsonObject.getString("NirnayeNumber");
                    String CaseType = jsonObject.getString("CaseType");
                    String File = jsonObject.getString("File");
                    najirNepalimodel.setSN(SN);
                    najirNepalimodel.setPublication(Publication);
                    najirNepalimodel.setAdalat(Adalat);
                    najirNepalimodel.setRegisterId(RegisterId);
                    najirNepalimodel.setMiti(Miti);
                    najirNepalimodel.setJudge(Judge);
                    najirNepalimodel.setPageNumber(PageNumber);
                    najirNepalimodel.setPubyear(Pubyear);
                    najirNepalimodel.setMonth(Month);
                    najirNepalimodel.setSubjectCaseType(SubjectCaseType);
                    najirNepalimodel.setPurnawedan(Purnawedan);
                    najirNepalimodel.setPartyashi(Partyashi);
                    najirNepalimodel.setPurnawedanTarf(PurnawedanTarf);
                    najirNepalimodel.setPartyashiTarf(partyashiTarf);
                    najirNepalimodel.setAttravtiveSubject(AttravtiveSubject);
                    najirNepalimodel.setCaseType(CaseType);
                    najirNepalimodel.setNirnayeNumber(NirnayeNumber);
                    najirNepalimodel.setFile(File);
                    najirNepalimodels.add(najirNepalimodel);
                }
                if (jsonArray.length() > 0) {
                    Intent intent = new Intent(getActivity(), DetailsrowActivityenglish.class);
                    intent.putExtra("mylist", najirNepalimodels);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "No data found", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
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
