package com.example.islam.fyberapiintegration.dagger;


import com.example.islam.fyberapiintegration.offers.bussiness.OffersBussiness;
import com.example.islam.fyberapiintegration.offers.presenter.OfferPresenter;
import com.example.islam.fyberapiintegration.offers.view.OffersFragment;
import com.example.islam.fyberapiintegration.param.presenter.ApiParamPresenter;
import com.example.islam.fyberapiintegration.param.view.APiParamFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by islam on 8/27/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
   void inject(OffersFragment offersFragment);
   void inject(OfferPresenter offerPresenter);
   void inject(OffersBussiness offersBussiness);
   void inject(APiParamFragment aPiParamFragment);
   void inject(ApiParamPresenter apiParamPresenter);

}
