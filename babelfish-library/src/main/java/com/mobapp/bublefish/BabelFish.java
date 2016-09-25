package com.mobapp.bublefish;

import android.util.Log;
import android.widget.TextView;

import com.mobapp.bublefish.cache.MemoryCache;
import com.mobapp.bublefish.network.GoogleTranslateModule;
import com.mobapp.bublefish.network.ITranslate;
import com.mobapp.bublefish.network.Request;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class BabelFish {
    private static final String TAG = "BabelFish";
    private static volatile BabelFish babelFish;

    Scheduler mainScheduler;
    Scheduler ioScheduler;

    private final MemoryCache memoryCache;
    private ITranslate translate;
    private String lang = "en";

    BabelFish(){
        memoryCache = new MemoryCache();
        translate = new GoogleTranslateModule();

        mainScheduler = AndroidSchedulers.mainThread();
        ioScheduler = Schedulers.io();
    }

    public static BabelFish get(){
        if(babelFish == null){
            synchronized (BabelFish.class) {
                if (babelFish == null) {
                    BabelFishBuilder builder = new BabelFishBuilder();
                    babelFish = builder.createBabelFish();
                }
            }
        }

        return babelFish;
    }

    public static void tearDown() {
        babelFish = null;
    }

    public BabelFish setLanguage(String language){
        memoryCache.clear();
        lang = language;
        return this;
    }

    public void translate(final TextView textView, final String text){
        String transText = memoryCache.get(text);

        if(transText != null){
            textView.setText(transText);
            Log.d(TAG, "found in cache");
            return;
        }

        Single.create(new Single.OnSubscribe<Request>() {

                    @Override
                    public void call(SingleSubscriber<? super Request> singleSubscriber) {
                        Request request = new Request(textView, lang, text, translate);
                        request.execute();

                        memoryCache.put(text, request.getTranslatedText());

                        singleSubscriber.onSuccess(request);
                    }
                })
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Action1<Request>() {

                    @Override
                    public void call(Request request) {
                        request.publishText();
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }
                });
    }
}
