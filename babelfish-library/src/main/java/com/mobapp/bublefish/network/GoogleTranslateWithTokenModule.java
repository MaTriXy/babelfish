package com.mobapp.bublefish.network;

import android.util.Log;

import com.mobapp.bublefish.network.responce.GoogleTranslateData;
import com.mobapp.bublefish.network.responce.GoogleTranslateResponce;
import com.mobapp.bublefish.network.responce.GoogleTranslateTranslatedText;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleTranslateWithTokenModule extends TranslateModule<String>{
    private final String TAG = GoogleTranslateWithTokenModule.class.getSimpleName();

    private final String TOKEN;
    private IGoogleTranslateWithToken service;

    public GoogleTranslateWithTokenModule(String token){
        TOKEN = token;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IGoogleTranslateWithToken.class);
    }

    @Override
    protected String translateService(String lang, String text) {
        try {
            Response<GoogleTranslateResponce> response = service.translate(TOKEN, text, lang).execute();

            GoogleTranslateData googleTranslateData = response.body().getGoogleTranslateData();
            List<GoogleTranslateTranslatedText> googleTranslateTranslatedTexts = googleTranslateData.getGoogleTranslateTranslatedText();
            if(googleTranslateTranslatedTexts != null && !googleTranslateTranslatedTexts.isEmpty()){
                return googleTranslateTranslatedTexts.get(0).getTranslatedText();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}
