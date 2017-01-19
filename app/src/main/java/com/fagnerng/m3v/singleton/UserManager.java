package com.fagnerng.m3v.singleton;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fagnerng.m3v.constants.C;

import org.json.JSONObject;

/**
 * Created by fagner on 18/01/2017.
 */
public class UserManager {
    private static final String TAG = "UserManager";
    private static UserManager mInstance = null;
    private SharedPreferences mPrefs;
    public static UserManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserManager(context);
        }
        return mInstance;
    }

    public JSONObject getUser() {
        JSONObject user = null;
        if (mPrefs != null && mPrefs.contains(C.Preferences.USER_KEY)) {
            String userAsString = mPrefs.getString(C.Preferences.USER_KEY, null);
            try {
               if (userAsString != null) {
                   user = new JSONObject(userAsString);
               }
           } catch (Exception e) {
                user = null;
               Log.e(TAG, e.getMessage());
           }
        }

        return user;
    }

    public boolean setUser(JSONObject user) {
        if (mPrefs != null && user !=null) {
            return mPrefs.edit().putString(C.Preferences.USER_KEY, user.toString()).commit();
        }
        return  false;
    }

    private UserManager(Context context) {
        mPrefs = context.getSharedPreferences(C.Preferences.PREFERENCES_NAME_USER, Context.MODE_PRIVATE);
    }
}
