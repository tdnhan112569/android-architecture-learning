package com.example.learnandroidarchitectures.MVVM;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.learnandroidarchitectures.model.Country;
import com.example.learnandroidarchitectures.service.CountriesService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesViewModel extends ViewModel {

    private CountriesService service;

    private MutableLiveData<List<String>> lstCountries;

    public CountriesViewModel() {
        service = new CountriesService();
        lstCountries = new MutableLiveData<>();
    }

    public void fetchCountries() {
        service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {

                    @Override
                    public void onSuccess(List<Country> countries) {
                        List<String> countriesName = new ArrayList<>();
                        for(Country country: countries) {
                            countriesName.add(country.countryName);
                        }
                        lstCountries.setValue(countriesName);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<String>> getCountries() {
        return this.lstCountries;
    }
}
