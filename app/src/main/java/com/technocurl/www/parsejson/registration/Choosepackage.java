package com.technocurl.www.parsejson.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.technocurl.www.parsejson.model.NajirNepalimodel;
import com.technocurl.www.parsejson.nepali.DetailsrowlistActivity;
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.ListUtils;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

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
    ListView listView,list_kanun,list_gazet;
    Button confirm_package;
    String phone;
    ArrayList<Najirchoosmodel> najirchoosmodels = new ArrayList<>();
    ArrayList<Kanunichoosemode> kanunichoosemodes = new ArrayList<>();
    ArrayList<Gazetchoosemodel>  gazetchoosemodels = new ArrayList<>();

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
        confirm_package=(Button)view.findViewById(R.id.confirm_package);
        confirm_package.setOnClickListener(this);
        phone= getArguments().getString("phone");
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
                    new InsertUserInfo().execute();



                }catch (Exception e){
                    e.printStackTrace();
                }

                getActivity().finish();
                break;
        }
    }
public  class  InsertUserInfo  extends AsyncTask<String,String,String>{
    Progressillrc progressDialog = new Progressillrc(getActivity());

    @Override
    protected String doInBackground(String... strings) {
        HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
        try {
            JSONObject mainobject = new JSONObject();
            mainobject.put("PhonNo","");
            mainobject.put("SecurityCode","");
            JSONArray najir = new JSONArray();
            JSONArray kanun = new JSONArray();
            JSONArray gazet = new JSONArray();
            mainobject.put("Lawgrp",kanun);
            mainobject.put("Mantralaya",gazet);
            mainobject.put("Publication",najir);
            Log.d(Tags.TAG,"dinesh : " + mainobject);

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        illrcDatabases.insertInfo(phone,"1","");
        startActivity(new Intent(getActivity(), MainPage.class));
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
                        Log.d(Tags.TAG, "law main group : " + Lawmaingrp);
                        Log.d(Tags.TAG, "Mantralaya : " + Mantralaya);
                        Log.d(Tags.TAG, "Publication : " + Publication);
                        for (int j = 0; j < Lawmaingrp.length(); j++) {
                            Najirchoosmodel najirchoosmodel = new Najirchoosmodel();
                            JSONObject Lawmaingrpobject = Lawmaingrp.getJSONObject(j);
                            String id = Lawmaingrpobject.getString("id");
                            String Name = Lawmaingrpobject.getString("Name");
                            najirchoosmodel.setId(id);
                            najirchoosmodel.setName(Name);
                            najirchoosmodels.add(najirchoosmodel);
//                            illrcDatabases.insertKanun(Name);
                            Log.d(Tags.TAG, "law main group sakha : " + id + Name);
                        }
                        for (int k = 0; k < Mantralaya.length(); k++) {
                            Kanunichoosemode kanunichoosemode = new Kanunichoosemode();
                            JSONObject Mantralayaobject = Mantralaya.getJSONObject(k);
                            String ID = Mantralayaobject.getString("ID");
                            String mantralayaname = Mantralayaobject.getString("mantralayaname");
                            kanunichoosemode.setId(ID);
                            kanunichoosemode.setName(mantralayaname);
                            kanunichoosemodes.add(kanunichoosemode);

//                            illrcDatabases.insertgazet(mantralayaname);
                            Log.d(Tags.TAG, "mantralaya main group sakha : " + ID + mantralayaname);
                        }
                        for (int l = 0; l < Publication.length(); l++) {
                            Gazetchoosemodel gazetchoosemodel = new Gazetchoosemodel();
                            JSONObject Publicationobject = Publication.getJSONObject(l);
                            String PublicationId = Publicationobject.getString("PublicationId");
                            String PublicationName = Publicationobject.getString("PublicationName");
                            gazetchoosemodel.setName(PublicationName);
                            gazetchoosemodel.setId(PublicationId);
                            gazetchoosemodels.add(gazetchoosemodel);
//                            illrcDatabases.insertNajir(PublicationName);
                            Log.d(Tags.TAG, "Publication main group sakha : " + PublicationId + PublicationName);
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
    public void insertNajirpublication(String Name){
       illrcDatabases.insertNajir(Name);
    }
    public void insertKanun(String Name){
        illrcDatabases.insertKanun(Name);
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
