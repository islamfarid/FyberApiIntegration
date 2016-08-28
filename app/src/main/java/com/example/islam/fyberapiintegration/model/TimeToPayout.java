
package com.example.islam.fyberapiintegration.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeToPayout {

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("readable")
    @Expose
    private String readable;

    /**
     * 
     * @return
     *     The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
