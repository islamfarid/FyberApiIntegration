package com.example.islam.fyberapiintegration.param.presenter;

import com.example.islam.fyberapiintegration.application.FyberApplication;
import com.example.islam.fyberapiintegration.model.Param;
import com.example.islam.fyberapiintegration.param.bussiness.ApiParamBussiness;
import com.example.islam.fyberapiintegration.param.view.ApiParamView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by islam on 8/27/16.
 */
public class ApiParamPresenter {
    @Inject
    ApiParamBussiness apiParamBussiness;

    WeakReference<ApiParamView> apiParamView;

    public void setView(ApiParamView apiParamView) {
        FyberApplication.getInstance().getApplicationComponent().inject(this);
        this.apiParamView = new WeakReference(apiParamView);
    }


    public void prepareOfferData(String apiKey, String uid, String pub0, String apiId) {
        apiParamBussiness.getParamObservable(apiKey, uid, pub0, apiId).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Param>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Param> params) {
                if (isViewAttached()) {
                    apiParamView.get().goToOffers((ArrayList<Param>) params);
                }
            }
        });
    }

    private boolean isViewAttached() {
        return apiParamView != null && apiParamView.get() != null;
    }


    public void setOffersBussiness(ApiParamBussiness apiParamBussiness) {
        this.apiParamBussiness = apiParamBussiness;
    }
}
