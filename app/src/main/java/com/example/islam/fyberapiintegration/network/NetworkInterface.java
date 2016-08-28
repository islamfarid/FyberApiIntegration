package com.example.islam.fyberapiintegration.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by islam on 8/27/16.
 */
public interface NetworkInterface {
    @GET("")
    Call<ResponseBody> getOffersData(@Url String absoluteURL);
}
