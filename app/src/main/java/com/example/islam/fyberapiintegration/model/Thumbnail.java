
package com.example.islam.fyberapiintegration.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("lowres")
    @Expose
    private String lowres;
    @SerializedName("hires")
    @Expose
    private String hires;

    /**
     * 
     * @return
     *     The lowres
     */
    public String getLowres() {
        return lowres;
    }

    /**
     * 
     * @param lowres
     *     The lowres
     */
    public void setLowres(String lowres) {
        this.lowres = lowres;
    }

    /**
     * 
     * @return
     *     The hires
     */
    public String getHires() {
        return hires;
    }

    /**
     * 
     * @param hires
     *     The hires
     */
    public void setHires(String hires) {
        this.hires = hires;
    }

}
