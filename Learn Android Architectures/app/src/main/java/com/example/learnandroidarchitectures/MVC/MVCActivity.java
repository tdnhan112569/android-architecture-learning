package com.example.learnandroidarchitectures.MVC;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnandroidarchitectures.R;
import com.example.learnandroidarchitectures.model.Country;

import java.util.ArrayList;
import java.util.List;

public class MVCActivity extends AppCompatActivity {

 // View
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listCountry;
    private CountriesController countriesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        addControls();
        addEvents();
        setTitle("MVC Pattern");
    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVCActivity.this, "You click on " + listCountry.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        countriesController.fetchCountries();
    }


    public void updateUI(List<String> countries) {
        listCountry.clear();
        listCountry.addAll(countries);
        adapter.notifyDataSetChanged();
    }


    private void addControls() {
        listView =  findViewById(R.id.listItemCountryMVC);
        listCountry = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.row_item_country,R.id.txtItem,listCountry);
        listView.setAdapter(adapter);
        countriesController = new CountriesController(this);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVCActivity.class);
    }
}
