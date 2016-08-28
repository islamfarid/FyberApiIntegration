package com.example.islam.fyberapiintegration.param.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.islam.fyberapiintegration.R;
import com.example.islam.fyberapiintegration.application.FyberApplication;
import com.example.islam.fyberapiintegration.common.Constants;
import com.example.islam.fyberapiintegration.model.Param;
import com.example.islam.fyberapiintegration.offers.view.OffersFragment;
import com.example.islam.fyberapiintegration.param.presenter.ApiParamPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by islam on 8/27/16.
 */
public class APiParamFragment extends Fragment implements ApiParamView {
    @Bind(R.id.api_id_editext)
    EditText mApiIDEditField;
    @Bind(R.id.api_key_edittext)
    EditText mApiKeyEditField;
    @Bind(R.id.pub0_editext)
    EditText mPob0EditField;
    @Bind(R.id.uid_edittext)
    EditText mUIDEditField;
    @Bind(R.id.get_offers_button)
    Button mGetOffers;
    @Inject
    ApiParamPresenter apiParamPresenter;

    public APiParamFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api_params, container, false);
        ButterKnife.bind(this, view);
        paramFormValidationNotEmty();
        FyberApplication.getInstance().getApplicationComponent().inject(this);
        apiParamPresenter.setView(this);
        return view;
    }

    /**
     * validate apikey ,uid and appId not null
     * pub0 is optional
     */
    private void paramFormValidationNotEmty() {

        Observable<Boolean> apiIDObservable = RxTextView.textChanges(mApiIDEditField)
                .map(inputText -> (!(inputText.length() == 0)));

        Observable<Boolean> apiKeyObservable = RxTextView.textChanges(mApiKeyEditField)
                .map(inputText -> (!(inputText.length() == 0)));

        Observable<Boolean> uidObservable = RxTextView.textChanges(mUIDEditField)
                .map(inputText -> (!(inputText.length() == 0)));
        Observable.combineLatest(apiIDObservable, uidObservable,
                apiKeyObservable, (aBoolean, aBoolean2, aBoolean3
                                   ) -> (aBoolean && aBoolean2 && aBoolean3))
                .subscribe(valid -> mGetOffers.setEnabled(valid));

    }

    @Override
    public void goToOffers(ArrayList<Param> params) {
        OffersFragment offersFragment =  new OffersFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.OFFERS_PARAMS,params);
        offersFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.fragment_container_framlayout,
                offersFragment    , OffersFragment.class.getName()).addToBackStack(Constants.MAIN_ACTIVITY_STACK).commit();
    }

    @OnClick(R.id.get_offers_button)
    public void getOffersClick() {
        hideKeyboard();
        apiParamPresenter.prepareOfferData(mApiKeyEditField.getText().toString(), mUIDEditField.
                getText().toString(), mPob0EditField.getText().toString(), mApiIDEditField.getText()
                .toString());
    }
    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
