package com.fagnerng.m3v.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fagner on 18/01/2017.
 */

public class GsonManager {

    private static GsonManager gc;
    private Gson gson;

    private GsonManager(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        this.gson = gsonBuilder.create();
    }

    public static GsonManager getInstance(){
        if(gc == null){
            gc = new GsonManager();
        }
        return gc;
    }

    public Object convertJsonStringToObject(String json, Class type){
        return new Gson().fromJson(json, type);
    }

    public List<Object> convertJsonArrayToArrayObject(String response, Type type){
        Object[] objects = gson.fromJson(response, type);
        return new LinkedList<>(Arrays.asList(objects));
    }

    public List<Object> convertJsonArrayToArrayObject(JSONArray response, Class type){
        return convertJsonArrayToArrayObject(response.toString(), type);
    }

    public Object convertJsonToObject(JSONObject response, Class type){
        return gson.fromJson(response.toString(), type);
    }
}
