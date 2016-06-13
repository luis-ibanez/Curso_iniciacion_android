package com.geekshubs.recyclerexample.ds.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by geekshubs on 17/04/16.
 */
public class SettingsSharedPrefs {

    private static final String SETTINGS_FILE_KEY = "settings";

    private SharedPreferences getSettingsSharedPrefs(Context context){

        return context.getSharedPreferences(SETTINGS_FILE_KEY, Context.MODE_PRIVATE);
    }

    public void savePrefs(Context context, String key, String value){
        SharedPreferences sharedPref = getSettingsSharedPrefs(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String loadStringPrefs(Context context, String key){
        SharedPreferences sharedPref = getSettingsSharedPrefs(context);
        return sharedPref.getString(key, "VALOR DEFECTO");
    }
}
