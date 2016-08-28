package com.example.islam.fyberapiintegration.offers.view;


import com.example.islam.fyberapiintegration.model.Offer;

import java.util.List;

/**
 * Created by islam on 8/27/16.
 */
public interface OffersView {
    void showProgress();
    void showOffersLoadedMessage();
    void showErrorMessage(String message);
    void setOffers(List<Offer> forecasts);
    void hideProgress();
}
