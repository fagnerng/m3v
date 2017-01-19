package com.fagnerng.m3v.singleton;

/**
 * Created by fagner on 18/01/2017.
 */
public class TreeManager {
    private static TreeManager mInstance = null;

    public static TreeManager getInstance() {
        if (mInstance == null) {
            mInstance = new TreeManager();
        }

        return mInstance;
    }

    private TreeManager() {
    }
}
