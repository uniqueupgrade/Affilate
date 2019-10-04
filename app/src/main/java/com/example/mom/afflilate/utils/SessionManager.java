package com.example.mom.afflilate.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SessionManager {
    // Shared preferences file name
    private static final String PREF_NAME = "affilate_prefs";
    static SharedPreferences mpref;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this._context = context;
        mpref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    private static void initPrefs(Context context, String prefsName, int mode) {
        mpref = context.getSharedPreferences(prefsName, mode);
    }

    public static SharedPreferences getPreferences() {
        if (mpref != null) {
            return mpref;
        }
        throw new RuntimeException(
                "Prefs class not correctly instantiated. Please call Builder.setContext().build() in the Application class onCreate.");
    }

    public static void clearSession(Context mContext) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.clear();
        editor.apply();
        setBoolean(Constants.KEY_IS_LOGGED_IN, false);
    }

    //String data set and get shared preference
    public static String getString(String key) {
        SharedPreferences pref = getPreferences();
        try {
            String strVal = pref.getString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt(""));
            return TwoWayChangeUtils.decrypt(strVal);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        try {
            editor.putString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    //Boolean data set and get shared preference
    public static boolean getBoolean(String key) {
        SharedPreferences pref = getPreferences();
        try {
            String boolValue = pref.getString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt("false"));
            return (Boolean.parseBoolean(TwoWayChangeUtils.decrypt(boolValue)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        try {
            editor.putString(TwoWayChangeUtils.encrypt(key), (TwoWayChangeUtils.encrypt(Boolean.toString(value))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    //Long data set and get shared preference
    public static long getLong(String key) {
        SharedPreferences pref = getPreferences();
        try {
            String longVal = pref.getString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt("0"));
            return Long.parseLong(TwoWayChangeUtils.decrypt(longVal));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setLong(String key, long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        try {
            editor.putString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt(Long.toString(value)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    //int data set and get shared preference
    public static int getInt(String key) {
        SharedPreferences pref = getPreferences();
        try {
            String intVal = pref.getString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt("0"));
            return Integer.parseInt(TwoWayChangeUtils.decrypt(intVal));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setInt(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        try {
            editor.putString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt(Integer.toString(value)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    //Flaot data set and get shared preference
    public static float getFloat(String key) {
        SharedPreferences pref = getPreferences();
        try {
            String floatVal = pref.getString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt("0"));
            return Float.parseFloat(TwoWayChangeUtils.decrypt(floatVal));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setFloat(String key, float value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        try {
            editor.putString(TwoWayChangeUtils.encrypt(key), TwoWayChangeUtils.encrypt(Float.toString(value)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public final static class Builder {

        private String mKey;
        private Context mContext;
        private int mMode = -1;
        private boolean mUseDefault = false;

        public Builder setPrefsName(final String prefsName) {
            mKey = prefsName;
            return this;
        }

        public Builder setContext(final Context context) {
            mContext = context;
            return this;
        }

        public Builder setMode(final int mode) {
            if (mode == ContextWrapper.MODE_PRIVATE || mode == ContextWrapper.MODE_WORLD_READABLE || mode == ContextWrapper.MODE_WORLD_WRITEABLE || mode == ContextWrapper.MODE_MULTI_PROCESS) {
                mMode = mode;
            } else {
                throw new RuntimeException("The mode in the SharedPreference can only be set too ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE or ContextWrapper.MODE_MULTI_PROCESS");
            }
            return this;
        }

        @SuppressWarnings("SameParameterValue")
        public Builder setUseDefaultSharedPreference(boolean defaultSharedPreference) {
            mUseDefault = defaultSharedPreference;
            return this;
        }

        public void build() {
            if (mContext == null) {
                throw new RuntimeException("Context not set, please set context before building the Prefs instance.");
            }
            if (TextUtils.isEmpty(mKey)) {
                mKey = mContext.getPackageName();
            }
            if (mUseDefault) {
                mKey += PREF_NAME;
            }
            if (mMode == -1) {
                mMode = ContextWrapper.MODE_PRIVATE;
            }
            SessionManager.initPrefs(mContext, mKey, mMode);
        }
    }
}
