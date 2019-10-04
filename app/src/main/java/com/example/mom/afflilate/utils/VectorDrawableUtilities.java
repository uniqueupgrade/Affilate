package com.example.mom.afflilate.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

import com.example.mom.afflilate.R;

public class VectorDrawableUtilities {
    public static Drawable setVectorForPreLollipop(int resourceId, Context activity) {

        Drawable icon = null;
        try {
            ContextThemeWrapper wrapper = new ContextThemeWrapper(activity, R.style.ThemeOne);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                icon = VectorDrawableCompat.create(activity.getResources(), resourceId, wrapper.getTheme());
            } else {
                icon = activity.getResources().getDrawable(resourceId, wrapper.getTheme());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return icon;
    }

    public static void setVectorForPreLollipop(TextView textView, int resourceId, Context activity, int position) {
        try {
            Drawable icon;
            ContextThemeWrapper wrapper = new ContextThemeWrapper(activity, R.style.ThemeOne);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                icon = VectorDrawableCompat.create(activity.getResources(), resourceId,
                        wrapper.getTheme());
            } else {
                icon = activity.getResources().getDrawable(resourceId, wrapper.getTheme());
            }

            switch (position) {
                case Constants.DRAWABLE_LEFT:
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null);
                    break;

                case Constants.DRAWABLE_RIGHT:
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
                    break;

                case Constants.DRAWABLE_TOP:
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, icon, null, null);
                    break;

                case Constants.DRAWABLE_BOTTOM:
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, icon);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVectorForTwoPreLollipop(TextView textView, int resourceId, int resourceId2, Context activity, int position) {
        try {
            Drawable icon1, icon2;
            ContextThemeWrapper wrapper1 = new ContextThemeWrapper(activity, R.style.ThemeOne);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                icon1 = VectorDrawableCompat.create(activity.getResources(), resourceId,
                        wrapper1.getTheme());
            } else {
                icon1 = activity.getResources().getDrawable(resourceId, wrapper1.getTheme());
            }

            ContextThemeWrapper wrapper2 = new ContextThemeWrapper(activity, R.style.ThemeOne);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                icon2 = VectorDrawableCompat.create(activity.getResources(), resourceId2,
                        wrapper2 .getTheme());
            } else {
                icon2 = activity.getResources().getDrawable(resourceId2, wrapper2.getTheme());
            }

            switch (position) {
                case Constants.DRAWABLE_LR_BOTH:
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(icon1, null, icon2, null);
                    break;

                case Constants.DRAWABLE_TB_BOTH:
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, icon1, null, icon2);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
