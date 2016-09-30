package com.mobapp.bublefish.network.responce;

import com.google.gson.annotations.SerializedName;

public class GoogleTranslateTranslatedText {
    @SerializedName("translatedText")
    String translatedText;

    public String getTranslatedText() {
        return translatedText;
    }
}
