package com.example.learnandroidarchitectures.MVVM;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnandroidarchitectures.MVP.CountriesPresenter;
import com.example.learnandroidarchitectures.MVP.MVPActivity;
import com.example.learnandroidarchitectures.R;

import java.util.ArrayList;
import java.util.List;

public class MVVMActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listCountry;
    private CountriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("MVVM Pattern");
        addControls();
        addEvents();
        viewModel.fetchCountries();
    }


    private void addEvents() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVVMActivity.this, "You click on " + listCountry.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getCountries().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> countries) {
                if(countries!=null) {
                    listCountry.clear();
                    listCountry.addAll(countries);
                    adapter.notifyDataSetChanged();
                }else {
                    viewModel.fetchCountries();
                }
            }
        });


    }

    private void addControls() {
        listView =  findViewById(R.id.listItemCountryMVVM);
        listCountry = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.row_item_country,R.id.txtItem,listCountry);
        listView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVVMActivity.class);
    }
}
