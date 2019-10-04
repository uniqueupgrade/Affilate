package com.example.mom.afflilate.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.adaapter.BannerAdapter;
import com.example.mom.afflilate.listeners.TryAgainButtonClickListener;
import com.example.mom.afflilate.model.BannerListBean;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.Utilities;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    Context mContext;
    RecyclerView mRvBannerList, mRvProductList;
    View no_internet;
    boolean mDoubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initializeViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int doSetSelectedPosition() {
        return 0;
    }

    private void initializeViews() {
        mRvBannerList = findViewById(R.id.rvBannerList);
        mRvProductList = findViewById(R.id.rvProductList);
        mProgressBarHandler = new ProgressBarHandler(mContext);
        initializeInternetViews();
    }


    private void initializeInternetViews() {
        no_internet = findViewById(R.id.no_internet);
        ImageView mImgNoInternet = no_internet.findViewById(R.id.imgNoInternet);
        Button mBtnTryAgain = no_internet.findViewById(R.id.btnTryAgain);

        TryAgainButtonClickListener mTryAgainButtonClickListener = new TryAgainButtonClickListener(mContext);
        mBtnTryAgain.setOnClickListener(mTryAgainButtonClickListener);
        Utilities.loadNoInternetGIFImage(mContext, mImgNoInternet);
    }

    public void attachVideoBannerView() {
        try {
            ArrayList<BannerListBean.Banner> mShownBannerList = new ArrayList<>();
            //API Call and add the data into banner arraylist
            BannerAdapter bannerAdapter = new BannerAdapter(this, mShownBannerList);
            mRvBannerList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRvBannerList.setAdapter(bannerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (mDoubleBackToExitPressedOnce) {
                ActivityCompat.finishAffinity(HomeActivity.this);
            }
            mDoubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.press_again), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDoubleBackToExitPressedOnce = false;
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
