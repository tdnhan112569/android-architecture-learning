package com.example.learnandroidarchitectures.service;

import com.example.learnandroidarchitectures.api.CountryAPI;
import com.example.learnandroidarchitectures.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    private CountryAPI api;

    public CountriesService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(CountryAPI.class);
    }

    public Single<List<Country>> getCountries() {
        return api.getCountries();
    }
}
