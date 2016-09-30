package com.mobapp.bublefish.network;

import com.mobapp.bublefish.network.responce.GoogleTranslateResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface IGoogleTranslateWithToken {

    @GET("language/translate/v2")
    Call<GoogleTranslateResponce> translate(@Query("key") String token, @Query("q") String text, @Query("target") String target);
}
