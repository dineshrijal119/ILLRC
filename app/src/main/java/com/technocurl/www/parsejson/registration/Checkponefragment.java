package com.technocurl.www.parsejson.registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.technocurl.www.parsejson.HttpUrlConnectionJson;
import com.technocurl.www.parsejson.MainPage;
import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.custumclasses.Progressillrc;
import com.technocurl.www.parsejson.databases.IllrcDatabases;
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.Util;

/**
 * Created by dinesh on 9/17/16.
 */
public class Checkponefragment extends Fragment implements View.OnClickListener {
    Button phone_submi;
    String unique_id;
    IllrcDatabases illrcDatabases;
    EditText phone,confirm_phone;
    Progressillrc progressDialog;
    ImageView logo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_cell_phone,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phone_submi=(Button)view.findViewById(R.id.phone_submit);
        phone=(EditText)view.findViewById(R.id.cell_text);
        confirm_phone=(EditText)view.findViewById(R.id.cell_text_confirm);
        phone_submi.setOnClickListener(this);
        logo=(ImageView)view.findViewById(R.id.logo);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .fontSize(30) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound("ILIRC", Color.parseColor("#FF013E3D"));
        logo.setImageDrawable(drawable);
       unique_id = Settings.Secure.getString(getContext().getContentResolver(),
               Settings.Secure.ANDROID_ID);
        Log.d(Constants.TAG,"unique id : " + unique_id);
        phone.requestFocus();
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()==10){
                    confirm_phone.requestFocus();
                }

            }
        });
        confirm_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()==10){
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromInputMethod(confirm_phone.getWindowToken(),0);
                }
            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illrcDatabases= new IllrcDatabases(getActivity());
        illrcDatabases.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_submit:
                try {
                    String no = phone.getText().toString();
                    String no_confir= confirm_phone.getText().toString();
                    if (no.equals(no_confir)){
                        new Checkcellphone(no,unique_id).execute();
                    }else {
                        phone.setError("Enter same number");
                        confirm_phone.setError("Enter same number");
                    }

                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    public class Checkcellphone extends AsyncTask<String,String,String>{
        String phone,unique_id;
        public Checkcellphone (String phone,String unique_id){
            this.phone=phone;
            this.unique_id=unique_id;
        }
        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            String check_phone="";
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Tags.UNIQUE_ID,unique_id);
                jsonObject.put(Tags.PHONE,phone);
                Log.d(Tags.TAG,"post json check cell : " + jsonObject);
                check_phone=httpUrlConnectionJson.sendHTTPData(Constants.CHECK_PHONE,jsonObject);
                Log.d(Tags.TAG,"response check cell : " + check_phone);

            }catch (JSONException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return check_phone;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new Progressillrc(getActivity());
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
                JSONArray jsonObject1 = jsonObject.getJSONArray("data");
                JSONObject jsonObject2 = jsonObject1.getJSONObject(0);
                if (success==true){
                    boolean IsRegisteredUser = jsonObject2.getBoolean("IsRegisteredUser");
                    if (IsRegisteredUser==false){

                        Registrationfragment registrationfragment = new Registrationfragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment,registrationfragment);
                        Bundle bundle = new Bundle();
                        bundle.putString("phone",phone);
                        registrationfragment.setArguments(bundle);
                        fragmentTransaction.commit();

                    }else if (IsRegisteredUser==true){
                        illrcDatabases.insertInfo(phone,"1","");
                        startActivity(new Intent(getActivity(), MainPage.class));
                        getActivity().finish();
                    }


                }


            }catch (Exception e){
                e.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"Failed to register",Toast.LENGTH_LONG).show();
            }
        }
    }
}
