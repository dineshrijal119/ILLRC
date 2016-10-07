package com.technocurl.www.parsejson.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.technocurl.www.parsejson.HttpUrlConnectionJson;
import com.technocurl.www.parsejson.MainPage;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.ServiceHandler;
import com.technocurl.www.parsejson.custumclasses.Progressillrc;
import com.technocurl.www.parsejson.databases.IllrcDatabases;
import com.technocurl.www.parsejson.kanun.Kanunimodel;
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.nepali.DetailsrowlistActivity;
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.ListUtils;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dinesh on 9/17/16.
 */
public class Choosepackage extends Fragment implements View.OnClickListener {
    private IllrcDatabases illrcDatabases;
    private SQLiteDatabase db;
    Cursor najircurson,gazetcursor,kanuncursor;
   NajirAdapter najirAdapter;
    Gazetadapter gazetadapter ;
    Kanunadapter kanunadapter;
    Kanunadapterenglish kanunadapterenglish;
    NajirAdapterEnglish najirAdapterEnglish;
    ListView listView,list_kanun,list_gazet,list_kanun_english,listview_english;
    Button confirm_package;
    String phone;
    ArrayList<Najirchoosmodel> najirchoosmodels = new ArrayList<>();
    ArrayList<Kanunichoosemode> kanunichoosemodes = new ArrayList<>();
    ArrayList<Gazetchoosemodel>  gazetchoosemodels = new ArrayList<>();
    ArrayList<Najirchoosmodel> najirNepalimodelsenglish = new ArrayList<>();
    ArrayList<Kanunichoosemode>  kanunichoosemodesenglish  = new ArrayList<>();

    ArrayList<String> data_post_najir;
    ArrayList<String> data_post_najir_english;
    ArrayList<String> data_post_kanun;
    ArrayList<String> data_post_kanun_english;
    ArrayList<String> data_post_gazet;
    String unique_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illrcDatabases = new IllrcDatabases(getActivity());
        illrcDatabases.getWritableDatabase();
        illrcDatabases.getReadableDatabase();
        db=illrcDatabases.getWritableDatabase();



    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Getallavaibalbepackage().execute();
        listView=(ListView)view.findViewById(R.id.list);
        list_kanun=(ListView)view.findViewById(R.id.list_kanun);
        list_gazet=(ListView)view.findViewById(R.id.list_gazet);
        listview_english=(ListView)view.findViewById(R.id.list_english);
        list_kanun_english=(ListView)view.findViewById(R.id.list_kanun_english);
        confirm_package=(Button)view.findViewById(R.id.confirm_package);
        confirm_package.setOnClickListener(this);
        phone= getArguments().getString("phone");
        unique_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_package, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_package:
                try {

                    data_post_najir = illrcDatabases.getnajirPublication();
                    data_post_kanun=illrcDatabases.getkanunchetragat();
                    data_post_gazet= illrcDatabases.getgazetmantralayagat();
                    data_post_najir_english=illrcDatabases.getnajirPublicationenglish();
                    data_post_kanun_english=illrcDatabases.getkanunbisayagatenglish();
                    if (data_post_najir.size() == 0 && data_post_kanun.size() == 0 && data_post_gazet.size()==0){
                        Toast.makeText(getContext(),"Please choose at lease one package.",Toast.LENGTH_LONG).show();
                    }else {
                        new InsertUserInfo(data_post_najir,data_post_kanun,data_post_gazet,data_post_kanun_english,data_post_najir_english).execute();
                    }




                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
        }
    }
public  class  InsertUserInfo  extends AsyncTask<String,String,String>{
    Progressillrc progressDialog = new Progressillrc(getActivity());
    ArrayList<String> najir_post;
    ArrayList<String> kanun_post;
    ArrayList<String> gazet_post;
    ArrayList<String> kanun_post_english;
    ArrayList<String> najir_post_english;
    String response_data=null;
    public InsertUserInfo(ArrayList<String> najir_post,ArrayList<String> kanun_post,ArrayList<String> gazet_post,ArrayList<String> kanun_post_english,ArrayList<String> najir_post_english){
        this.najir_post=najir_post;
        this.kanun_post=kanun_post;
        this.gazet_post=gazet_post;
        this.kanun_post_english=kanun_post_english;
        this.najir_post_english=najir_post_english;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
        try {
            JSONObject mainobject = new JSONObject();
            mainobject.put("PhonNo",phone);
            mainobject.put("SecurityCode",unique_id);
            JSONArray najir = new JSONArray();

            JSONArray kanun = new JSONArray();
            JSONArray gazet = new JSONArray();
            JSONArray kanun_english = new JSONArray();
            JSONArray najir_english = new JSONArray();
            if (kanun_post_english.size() > 0){
                for (int h = 0; h< kanun_post_english.size();h++){
                    JSONObject kaunn_sub_english = new JSONObject();
                    kaunn_sub_english.put("Name",kanun_post_english.get(h));
                    kanun_english.put(kaunn_sub_english);
                }
            }

if (kanun_post.size()>0){
    for (int i = 0; i < kanun_post.size();i++){
        JSONObject kaunn_sub = new JSONObject();
        kaunn_sub.put("Name",kanun_post.get(i));
        kanun.put(kaunn_sub);
    }
}

if (najir_post.size() > 0){

    for (int j = 0 ; j<najir_post.size();j++){
        JSONObject najir_sub = new JSONObject();
        najir_sub.put("Name",najir_post.get(j));
        najir.put(najir_sub);
    }
}
            if (najir_post_english.size() >0){
                for (int m= 0 ; m<najir_post_english.size();m++){
                    JSONObject najir_sub_english = new JSONObject();
                    najir_sub_english.put("Name",najir_post_english.get(m));
                    najir_english.put(najir_sub_english);
                }
            }

if (gazet_post.size()>0){

    for (int k = 0; k<gazet_post.size();k++){
        JSONObject gazet_sub = new JSONObject();
        gazet_sub.put("Name",gazet_post.get(k));
        gazet.put(gazet_sub);

    }
}
            mainobject.put("Lawgrp",kanun);
            mainobject.put("Mantralaya",gazet);
            mainobject.put("Publication",najir);
            mainobject.put("PublicationEnglish",najir_english);
            mainobject.put("LawgrpEnglish",kanun_english);

            Log.d(Tags.TAG,"dinesh : " + mainobject);
            response_data=httpUrlConnectionJson.sendHTTPData(Constants.INSERT_USER_PACKAGE,mainobject);
            Log.d(Tags.TAG,"dinesh post response : " + response_data);

        }catch (Exception e){
            e.printStackTrace();
        }


        return response_data;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(s);
            boolean success = jsonObject.getBoolean("success");
            if (success == true){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    JSONArray  gaazet = jsonObject1.getJSONArray("Mantralaysub");
                JSONArray kanun = jsonObject1.getJSONArray("LawgroupSub");
                for (int i=0;i<gaazet.length();i++){
                    JSONObject jsonObject2 = gaazet.getJSONObject(i);
                    String GadgetBargikaran = jsonObject2.getString("SubName");
                    illrcDatabases.insertgazetsub(GadgetBargikaran);
                }
                for ( int j=0;j<kanun.length();j++){
                    JSONObject jsonObject2 = kanun.getJSONObject(j);
                    String LawBargiKaranDto  = jsonObject2.getString("SubName");
                    illrcDatabases.insertKanunsub(LawBargiKaranDto);
                }
                illrcDatabases.insertInfo(phone,"1",unique_id);
                startActivity(new Intent(getActivity(), MainPage.class));

            }else {
                Toast.makeText(getContext(),"Failed to complete register",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            progressDialog.dismiss();
            e.printStackTrace();
            Toast.makeText(getContext(),"Failed to complete register",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog= new Progressillrc(getActivity());
        progressDialog.show();



    }
}
    public class Getallavaibalbepackage extends AsyncTask<String, String, String> {
        Progressillrc progressDialog = new Progressillrc(getActivity());

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection conn = null;

            String jsonStr = "";
            try {
                ServiceHandler sh = new ServiceHandler();
                jsonStr = sh.makeServiceCall(Constants.GET_ALL_PACKAGE, ServiceHandler.GET);
                Log.d(Tags.TAG, jsonStr);
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
                JSONObject jsonObject = new JSONObject(s);
                boolean success = jsonObject.getBoolean("success");
                if (success == true) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Log.d(Tags.TAG, "object : " + jsonObject1);
                        JSONArray Lawmaingrp = jsonObject1.getJSONArray("Lawgrp");
                        JSONArray Mantralaya = jsonObject1.getJSONArray("Mantralaya");
                        JSONArray Publication = jsonObject1.getJSONArray("Publication");
                        JSONArray LawgrpEnglish = jsonObject1.getJSONArray("LawgrpEnglish");
                        JSONArray PublicationEnglish = jsonObject1.getJSONArray("PublicationEnglish");
                        Log.d(Tags.TAG, "law main group : " + Lawmaingrp);
                        Log.d(Tags.TAG, "Mantralaya : " + Mantralaya);
                        Log.d(Tags.TAG, "Publication : " + Publication);
                        for (int j = 0; j < Lawmaingrp.length(); j++) {
                            Kanunichoosemode kanunichoosemode = new Kanunichoosemode();
                            JSONObject Lawmaingrpobject = Lawmaingrp.getJSONObject(j);
                            String Name = Lawmaingrpobject.getString("Name");
                            kanunichoosemode.setName(Name);
                            kanunichoosemodes.add(kanunichoosemode);
                        }
                        for (int k = 0; k < Mantralaya.length(); k++) {
                            Gazetchoosemodel gazetchoosemodel = new Gazetchoosemodel();
                            JSONObject Mantralayaobject = Mantralaya.getJSONObject(k);
//                            String ID = Mantralayaobject.getString("ID");
                            String mantralayaname = Mantralayaobject.getString("Name");
                            gazetchoosemodel.setName(mantralayaname);
                            gazetchoosemodels.add(gazetchoosemodel);


//                            illrcDatabases.insertgazet(mantralayaname);
                        }
                        for (int l = 0; l < Publication.length(); l++) {
                            Najirchoosmodel najirchoosmodel = new Najirchoosmodel();
                            JSONObject Publicationobject = Publication.getJSONObject(l);
                            String PublicationName = Publicationobject.getString("Name");
                            najirchoosmodel.setName(PublicationName);
                            najirchoosmodels.add(najirchoosmodel);
                        }
                        for (int i1=0;i1<PublicationEnglish.length();i1++){
                            Najirchoosmodel najirchoosmodel = new Najirchoosmodel();
                            JSONObject Publicationobject = PublicationEnglish.getJSONObject(i1);
                            String PublicationName = Publicationobject.getString("Name");
                            najirchoosmodel.setName(PublicationName);
                            najirNepalimodelsenglish.add(najirchoosmodel);
                        }
                        for (int i1=0;i1<LawgrpEnglish.length();i1++){
                            Kanunichoosemode najirchoosmodel = new Kanunichoosemode();
                            JSONObject Publicationobject = LawgrpEnglish.getJSONObject(i1);
                            String PublicationName = Publicationobject.getString("Name");
                            najirchoosmodel.setName(PublicationName);
                            kanunichoosemodesenglish.add(najirchoosmodel);
                        }
                    }

                    if (najirchoosmodels.size() > 0){
                        najirAdapter = new NajirAdapter(getContext(), najirchoosmodels,Choosepackage.this);
                        listView.setAdapter(najirAdapter);
                        ListUtils.setDynamicHeight(listView);
                    }

                    if (kanunichoosemodes.size() > 0){
                        kanunadapter = new Kanunadapter(getContext(), kanunichoosemodes,Choosepackage.this);
                        list_kanun.setAdapter(kanunadapter);
                        ListUtils.setDynamicHeight(list_kanun);
                    }
                    if (gazetchoosemodels.size() > 0){
                        gazetadapter = new Gazetadapter(getContext(), gazetchoosemodels,Choosepackage.this);
                        list_gazet.setAdapter(gazetadapter);
                        ListUtils.setDynamicHeight(list_gazet);
                    }
                    if (najirNepalimodelsenglish.size() > 0){
                        najirAdapterEnglish = new NajirAdapterEnglish(getContext(), najirNepalimodelsenglish,Choosepackage.this);
                        listview_english.setAdapter(najirAdapterEnglish);
                        ListUtils.setDynamicHeight(listview_english);
                    }
                    if (kanunichoosemodesenglish.size() > 0){
                        kanunadapterenglish = new Kanunadapterenglish(getContext(), kanunichoosemodesenglish,Choosepackage.this);
                        list_kanun_english.setAdapter(kanunadapterenglish);
                        ListUtils.setDynamicHeight(list_kanun_english);
                    }

                } else {
                    Toast.makeText(getActivity(), "Failed to get data.", Toast.LENGTH_LONG).show();
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
public void deletNajirItem(String id){
    illrcDatabases.deletNajirItem(id);
}
    public void deletNajirItemenglish(String id){
        illrcDatabases.deletNajirItemenglish(id);
    }


    public void deleKanunIteam(String id){
        illrcDatabases.deletKanunItem(id);
    }

    public void deleKanunIteamenglish(String id){
        illrcDatabases.deletKanunItemenglish(id);
    }
    public void deletdGazet(String id){
        illrcDatabases.deletGazetItem(id);
    }
    public void insertNajirpublication(String Name){
       illrcDatabases.insertNajir(Name);
    }
    public void insertNajirpublicationenglish(String Name){
        illrcDatabases.insertNajirenglish(Name);
    }
    public void insertKanun(String Name){
        illrcDatabases.insertKanun(Name);
    }
    public void insertKanunenglish(String Name){
        illrcDatabases.insertKanunenglish(Name);
    }
    public void insertGazet(String Name){
        illrcDatabases.insertgazet(Name);
    }

    public class  PostfinalJson extends  AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONArray array = new JSONArray();

                JSONObject obj1 = new JSONObject();
                obj1.put("id", "01");
                obj1.put("name", "John");
                obj1.put("number", "010");

                JSONObject obj2 = new JSONObject();
                obj2.put("id", "02");
                obj2.put("name", "Mike");
                obj2.put("number", "020");
                JSONArray jsonArray = new JSONArray();
                jsonArray.put("Lawmaingrp");

                jsonArray.put(obj1);
                jsonArray.put(obj2);

/* array = [
              {
                   id:01,
                   name:"John",
                   number:010
               },
               {
                   id:02,
                   name:"Mike",
                   number: 020
               }
           ]
  */
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
