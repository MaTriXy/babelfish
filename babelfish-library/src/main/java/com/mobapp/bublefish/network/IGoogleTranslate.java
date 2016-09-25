package com.mobapp.bublefish.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface IGoogleTranslate {

    @GET("translate_a/single?client=gtx&sl=auto&dt=t&ie=UTF-8&oe=UTF-8")
    Call<ResponseBody> translate(@Query("tl") String lang, @Query("q") String text);
}
