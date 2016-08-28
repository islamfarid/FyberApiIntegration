package com.example.islam.fyberapiintegration.offers.presenter;

import android.support.test.InstrumentationRegistry;

import com.example.islam.fyberapiintegration.offers.bussiness.OffersBussiness;
import com.example.islam.fyberapiintegration.offers.view.OffersView;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by islam on 8/28/16.
 */
public class OfferPresenterTest {
    OffersBussiness offersBussiness;
    OffersView offersView;
    OfferPresenter offerPresenter;

    @Before
    public void init() {
        System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getInstrumentation().getTargetContext().getCacheDir().getPath());
        offerPresenter = new OfferPresenter();
        offersBussiness = mock(OffersBussiness.class);
        offersView = mock(OffersView.class);
        offerPresenter.setView(offersView);
        offerPresenter.setOffersBussiness(offersBussiness);
    }

    @Test
    public void testWhenloadOffersCalled_getOffersParamStringInBussinessISCalled()  {
        doNothing().when(offersView).showProgress();
        when(offersBussiness.getOffersParamString(null)).thenReturn(Observable.create((Observable.OnSubscribe<String>) sub -> {
            //do nothing
        }));
        offerPresenter.loadOffers(null);
        verify(offersBussiness, times(1)).getOffersParamString(null);
    }
    @Test
    public void testWhenloadOffersCalled_offerViewShowProgressISCalled() {
        when(offersBussiness.getOffersParamString(null)).thenReturn(Observable.create((Observable.OnSubscribe<String>) sub -> {
            //do nothing we just test if method is called
        }));
        offerPresenter.loadOffers(null);
        verify(offersView, times(1)).showProgress();
    }
    @Test
    public void testWhenloadOffersError_offerViewHideProgressISCalled()  {
        doNothing().when(offersView).showProgress();
        when(offersBussiness.getOffersParamString(null)).thenReturn(Observable.create((Observable.OnSubscribe<String>) sub -> {
            sub.onError(new Throwable());
            verify(offersView, times(1)).hideProgress();
        }));
        offerPresenter.loadOffers(null);
    }
    @Test
    public void testWhenloadoffersCompleted_offerViewHideProgressISCalled()  {
        doNothing().when(offersView).showProgress();
        when(offersBussiness.getOffersParamString(null)).thenReturn(Observable.create((Observable.OnSubscribe<String>) sub -> {
            sub.onCompleted();
            verify(offersView, times(1)).hideProgress();
        }));
        offerPresenter.loadOffers(null);
    }
    @Test
    public void testWhenloadOffersCompleted_offerBussinessGetOffersIsCalled()  {
        doNothing().when(offersView).showProgress();
        when(offersBussiness.getOffersParamString(null)).thenReturn(Observable.create((Observable.OnSubscribe<String>) sub -> {
            sub.onCompleted();
            verify(offersBussiness, times(1)).getOffers(null);
        }));
        offerPresenter.loadOffers(null);
    }
}