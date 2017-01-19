package com.fagnerng.m3v.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fagnerng.m3v.constants.C;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fagner on 18/01/2017.
 */

public class RestClient {
    private static final String TAG = "RestClient";
    private String mToken = "";
    private RequestQueue mRequestQueue;
    private static RestClient mInstance;
    public static RestClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RestClient(context.getApplicationContext());
        }

        return mInstance;
    }
    public void setToken(String token) {
        mToken = token;
    }
    private  RestClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void doLogin(String user, String password, final Response.Listener<JSONObject> successCallBack,
                        final Response.ErrorListener errorCallback) {
        try {
            JSONObject userObj = new JSONObject();
            userObj.put("username", user );
            userObj.put("password", password );
            post(C.Url.LOGIN, userObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        response = response.getJSONObject("user");
                        setToken(response.optString("token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    successCallBack.onResponse(response);
                }
            }, errorCallback);
        } catch (JSONException e) {
            if (errorCallback != null) {
                errorCallback.onErrorResponse(new VolleyError(e.getMessage()));
            } else  {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void get(String url) {
        get(url, null, null);
    }

    public void get(String url, final Response.Listener<JSONObject> successCallBack) {
        get(url, successCallBack, null);
    }

    public void get(String url, final Response.Listener<JSONObject> successCallBack,
                    final Response.ErrorListener errorCallback) {
        addToQueue(Request.Method.GET, url, new JSONObject(), successCallBack, errorCallback);
    }

    public void post(String url, JSONObject params) {
        post(url, params, null, null);
    }
    public void post(String url, JSONObject params,
                     final Response.Listener<JSONObject> successCallBack) {
        post(url, params, successCallBack, null);
    }

    public void post(String url, JSONObject params,
                     final Response.Listener<JSONObject> successCallBack,
                     final Response.ErrorListener errorCallback) {
        addToQueue(Request.Method.POST, url, params, successCallBack, errorCallback);
    }

    public void put(String url, JSONObject params) {
        addToQueue(Request.Method.PUT, url, params, null, null);
    }

    public void delete(String url, JSONObject params) {
        delete(url, params, null, null);
    }

    public void delete(String url, JSONObject params,
                       final Response.Listener<JSONObject> successCallBack) {
        delete(url, params, successCallBack, null);
    }

    public void delete(String url, JSONObject params,
                       final Response.Listener<JSONObject> successCallBack,
                       final Response.ErrorListener errorCallback) {
        addToQueue(Request.Method.DELETE, url, params, successCallBack, errorCallback);
    }

    private void addToQueue(int method, String url, JSONObject params,
                            final Response.Listener<JSONObject> success,
                            final Response.ErrorListener errorCallback){

        mRequestQueue.add(new JsonObjectRequest(method, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse" + response.toString());
                        if(success != null){
                            success.onResponse(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse" + error.getMessage());
                        if(errorCallback != null){
                            errorCallback.onErrorResponse(error);
                        }
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders()throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                if(mToken != null && !mToken.isEmpty()){
                    String auth = "Bearer " + mToken;
                    headers.put("Authorization", auth);
                }

                return headers;
            }
        });
    }
}
