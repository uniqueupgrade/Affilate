package com.example.mom.afflilate.listeners;

import android.content.Context;
import android.view.View;

import com.example.mom.afflilate.R;
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
        }
    }
}
