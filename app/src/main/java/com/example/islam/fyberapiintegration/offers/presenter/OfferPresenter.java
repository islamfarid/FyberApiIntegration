package com.example.islam.fyberapiintegration.offers.presenter;


import com.example.islam.fyberapiintegration.application.FyberApplication;
import com.example.islam.fyberapiintegration.model.OffersResponse;
import com.example.islam.fyberapiintegration.model.Param;
import com.example.islam.fyberapiintegration.offers.bussiness.OffersBussiness;
import com.example.islam.fyberapiintegration.offers.view.OffersView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by islam on 8/27/16.
 */
public class OfferPresenter {
    WeakReference<OffersView> offersView;
    @Inject
    OffersBussiness offersBussiness;

    public void setView(OffersView offersView) {
        FyberApplication.getInstance().getApplicationComponent().inject(this);
        this.offersView = new WeakReference(offersView);
    }

    public void loadOffers(ArrayList<Param> params) {
        if (isViewAttached()) {
            offersView.get().showProgress();
        }
        offersBussiness.getOffersParamString(params).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                //do nothing
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    offersView.get().hideProgress();
                    offersView.get().showErrorMessage(e.getMessage());
                }
            }

            @Override
            public void onNext(String s) {
                offersBussiness.getOffers(s).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                        .mainThread()).subscribe(new Subscriber<OffersResponse>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            offersView.get().hideProgress();
                            offersView.get().showOffersLoadedMessage();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            offersView.get().hideProgress();
                            offersView.get().showErrorMessage(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(OffersResponse offersResponse) {
                        if (isViewAttached()) {
                            offersView.get().hideProgress();
                            offersView.get().setOffers(offersResponse.getOffers());
                        }
                    }
                });
            }
        });

    }

    private boolean isViewAttached() {
        return offersView != null && offersView.get() != null;
    }


    public void setOffersBussiness(OffersBussiness offersBussiness) {
        this.offersBussiness = offersBussiness;
    }
}
