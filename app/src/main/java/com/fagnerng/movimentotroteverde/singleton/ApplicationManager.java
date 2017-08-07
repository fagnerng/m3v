package com.fagnerng.movimentotroteverde.singleton;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by fagnerng on 07/08/2017.
 */

public class ApplicationManager extends Application {
    private static ApplicationManager ourInstance = new ApplicationManager();

    public static ApplicationManager getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        FirebaseApp.initializeApp(this);
    }
}
