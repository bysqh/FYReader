package xyz.fycz.myreader.util;

import android.content.Context;
import android.content.SharedPreferences;

import xyz.fycz.myreader.application.App;

import java.util.Map;

/**
 * SharedPreferences工具类
 */

public class SharedPreAdUtils {
    public static final String SHARED_NAME = "FYReader_ad_pref";
    private static SharedPreAdUtils sInstance;
    private SharedPreferences sharedReadable;
    private SharedPreferences.Editor sharedWritable;

    private SharedPreAdUtils() {
        sharedReadable = App.getmContext()
                .getSharedPreferences(SHARED_NAME, Context.MODE_MULTI_PROCESS);
        sharedWritable = sharedReadable.edit();
    }

    public static SharedPreAdUtils getInstance() {
        if (sInstance == null) {
            synchronized (SharedPreAdUtils.class) {
                if (sInstance == null) {
                    sInstance = new SharedPreAdUtils();
                }
            }
        }
        return sInstance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedReadable;
    }

    public void putString(String key, String value) {
        sharedWritable.putString(key, value);
        sharedWritable.apply();
    }

    public void putInt(String key, int value) {
        sharedWritable.putInt(key, value);
        sharedWritable.apply();
    }

    public void putBoolean(String key, boolean value) {
        sharedWritable.putBoolean(key, value);
        sharedWritable.apply();
    }

    public void putFloat(String key, float value) {
        sharedWritable.putFloat(key, value);
        sharedWritable.apply();
    }

    public void putLong(String key, long value) {
        sharedWritable.putLong(key, value);
        sharedWritable.apply();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String def) {
        return sharedReadable.getString(key, def);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return sharedReadable.getInt(key, def);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedReadable.getBoolean(key, def);
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    public float getFloat(String key, float def) {
        return sharedReadable.getFloat(key, def);
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long def) {
        return sharedReadable.getLong(key, def);
    }

    public void remove(String key) {
        sharedWritable.remove(key).apply();
    }

    public Map<String, ?> getAll() {
        return sharedReadable.getAll();
    }

}

