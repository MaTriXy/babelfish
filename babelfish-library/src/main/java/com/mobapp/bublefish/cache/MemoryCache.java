package com.mobapp.bublefish.cache;

import android.util.LruCache;

public class MemoryCache {
    int cacheSizeInKB = 1024 * 2; // 2MB
    private LruCache<String, String> memoryCache;

    public MemoryCache(){
        memoryCache = new LruCache<String, String>(cacheSizeInKB) {
            @Override
            protected int sizeOf(String key, String translation) {
                return translation.getBytes().length / 1024;
            }
        };
    }

    public void put(String key, String value){
        memoryCache.put(key, value);
    }

    public String get(String key){
        return memoryCache.get(key);
    }

    public void clear(){
        memoryCache.evictAll();
    }
}
