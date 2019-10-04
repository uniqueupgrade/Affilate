package com.example.mom.afflilate;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.example.mom.afflilate.utils.SessionManager;


public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        try {
            new SessionManager.Builder()
                    .setContext(this)
                    .setMode(ContextWrapper.MODE_PRIVATE)
                    .setPrefsName(getPackageName())
                    .setUseDefaultSharedPreference(true)
                    .build();

            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
