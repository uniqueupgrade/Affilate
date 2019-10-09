package com.example.mom.afflilate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.mom.afflilate.R;
import com.example.mom.afflilate.activities.HomeActivity;
import com.example.mom.afflilate.activities.LoginActivity;
import com.example.mom.afflilate.activities.SearchActivity;
import com.example.mom.afflilate.activities.SettingActivity;
import com.example.mom.afflilate.activities.ShortListActivity;

public class BoomButtonUtils {

    public static void changeSelectedPositionOfBottomItem(int position, AHBottomNavigation bottomAppBar) {
        bottomAppBar.setCurrentItem(position);
    }

    public static void addBottomMenuItemsInAppBar(final Context mContext, final AHBottomNavigation bottomAppBar, final int selectedPosition) {
        AHBottomNavigationItem item1 =
                new AHBottomNavigationItem(mContext.getString(R.string.home),
                        R.drawable.ic_home);
        AHBottomNavigationItem item2 =
                new AHBottomNavigationItem(mContext.getString(R.string.search),
                        R.drawable.ic_search);
        AHBottomNavigationItem item3 =
                new AHBottomNavigationItem(mContext.getString(R.string.short_list),
                        R.drawable.ic_short_list);
        AHBottomNavigationItem item4 =
                new AHBottomNavigationItem(mContext.getString(R.string.setting),
                        R.drawable.ic_setting);
        bottomAppBar.addItem(item1);
        bottomAppBar.addItem(item2);
        bottomAppBar.addItem(item3);
        bottomAppBar.addItem(item4);

        bottomAppBar.setCurrentItem(selectedPosition);
        bottomAppBar.setTitleTextSize(10 * mContext.getResources().getDisplayMetrics().scaledDensity, 10 * mContext.getResources().getDisplayMetrics().scaledDensity);
        bottomAppBar.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomAppBar.setInactiveColor(fetchColor(R.color.color_gray, mContext));
        bottomAppBar.setAccentColor(fetchColor(R.color.colorPrimaryButton, mContext));
        bottomAppBar.setBehaviorTranslationEnabled(true);

        bottomAppBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        if (selectedPosition != 0) {
                            mContext.startActivity(new Intent(mContext, HomeActivity.class));
                            ((Activity) mContext).overridePendingTransition(0, 0);
                            ((Activity) mContext).finish();
                        }
                        break;
                    case 1:
                        if (selectedPosition != 1) {
                            if (!SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
                                mContext.startActivity(new Intent(mContext, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                ((Activity) mContext).overridePendingTransition(0, 0);((Activity) mContext).overridePendingTransition(0, 0);
                                if (!mContext.getClass().getSimpleName().equalsIgnoreCase(HomeActivity.class.getSimpleName())) {
                                    ((Activity) mContext).finish();
                                }
                            } else {
                                mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            }
                        }
                        break;
                    case 2:
                        if (selectedPosition != 2) {
                            if (!SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
                                mContext.startActivity(new Intent(mContext, ShortListActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                ((Activity) mContext).overridePendingTransition(0, 0);
                                if (!mContext.getClass().getSimpleName().equalsIgnoreCase(HomeActivity.class.getSimpleName())) {
                                    ((Activity) mContext).finish();
                                }
                            } else {
                                mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            }
                        }
                        break;
                    case 3:
                        if (selectedPosition != 3) {
                            if (!SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
                                mContext.startActivity(new Intent(mContext, SettingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                ((Activity) mContext).overridePendingTransition(0, 0);
                                if (!mContext.getClass().getSimpleName().equalsIgnoreCase(HomeActivity.class.getSimpleName())) {
                                    ((Activity) mContext).finish();
                                }
                            } else {
                                mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            }
                        }
                        break;
                }

                return true;
            }
        });
    }

    public static int fetchColor(@ColorRes int color, Context mContext) {
        return ContextCompat.getColor(mContext, color);
    }
}
