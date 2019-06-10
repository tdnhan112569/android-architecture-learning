package com.example.learnandroidarchitectures.MVP;

import com.example.learnandroidarchitectures.model.Country;
import com.example.learnandroidarchitectures.service.CountriesService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesPresenter {

    private View view;
    private CountriesService service;

    public CountriesPresenter(View view) {
        this.view = view;
        service = new CountriesService();
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
                        view.onGetCountriesCompleted(countriesName);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorFetching();
                    }
                });
    }

    public interface View {
       void onGetCountriesCompleted(List<String> countries);
       void onErrorFetching();
    }

}
