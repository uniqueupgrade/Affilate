package com.example.mom.afflilate.listeners;

import android.content.Context;
import android.view.View;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.activities.HomeActivity;
import com.example.mom.afflilate.activities.SearchActivity;
import com.example.mom.afflilate.activities.SettingActivity;
import com.example.mom.afflilate.activities.ShortListActivity;
import com.example.mom.afflilate.activities.SplashActivity;

public class TryAgainButtonClickListener implements View.OnClickListener {

    private Context mContext;
    private String mCallType;

    public TryAgainButtonClickListener(Context context) {
        this.mContext = context;
    }

    public TryAgainButtonClickListener(Context context, String mCallType) {
        this.mContext = context;
        this.mCallType = mCallType;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTryAgain:
                tryButtonClickEvent();
                break;

            default:
                break;
        }
    }

    private void tryButtonClickEvent() {
        if (mContext.getClass().getSimpleName().equalsIgnoreCase(SplashActivity.class.getSimpleName())) {
            SplashActivity splashActivity = (SplashActivity) mContext;
            //splashActivity.checkAppVersionAPICall();
        }else if(mContext.getClass().getSimpleName().equalsIgnoreCase(HomeActivity.class.getSimpleName())) {
            HomeActivity homeActivity = (HomeActivity) mContext;
            //homeActivity.checkAppVersionAPICall();
        }else if(mContext.getClass().getSimpleName().equalsIgnoreCase(SearchActivity.class.getSimpleName())) {
            SearchActivity searchActivity = (SearchActivity) mContext;
            //searchActivity.checkAppVersionAPICall();
        }else if(mContext.getClass().getSimpleName().equalsIgnoreCase(ShortListActivity.class.getSimpleName())) {
            ShortListActivity shortListActivity = (ShortListActivity) mContext;
            //shortListActivity.checkAppVersionAPICall();
        }else if(mContext.getClass().getSimpleName().equalsIgnoreCase(SettingActivity.class.getSimpleName())) {
            SettingActivity settingActivity = (SettingActivity) mContext;
            //settingActivity.checkAppVersionAPICall();
        }
    }
}
