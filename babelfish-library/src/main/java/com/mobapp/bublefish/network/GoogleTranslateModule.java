package com.mobapp.bublefish.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GoogleTranslateModule extends TranslateModule<String>{
    private final String TAG = GoogleTranslateModule.class.getSimpleName();

    private IGoogleTranslate service;

    public GoogleTranslateModule(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.google.com/")
                .client(httpClient.build())
                .build();

        service = retrofit.create(IGoogleTranslate.class);
    }

    @Override
    protected String translateService(String lang, String text) {
        try {
            Response<ResponseBody> response = service.translate(lang, text).execute();

            String transText = response.body().string();
            transText = transText.substring(4, transText.indexOf("\",\""));

            return transText;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}
