
package com.example.islam.fyberapiintegration.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferType {

    @SerializedName("offer_type_id")
    @Expose
    private Integer offerTypeId;
    @SerializedName("readable")
    @Expose
    private String readable;

    /**
     * 
     * @return
     *     The offerTypeId
     */
    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    /**
     * 
     * @param offerTypeId
     *     The offer_type_id
     */
    public void setOfferTypeId(Integer offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    /**
     * 
     * @return
     *     The readable
     */
    public String getReadable() {
        return readable;
    }

    /**
     * 
     * @param readable
     *     The readable
     */
    public void setReadable(String readable) {
        this.readable = readable;
    }

}
