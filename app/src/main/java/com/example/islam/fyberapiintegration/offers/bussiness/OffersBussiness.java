package com.example.islam.fyberapiintegration.offers.bussiness;

import com.example.islam.fyberapiintegration.R;
import com.example.islam.fyberapiintegration.application.FyberApplication;
import com.example.islam.fyberapiintegration.common.Constants;
import com.example.islam.fyberapiintegration.model.OffersResponse;
import com.example.islam.fyberapiintegration.model.Param;
import com.example.islam.fyberapiintegration.network.NetworkInterface;
import com.example.islam.fyberapiintegration.utils.AeSimpleSHA1;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;


/**
 * Created by islam on 8/27/16.
 */
public class OffersBussiness {
    String apiKey = null;
    @Inject
    NetworkInterface networkInterface;

    @Inject
    public OffersBussiness() {
        FyberApplication.getInstance().getApplicationComponent().inject(this);
    }

    public Observable<String> getOffersParamString(ArrayList<Param> params) {
        return Observable.create(subscriber -> {
            if (params != null && params.size() != 0) {
                addApiParams(params);
                apiKey = params.get(0).getParamValue();
                params.remove(0);
                Collections.sort(params);
                StringBuilder paramAsString = new StringBuilder();
                for (Param param : params) {
                    paramAsString.append(param.getParamKey());
                    paramAsString.append("=");
                    paramAsString.append(param.getParamValue());
                    paramAsString.append("&");
                }
                String apiParamWithoutHashKey = paramAsString.toString().substring(0, paramAsString.toString().length() - 1);
                paramAsString.append(apiKey);
                try {
                    String hashedApiKey = AeSimpleSHA1.SHA1(paramAsString.toString()).toLowerCase();

                    subscriber.onNext(apiParamWithoutHashKey + "&" + "hashkey" + "=" + hashedApiKey);
                    subscriber.onCompleted();
                } catch (NoSuchAlgorithmException e) {
                    subscriber.onError(e);
                } catch (UnsupportedEncodingException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    private void addApiParams(ArrayList params) {
        Param formatParam = new Param();
        formatParam.setParamKey("format");
        formatParam.setParamValue("json");
        Param timeStampParam = new Param();
        timeStampParam.setParamKey("timestamp");
        timeStampParam.setParamValue(String.valueOf(System.currentTimeMillis() / 1000L));
        Param osVersionParam = new Param();
        osVersionParam.setParamKey("os_version");
        osVersionParam.setParamValue(android.os.Build.VERSION.RELEASE);
        Param localParam = new Param();
        localParam.setParamKey("locale");
        localParam.setParamValue("DE");
        Param offerTypesParam = new Param();
        offerTypesParam.setParamKey("offer_types");
        offerTypesParam.setParamValue(String.valueOf(112));
        Param ipParam = new Param();
        ipParam.setParamKey("ip");
        ipParam.setParamValue("109.235.143.113");
        Param deviceIDParam = null;
        try {
            deviceIDParam = new Param();
            deviceIDParam.setParamKey("device_id");
            deviceIDParam.setParamValue(AdvertisingIdClient.getAdvertisingIdInfo(FyberApplication.getInstance().getApplicationContext()).getId());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        params.add(formatParam);
        params.add(timeStampParam);
        params.add(osVersionParam);
        params.add(localParam);
        params.add(offerTypesParam);
        params.add(ipParam);
        if (deviceIDParam != null) {
            params.add(deviceIDParam);
        }
    }

    public Observable<OffersResponse> getOffers(String apiKeysString) {
        return Observable.create(subscriber -> {
            Call<ResponseBody> offersCall = networkInterface.getOffersData(Constants.OFFERS_BASE_URL + apiKeysString);
            Response offersResponse = null;
            try {
                offersResponse = offersCall.execute();
                if(offersResponse.isSuccessful()) {
                    String responseBody = ((ResponseBody) offersResponse.body()).string();
                    String responseSignature = offersResponse.headers().get("X-Sponsorpay-Response-Signature");
                    if (responseSignature != null) {
                        try {
                            subscriber.onNext(new Gson().fromJson(responseBody, OffersResponse.class));
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            //no offers available
                            OffersResponse offersResponseObject = new OffersResponse();
                            offersResponseObject.setOffers(new ArrayList<>());
                            subscriber.onNext(offersResponseObject);
                            subscriber.onCompleted();
                        }
                    } else {
                        subscriber.onError(new Throwable(FyberApplication.getInstance().getString(R.string.error_general)));
                    }
                }else {
                    try {
                        String errorResponse = offersResponse.errorBody().string();
                        String key = "message";
                        JSONObject obj = new JSONObject(errorResponse);
                        subscriber.onError(new Throwable(obj.getString(key)));
                    } catch (IOException e1) {
                        subscriber.onError(new Throwable(FyberApplication.getInstance().getString(R.string.error_general)));
                    } catch (JSONException e1) {
                        subscriber.onError(new Throwable(FyberApplication.getInstance().getString(R.string.error_general)));
                    }
                }
            } catch (IOException e) {
                subscriber.onError(new Throwable(FyberApplication.getInstance().getString(R.string.error_general_internet)));
            }

        });

    }

}
