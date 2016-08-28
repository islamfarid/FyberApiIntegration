package com.example.islam.fyberapiintegration.param.bussiness;

import com.example.islam.fyberapiintegration.model.Param;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertNotNull;

/**
 * Created by islam on 8/28/16.
 */
public class ApiParamBussinessTest {
    ApiParamBussiness apiParamBussiness;
    @Before
    public void init(){
        apiParamBussiness = new ApiParamBussiness();
    }
    @Test
    public void testgetParamObservable_returnNotNullEvents(){
        TestSubscriber<List<Param>> listTestSubscriber = new TestSubscriber<>();
        apiParamBussiness.getParamObservable("","","","").subscribe(listTestSubscriber);
        assertNotNull(listTestSubscriber.getOnErrorEvents());
    }
    @Test
    public void testgetParamObservable_returnNoError(){
        TestSubscriber<List<Param>> listTestSubscriber = new TestSubscriber<>();
        apiParamBussiness.getParamObservable("","","","").subscribe(listTestSubscriber);
        listTestSubscriber.assertNoErrors();
    }

    @Test
    public void testgetParamObservable_isCompleted(){
        TestSubscriber<List<Param>> listTestSubscriber = new TestSubscriber<>();
        apiParamBussiness.getParamObservable("","","","").subscribe(listTestSubscriber);
        listTestSubscriber.assertCompleted();
    }
}