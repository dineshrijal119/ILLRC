package com.technocurl.www.parsejson.registration;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.technocurl.www.parsejson.utility.Constants;
import com.technocurl.www.parsejson.utility.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dinesh on 9/17/16.
 */
public class Registrationfragment extends Fragment implements View.OnClickListener {
    Button btn_signup;
    EditText firstname, lastname, email, address;
    CheckBox checkBox1;
    String phone;
    Progressillrc progressDialog;
    ImageView logo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_information, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_signup = (Button) view.findViewById(R.id.btn_signup);
        firstname = (EditText) view.findViewById(R.id.first_name);
        lastname = (EditText) view.findViewById(R.id.last_name);
        email = (EditText) view.findViewById(R.id.email);
        address = (EditText) view.findViewById(R.id.address);
        checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
        checkBox1.setChecked(false);
        btn_signup.setOnClickListener(this);
        phone = getArguments().getString("phone");
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                try {
                    if (checkBox1.isChecked()){
                        new Registeruser(phone, firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), address.getText().toString()).execute();

                    }else {
                        Toast.makeText(getActivity(),"Agree terms and conditon",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public class Registeruser extends AsyncTask<String, String, String> {
        String phone, firstname, lastname, email, address;

        public Registeruser(String phone, String firstname, String lastname, String email, String address) {
            this.phone = phone;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.address = address;
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            String check_phone = "";
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Tags.PHONE, phone);
                jsonObject.put(Tags.FIRST_NAME, firstname);
                jsonObject.put(Tags.LAST_NAME, lastname);
                jsonObject.put(Tags.EMAIL, email);
                jsonObject.put(Tags.ADDRESS, address);
                Log.d(Tags.TAG, "post json check cell : " + jsonObject);
                check_phone = httpUrlConnectionJson.sendHTTPData(Constants.REGISTRR_USER, jsonObject);
                Log.d(Tags.TAG, "response check cell : " + check_phone);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return check_phone;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new Progressillrc(getActivity());
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
                       try {
                          /* Choosepackage choosepackage = new Choosepackage();
                           FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                           fragmentTransaction.replace(R.id.fragment,choosepackage);
                           Bundle bundle = new Bundle();
                           bundle.putString("phone",phone);
                           setArguments(bundle);
                           fragmentTransaction.commit();*/

                           Choosepackage registrationfragment = new Choosepackage();
                           FragmentManager fragmentManager = getFragmentManager();
                           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                           fragmentTransaction.replace(R.id.fragment,registrationfragment);
                           Bundle bundle = new Bundle();
                           bundle.putString("phone",phone);
                           registrationfragment.setArguments(bundle);
                           fragmentTransaction.commit();
                       }catch (NullPointerException e){
                           e.printStackTrace();
                       }
                }else {
                    Toast.makeText(getActivity(),"Failed to register",Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Failed to register", Toast.LENGTH_LONG).show();
            }
        }
    }

}
