package com.technocurl.www.parsejson;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.technocurl.www.parsejson.english.NajirEnglishGeneral;

import mehdi.sakout.aboutpage.AboutPage;

/**
 * Created by dinesh on 8/10/16.
 */
public class Aboutusfragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_us,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView illrc= (ImageView)view.findViewById(R.id.ilrc);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .fontSize(30) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound("ILIRC", Color.parseColor("#FF013E3D"));
        illrc.setImageDrawable(drawable);
      

    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    try {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.flContent, new Mainfragmennt())
                                .commit();
                        getActivity().setTitle("ILIRC");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    return true;
                }
                return false;
            }
        });
    }
}
