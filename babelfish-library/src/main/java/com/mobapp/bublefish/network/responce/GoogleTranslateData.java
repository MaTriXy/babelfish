package com.mobapp.bublefish.network.responce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleTranslateData {
    @SerializedName("translations")
    List<GoogleTranslateTranslatedText> googleTranslateTranslatedText;

    public List<GoogleTranslateTranslatedText> getGoogleTranslateTranslatedText() {
        return googleTranslateTranslatedText;
    }
}
