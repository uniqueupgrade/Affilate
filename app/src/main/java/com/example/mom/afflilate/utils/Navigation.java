package com.example.mom.afflilate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.mom.afflilate.activities.HomeActivity;
import com.example.mom.afflilate.activities.LoginActivity;

public class Navigation {

    public static void navigateToDashboard(Context context) {
        SessionManager.setBoolean(Constants.KEY_IS_LOGGED_OUT, false);
        context.startActivity(new Intent(context, HomeActivity.class));
        ((Activity) context).finish();
    }

    public static void navigateToLogin(Context context) {
        if (SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
            SessionManager.clearSession(context);
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }
}
