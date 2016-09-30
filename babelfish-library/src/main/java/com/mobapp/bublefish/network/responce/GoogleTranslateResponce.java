package com.mobapp.bublefish.network.responce;

import com.google.gson.annotations.SerializedName;

public class GoogleTranslateResponce {
    @SerializedName("data")
    GoogleTranslateData googleTranslateData;

    public GoogleTranslateData getGoogleTranslateData() {
        return googleTranslateData;
    }
}
