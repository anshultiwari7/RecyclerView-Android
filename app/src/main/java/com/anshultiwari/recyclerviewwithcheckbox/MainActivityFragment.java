package com.anshultiwari.recyclerviewwithcheckbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.anshultiwari.adapter.NumbersAdapter;
import com.anshultiwari.entity.Number;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayList<Number> numbers;
    private RecyclerView list;
    private Button btnGetSelected;
    private Button btnDeleteSelected;
    boolean SelectAlltf = false;
    private Button btnSelectAll;
    NumbersAdapter adapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnGetSelected = (Button) view.findViewById(R.id.btnGetSelected);
        btnDeleteSelected = (Button) view.findViewById(R.id.btnDeleteSelected);
        btnSelectAll = (Button) view.findViewById(R.id.btnSelectAll);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        numbers = new ArrayList<>();
        final String ONEs[] = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
                "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN", "TWENTY"};
        for (int i = 0; i <= 20; i++) {
            Number number = new Number();
            number.setONEs(i + "");
            number.setTextONEs(ONEs[i]);
            this.numbers.add(number);
        }


        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Number number : numbers) {
                    if (number.isSelected()) {
                        if (stringBuilder.length() > 0)
                            stringBuilder.append(", ");
                        stringBuilder.append(number.getONEs());
                    }
                }
                Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }
        });


        btnDeleteSelected.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                StringBuilder stringBuilder = new StringBuilder();
                Log.i("huah","sd");
                Log.i("uhush","+"+numbers.size());
                //for(Number number : numbers){
                //for(Number number:numbers) {
                for (Iterator<Number> iterator = numbers.iterator(); iterator.hasNext();){
                     Number number = iterator.next();
                    if (number.isSelected()) {
                        Log.i("uhuh", "hjij");
                        iterator.remove();
                    }
                    Log.i("ygyg",numbers.size()+" ");
                }
//                adapter.numbers=numbers;
                adapter=new NumbersAdapter(numbers);
                list.setAdapter(adapter);
            }
        });

        btnSelectAll.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if (SelectAlltf == true) {

                    for (Iterator<Number> iterator = numbers.iterator(); iterator.hasNext(); ) {
                        Number number = iterator.next();
                        number.setSelected(false);
                    }
                    SelectAlltf = false;
                    btnSelectAll.setText("SELECT ALL");
                } else {

                    for (Iterator<Number> iterator = numbers.iterator(); iterator.hasNext(); ) {
                        Number number = iterator.next();
                        number.setSelected(true);
                    }
                    SelectAlltf = true;
                    btnSelectAll.setText("DESELECT ALL");
                }

                adapter = new NumbersAdapter(numbers);
                list.setAdapter(adapter);
            }
        });

        adapter = new NumbersAdapter(this.numbers);
        list.setAdapter(adapter);

    }
}
