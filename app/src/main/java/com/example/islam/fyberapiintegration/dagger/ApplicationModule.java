package com.example.islam.fyberapiintegration.dagger;

import com.example.islam.fyberapiintegration.application.FyberApplication;
import com.example.islam.fyberapiintegration.network.NetworkInterface;
import com.example.islam.fyberapiintegration.network.ServiceGenerator;
import com.example.islam.fyberapiintegration.offers.bussiness.OffersBussiness;
import com.example.islam.fyberapiintegration.offers.presenter.OfferPresenter;
import com.example.islam.fyberapiintegration.param.bussiness.ApiParamBussiness;
import com.example.islam.fyberapiintegration.param.presenter.ApiParamPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 8/27/16.
 */
@Module
public class ApplicationModule {
    //if any provided component needs the context like Database or sharedpreference
    //we will use this
    FyberApplication fyberApplication;

    public ApplicationModule(FyberApplication fyberApplication) {
        this.fyberApplication = fyberApplication;
    }

    @Provides
    public OfferPresenter provideOfferPresenter() {
        return new OfferPresenter();
    }

    @Provides
    public OffersBussiness provideOfferBussiness() {
        return new OffersBussiness();
    }

    @Provides
    public NetworkInterface provideFyberClient() {
        return ServiceGenerator.createService(NetworkInterface.class);
    }
    @Provides
    public ApiParamBussiness provideApiParamBussiness() {
        return new ApiParamBussiness();
    }
    @Provides
    public ApiParamPresenter provideApiParamPresenter() {
        return new ApiParamPresenter();
    }
}
