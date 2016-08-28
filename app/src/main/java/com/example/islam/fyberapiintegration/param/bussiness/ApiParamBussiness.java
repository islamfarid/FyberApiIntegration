package com.example.islam.fyberapiintegration.param.bussiness;

import com.example.islam.fyberapiintegration.model.Param;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by islam on 8/27/16.
 */

/**
 * this class seemed to be has no need as we can put the only method of it
 * in the ApiParamPreseneter for example but it is needed just in case another bussiness is added
 * to apiparamfragment
 */
public class ApiParamBussiness {
    @Inject
    public ApiParamBussiness() {

    }

    /**
     * @param apiKey
     * @param uid
     * @param pub0
     * @param apiId
     * @return
     */
    public rx.Observable<List<Param>> getParamObservable(String apiKey, String uid, String pub0, String apiId) {
        return rx.Observable.create(subscriber -> {
            ArrayList<Param> params = new ArrayList<Param>();
            Param apiKeyParam = new Param();
            apiKeyParam.setParamValue(apiKey);
            Param uidParam = new Param();
            uidParam.setParamKey("uid");
            uidParam.setParamValue(uid);
            Param pub0Param = null;
            if (pub0 != null && !pub0.equals("")) {
                pub0Param = new Param();
                pub0Param.setParamKey("pub0");
                pub0Param.setParamValue(pub0);
            }
            Param apiIdParam = new Param();
            apiIdParam.setParamKey("appid");
            apiIdParam.setParamValue(apiId);
            params.add(apiKeyParam);
            params.add(apiIdParam);
            params.add(uidParam);
            if (pub0 != null && !pub0.equals("")) {
                params.add(pub0Param);
            }
            subscriber.onNext(params);
            subscriber.onCompleted();
        });
    }
}
