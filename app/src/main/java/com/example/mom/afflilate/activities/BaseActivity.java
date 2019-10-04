package com.example.mom.afflilate.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.mom.afflilate.R;
import com.example.mom.afflilate.utils.BoomButtonUtils;
import com.example.mom.afflilate.utils.ProgressBarHandler;

public abstract class BaseActivity extends AppCompatActivity {

    public ProgressBarHandler mProgressBarHandler;
    private Context mContext;
    private AHBottomNavigation bottomAppBar;
    private View mBottomLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        mContext = getContext();
        initializeBottomViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public abstract int getLayoutResourceId();

    public abstract Context getContext();

    public abstract int doSetSelectedPosition();

    private void initializeBottomViews() {
        mBottomLayout = findViewById(R.id.bottom_layout);
        bottomAppBar = mBottomLayout.findViewById(R.id.bottom_navigation);
        BoomButtonUtils.addBottomMenuItemsInAppBar(mContext, bottomAppBar, doSetSelectedPosition());
        bottomAppBar.bringToFront();
    }

    public void visibleBottomLayout(boolean isvisible) {
        if (isvisible) {
            mBottomLayout.setVisibility(View.VISIBLE);
        } else {
            mBottomLayout.setVisibility(View.GONE);
        }
    }

    public void setSelectedBottomTab(int position) {
        BoomButtonUtils.changeSelectedPositionOfBottomItem(position, bottomAppBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
