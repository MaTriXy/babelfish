package com.mobapp.bublefish.network;

import android.widget.TextView;

import java.lang.ref.WeakReference;

public class Request {
    WeakReference<TextView> textView;
    int originalTag = -1;
    String lang;
    String originalText;
    String translatedText;

    ITranslate translate;

    public Request(TextView textView, String lang, String originalText, ITranslate translate){
        this.textView = new WeakReference<TextView>(textView);
        if(textView.getTag() != null){
            this.originalTag = textView.getTag().hashCode();
        }else{
            int number = Math.round(10000);
            textView.setTag(number);
            this.originalTag = textView.getTag().hashCode();
        }

        this.lang = lang;
        this.originalText = originalText;
        this.translate = translate;
    }

    public void execute(){
        String text = translate.translate(lang, originalText);
        Request.this.translatedText = text;
    }

    public void publishText(){
        TextView tv = textView.get();
        if(tv != null && tv.getTag().hashCode() == originalTag){
            tv.setText(translatedText);
        }
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
