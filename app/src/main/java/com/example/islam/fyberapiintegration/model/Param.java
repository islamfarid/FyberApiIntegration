package com.example.islam.fyberapiintegration.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by islam on 8/27/16.
 */
public class Param implements Parcelable,Comparable {
    private String paramKey;
    private String ParamValue;

    public String getParamValue() {
        return ParamValue;
    }

    public void setParamValue(String paramValue) {
        ParamValue = paramValue;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public Param() {
    }

    protected Param(Parcel in) {
        paramKey = in.readString();
        ParamValue = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(paramKey);
        dest.writeString(ParamValue);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Param> CREATOR = new Parcelable.Creator<Param>() {
        @Override
        public Param createFromParcel(Parcel in) {
            return new Param(in);
        }

        @Override
        public Param[] newArray(int size) {
            return new Param[size];
        }
    };


    @Override
    public int compareTo(Object another) {
        String anotherString = ((Param)another).getParamKey();
        return paramKey.compareTo(anotherString);
    }
}