package com.example.islam.fyberapiintegration.offers.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.islam.fyberapiintegration.R;
import com.example.islam.fyberapiintegration.application.FyberApplication;
import com.example.islam.fyberapiintegration.common.Constants;
import com.example.islam.fyberapiintegration.model.Offer;
import com.example.islam.fyberapiintegration.offers.presenter.OfferPresenter;
import com.example.islam.fyberapiintegration.offers.view.adapter.OffersAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 8/27/16.
 */
public class OffersFragment extends Fragment implements OffersView {
    @Bind(R.id.offers_recyclerview)
    RecyclerView mOfferssList;
    @Bind(R.id.progressBar)
    ProgressBar loadingBar;
    @Bind(R.id.no_offers_textview)
    TextView mNoOffersFoundTextView;
    @Inject
    OfferPresenter offerPresenter;

    public OffersFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        ButterKnife.bind(this, view);
        mOfferssList.setLayoutManager(new LinearLayoutManager(getActivity()));
        attachPresenter();
        return view;
    }


    private void attachPresenter() {
        FyberApplication.getInstance().getApplicationComponent().inject(this);
        offerPresenter.setView(this);
        offerPresenter.loadOffers(getArguments().getParcelableArrayList(Constants.OFFERS_PARAMS));
    }

    @Override
    public void hideProgress() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOffersLoadedMessage() {
        Snackbar.make(mOfferssList, getResources().getString(R.string.offers_integrated_successfully), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(mOfferssList, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOffers(List<Offer> offers) {
        if(offers != null && offers.size() != 0) {
            OffersAdapter offersAdapter = new OffersAdapter(getActivity(), offers);
            mOfferssList.setAdapter(offersAdapter);
        }else {
            mNoOffersFoundTextView.setVisibility(View.VISIBLE);
            mOfferssList.setVisibility(View.GONE);
        }
    }


}
