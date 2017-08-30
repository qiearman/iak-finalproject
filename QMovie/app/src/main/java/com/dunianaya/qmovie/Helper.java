package com.dunianaya.qmovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Qi on 8/30/2017.
 */

public class Helper {

    public static JSONArray getListFromRoot(JSONObject root, String key) {
        JSONArray lists = null;
        try {
            lists = root.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public static String getStringFromRoot(JSONObject root, String key) {
        String value="";
        try {
            value = root.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int getIntFromRoot(JSONObject root, String key) {
        int value = 0;
        try {
            value = root.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static JSONArray getListFromRoot(String body, String key) {
        JSONArray lists = null;
        try {
            lists = createRoot(body).getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public static String getStringFromRoot(String body, String key) {
        String value="";
        try {
            value = createRoot(body).getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int getIntFromRoot(String body, String key) {
        int value=0;
        try {
            value = createRoot(body).getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static JSONObject createRoot(String body) {
        JSONObject root = null;
        try {
            root = new JSONObject(body);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
