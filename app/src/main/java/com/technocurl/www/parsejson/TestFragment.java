package com.technocurl.www.parsejson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technocurl.www.parsejson.english.NajirEnglishGeneral;

/**
 * Created by dinesh on 8/9/16.
 */
public class TestFragment extends Fragment {
    Toolbar toolbar;
    TestFragment fragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Testing");

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
                   NajirEnglishGeneral najirEnglishGeneral = new NajirEnglishGeneral();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.flContent, najirEnglishGeneral)
                            .commit();
                    getActivity().setTitle("नजिर (English)");
                    return true;
                }
                return false;
            }
        });
    }
}
