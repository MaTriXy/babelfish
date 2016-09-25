package com.mobapp.bublefish.network;

public abstract class TranslateModule<T> implements ITranslate<T>{

    protected abstract String translateService(String lang, String text);

    @Override
    public String translate(final String lang, final String text){
        return translateService(lang, text);
    }
}
