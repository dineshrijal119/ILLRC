package com.technocurl.www.parsejson.english;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.technocurl.www.parsejson.SpinnerAdapter;
import com.technocurl.www.parsejson.R;

import java.util.ArrayList;

/**
 * Created by dinesh on 8/2/16.
 */
public class NajirEnglishdetails extends Fragment implements View.OnClickListener {
    LinearLayout button;
    Toolbar toolbar;
    String selectedItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page_english_details,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button=(LinearLayout)view.findViewById(R.id.search);
        button.setOnClickListener(this);

        Spinner spinner_ntc = (Spinner) view.findViewById(R.id.publication);
        ArrayList<String> spinnerData_ntc = new ArrayList<>();
        spinnerData_ntc.add("Nekap");
        spinnerData_ntc.add("Buletin");
        spinnerData_ntc.add("Others");
        SpinnerAdapter adapter_ntc = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spinnerData_ntc);
        spinner_ntc.setAdapter(adapter_ntc);
        spinner_ntc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem="Rs 100.00";

            }
        });
        Spinner spinner_category = (Spinner) view.findViewById(R.id.publication);
        ArrayList<String> spinnerspinner_category = new ArrayList<>();
        spinnerspinner_category.add("Nekap");
        spinnerspinner_category.add("Buletin");
        spinnerspinner_category.add("Others");
        SpinnerAdapter adapter_spinner_category = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spinnerData_ntc);
        spinner_category.setAdapter(adapter_spinner_category);
        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem="Rs 100.00";

            }
        });
        Spinner sabdhaanusar = (Spinner) view.findViewById(R.id.sabdaanusar);
        ArrayList<String> spinnersabdhaanusar = new ArrayList<>();
        spinnerData_ntc.add("najir");
        spinnerData_ntc.add("purnapath");
        SpinnerAdapter adapter_sabdaanusar = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spinnerData_ntc);
        sabdhaanusar.setAdapter(adapter_sabdaanusar);
        sabdhaanusar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem="najir";

            }
        });

        Spinner adalat = (Spinner) view.findViewById(R.id.court);
        ArrayList<String> spineer_adalat = new ArrayList<>();
        spineer_adalat.add("Supreme Court");
        spineer_adalat.add("Appellate Court");
        spineer_adalat.add("District Court");


        SpinnerAdapter adapter_adalat = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_adalat);
        adalat.setAdapter(adapter_adalat);
        adalat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem="Supreme Court";

            }
        });


        Spinner mahina = (Spinner) view.findViewById(R.id.month);
        ArrayList<String> spineer_mahina = new ArrayList<>();
        spineer_mahina.add("Jan.");
        spineer_mahina.add("Feb");
        spineer_mahina.add("Mar.");
        spineer_mahina.add("Apr.");
        spineer_mahina.add("May");
        spineer_mahina.add("Jun");
        spineer_mahina.add("Jul.");
        spineer_mahina.add("Aug.");
        spineer_mahina.add("Set.");
        spineer_mahina.add("Oct.");
        spineer_mahina.add("Nov.");
        spineer_mahina.add("Dec.");
        SpinnerAdapter adapter_mahina = new SpinnerAdapter(getActivity(), R.layout.top_off_spinner_layout, spineer_mahina);
        mahina.setAdapter(adapter_mahina);
        mahina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem="";

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search:

                break;
        }
    }
   /* @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    NajirNepaliBistrti najirEnglish = new NajirNepaliBistrti();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.flContent,najirEnglish)
                            .commit();
                    getActivity().setTitle("नजिर (Nepali)");
                    return true;
                }
                return false;
            }
        });
    }*/
}
