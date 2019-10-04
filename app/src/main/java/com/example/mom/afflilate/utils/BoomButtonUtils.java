package com.example.mom.afflilate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.mom.afflilate.R;
import com.example.mom.afflilate.activities.HomeActivity;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;

public class BoomButtonUtils {

    public static void addHamButtonWithoutBadge(int image, int name, BoomMenuButton hamButton, Context mContext) {
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(image)
                .normalTextRes(name)
                .containsSubText(false)
                .rotateImage(true)
                .shadowEffect(false)
                .pieceColor(android.R.color.transparent)
                .normalColor(mContext.getResources().getColor(R.color.colorPrimaryButton))
                .imageRect(new Rect((int) mContext.getResources().getDimension(R.dimen.dp8), (int) mContext.getResources().getDimension(R.dimen.dp10),
                        (int) mContext.getResources().getDimension(R.dimen.dp40), (int) mContext.getResources().getDimension(R.dimen.dp40)))
                .textRect(new Rect((int) mContext.getResources().getDimension(R.dimen.dp50), (int) mContext.getResources().getDimension(R.dimen.dp8),
                        (int) mContext.getResources().getDimension(R.dimen.dp400), (int) mContext.getResources().getDimension(R.dimen.dp40)))
                .textSize(16)
                .buttonWidth((int) mContext.getResources().getDimension(R.dimen.dp300))
                .buttonHeight((int) mContext.getResources().getDimension(R.dimen.dp50))
                .highlightedColor(android.R.color.transparent);
        hamButton.addBuilder(builder);
    }

    public static void setNotificationCount(final AHBottomNavigation bottomAppBar, String count, int position) {
        AHNotification notification = new AHNotification.Builder()
                .setText(count)
                .setBackgroundColor(Color.RED)
                .setTextColor(Color.WHITE)
                .build();
        // Adding notification to last item.
        bottomAppBar.setNotification(notification, position);
    }

    public static void changeSelectedPositionOfBottomItem(int position, AHBottomNavigation bottomAppBar) {
        bottomAppBar.setCurrentItem(position);
    }

    public static void removeNotificationCount(final AHBottomNavigation bottomAppBar, int position) {
        bottomAppBar.setNotification(new AHNotification(), position);
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
                new AHBottomNavigationItem(mContext.getString(R.string.profile),
                        R.drawable.ic_profile);
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
//                            if (SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
//                                mContext.startActivity(new Intent(mContext, VisitListActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//
//                                if (!mContext.getClass().getSimpleName().equalsIgnoreCase(PatientDashboardActivity.class.getSimpleName())) {
//                                    ((Activity) mContext).finish();
//                                }
//                            } else {
//                                mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//                            }
                        }
                        break;
                    case 2:
                        if (selectedPosition != 2) {
//                            if (SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
//                                mContext.startActivity(new Intent(mContext, AlertsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//
//                                if (!mContext.getClass().getSimpleName().equalsIgnoreCase(PatientDashboardActivity.class.getSimpleName())) {
//                                    ((Activity) mContext).finish();
//                                }
//                            } else {
//                                mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//                            }
                        }
                        break;
                    case 3:
                        if (selectedPosition != 3) {
//                            if (SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
//                                mContext.startActivity(new Intent(mContext, DownloadActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//
//                                if (!mContext.getClass().getSimpleName().equalsIgnoreCase(PatientDashboardActivity.class.getSimpleName())) {
//                                    ((Activity) mContext).finish();
//                                }
//                            } else {
//                                mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//                            }
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
