package com.example.learnandroidarchitectures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.learnandroidarchitectures.MVC.MVCActivity;
import com.example.learnandroidarchitectures.MVP.MVPActivity;
import com.example.learnandroidarchitectures.MVVM.MVVMActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onStartActivity(View view) {
        switch (view.getTag().toString()){
            case "1": startActivity(MVPActivity.getIntent(this)); break;
            case "2": startActivity(MVVMActivity.getIntent(this)); break;
            default: startActivity(MVCActivity.getIntent(this)); break;
        }
    }
}
