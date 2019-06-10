package com.example.learnandroidarchitectures.MVP;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnandroidarchitectures.MVC.CountriesController;
import com.example.learnandroidarchitectures.MVC.MVCActivity;
import com.example.learnandroidarchitectures.R;

import java.util.ArrayList;
import java.util.List;

public class MVPActivity extends AppCompatActivity implements CountriesPresenter.View{

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listCountry;
    private CountriesPresenter countriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        addControls();
        addEvents();
        setTitle("MVP Pattern");
    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVPActivity.this, "You click on " + listCountry.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        countriesPresenter.fetchCountries();
    }

    private void addControls() {
        listView =  findViewById(R.id.listItemCountryMVP);
        listCountry = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.row_item_country,R.id.txtItem,listCountry);
        listView.setAdapter(adapter);
        countriesPresenter = new CountriesPresenter(this);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVPActivity.class);
    }

    @Override
    public void onGetCountriesCompleted(List<String> countries) {
        listCountry.clear();
        listCountry.addAll(countries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorFetching() {

    }
}
