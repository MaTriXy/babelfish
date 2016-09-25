package com.mobapp.bublefish;

import android.util.Log;

public class BabelFishBuilder {

    private int logLevel = Log.INFO;

    BabelFishBuilder(){

    }

    BabelFish createBabelFish(){
        return new BabelFish();
    }
}
