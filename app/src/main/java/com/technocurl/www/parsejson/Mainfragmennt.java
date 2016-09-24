package com.technocurl.www.parsejson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.technocurl.www.parsejson.english.NajirEnglish;
import com.technocurl.www.parsejson.gazat.Gazat;
import com.technocurl.www.parsejson.kanun.KanunnepaliAactivity;
import com.technocurl.www.parsejson.nepali.NepaliMain;

/**
 * Created by dinesh on 8/24/16.
 */
public class Mainfragmennt extends Fragment implements View.OnClickListener {
    LinearLayout najir_nepali,najir_english,kanun_nepali,kanun_english,gazat,about_us;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        najir_nepali=(LinearLayout)view.findViewById(R.id.najir_nepali);
        najir_english=(LinearLayout)view.findViewById(R.id.najir_english);
        kanun_nepali=(LinearLayout)view.findViewById(R.id.kanun_nepali);
        kanun_english=(LinearLayout)view.findViewById(R.id.kanun_english);
        gazat=(LinearLayout)view.findViewById(R.id.gazat);
        about_us=(LinearLayout)view.findViewById(R.id.about_us);
        najir_nepali.setOnClickListener(this);
        najir_english.setOnClickListener(this);
        kanun_nepali.setOnClickListener(this);
        kanun_english.setOnClickListener(this);
        gazat.setOnClickListener(this);
        about_us.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.najir_nepali:
                try{
                   /* getFragmentManager().beginTransaction()
                            .replace(R.id.flContent,new NepaliMain())
                            .commit();
                    getActivity().setTitle("नजिर (Nepali)");*/
                    startActivity(new Intent(getActivity(),NepaliMain.class));
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.najir_english:
                try {
                   startActivity(new Intent(getActivity(),NajirEnglish.class));
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.kanun_english:
                startActivity(new Intent(getActivity(), KanunnepaliAactivity.class));
                break;
            case R.id.kanun_nepali:
                startActivity(new Intent(getActivity(), KanunnepaliAactivity.class));
                break;
            case R.id.gazat:
                try {
                   startActivity(new Intent(getActivity(),Gazat.class));

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.about_us:
                try {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.flContent,new Aboutusfragment())
                            .commit();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;

        }
    }
}
