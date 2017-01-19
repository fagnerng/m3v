package com.fagnerng.m3v.constants;

/**
 * Created by fagner on 18/01/2017.
 */

public abstract class C {
    public abstract class Preferences {
        public static final String USER_KEY = "user";
        public static final String USER_TOKEN = "token";
        public static final String PREFERENCES_NAME_USER = USER_KEY;

    }
    public abstract class Url {
        public static final String SERVER = "https://m3v.herokuapp.com";
        public static final String LOGIN = SERVER + "/auth";
    }

}
