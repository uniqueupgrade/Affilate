package com.example.mom.afflilate.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.adaapter.SettingListAdapter;
import com.example.mom.afflilate.listeners.TryAgainButtonClickListener;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.SessionManager;
import com.example.mom.afflilate.utils.Utilities;
import com.facebook.shimmer.ShimmerFrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private int[] mExploreIcon = {R.drawable.ic_explore_my_profile, R.drawable.ic_explore_faq,
            R.drawable.ic_explore_share, R.drawable.ic_explore_service, R.drawable.ic_explore_howitwork, R.drawable.ic_explore_tnc,
            R.drawable.ic_explore_contact_mob, R.drawable.ic_explore_logout};

    private Context mContext;
    private TextView mTvEdit;
    private TextView mTvName;
    private TextView mTvLocation;
    private CircleImageView mRvProfilePic;
    private ListView mListView;
    private ProgressBar mProgressBar;
    private RelativeLayout mMainLayout;
    private ShimmerFrameLayout mShimmerBannerContainer;
    private View mViewNoInternet;

    private String[] mTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initializeViews();
        setAdpaterForExplore();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_setting;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int doSetSelectedPosition() {
        return 3;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelectedBottomTab(3);
    }

    private void initializeViews() {
        mTvEdit = findViewById(R.id.tvEdit);
        mRvProfilePic = findViewById(R.id.profilePic);
        mTvName = findViewById(R.id.tvName);
        mTvLocation = findViewById(R.id.tvLocation);
        mListView = findViewById(R.id.listview);
        mProgressBar = findViewById(R.id.progress);
        mMainLayout = findViewById(R.id.mainRelative);
        mShimmerBannerContainer = findViewById(R.id.shimmer_view_container);
        mViewNoInternet = findViewById(R.id.no_internet);
        Button mBtnTryAgain = mViewNoInternet.findViewById(R.id.btnTryAgain);
        ImageView mImgNoInternet = mViewNoInternet.findViewById(R.id.imgNoInternet);
        TryAgainButtonClickListener mTryAgainButtonClickListener = new TryAgainButtonClickListener(mContext);
        mBtnTryAgain.setOnClickListener(mTryAgainButtonClickListener);
        Utilities.loadNoInternetGIFImage(mContext, mImgNoInternet);

        mTvEdit.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void setAdpaterForExplore() {
        SettingListAdapter settingListAdapter;
        mTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        settingListAdapter = new SettingListAdapter(mContext, mTitles, mExploreIcon);
        mListView.setAdapter(settingListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String SHARE_PLAYSTORE_LINK = "https://play.google.com/store/apps/details?id=+" + getPackageName() + "&hl=en";

        Intent intent = new Intent(mContext, WebViewActivity.class);
        if (position == 0) {
            Intent mIntent = new Intent(mContext, LoginActivity.class);
            mIntent.putExtra("ComeFrom", "Home");
            startActivity(mIntent);
        } else if (position == 1) {
            intent.putExtra("titlename", mTitles[position]);
            intent.putExtra("url", Constants.LINK_FAQ);
            intent.putExtra("ComeFrom", "Home");
            startActivity(intent);
        } else if (position == 2) {
            Utilities.openShareDialog(mContext, SHARE_PLAYSTORE_LINK, getString(R.string.app_name), "");
        } else if (position == 3) {
            intent.putExtra("titlename", mTitles[position]);
            intent.putExtra("url", Constants.LINK_SERVICE);
            intent.putExtra("ComeFrom", "Home");
            startActivity(intent);
        } else if (position == 4) {
            intent.putExtra("titlename", mTitles[position]);
            intent.putExtra("url", Constants.LINK_HOW_IT_WORKS);
            intent.putExtra("ComeFrom", "Home");
            startActivity(intent);
        } else if (position == 5) {
            intent.putExtra("titlename", mTitles[position]);
            intent.putExtra("url", Constants.LINK_TERMS_AND_CONDITIONS);
            intent.putExtra("ComeFrom", "Home");
            startActivity(intent);
        } else if (position == 6) {
            intent.putExtra("titlename", mTitles[position]);
            intent.putExtra("url", Constants.LINK_CONTACT_US);
            intent.putExtra("ComeFrom", "Home");
            startActivity(intent);
        } else if (position == 7) {
            SessionManager.setBoolean(Constants.KEY_IS_LOGGED_OUT, true);
            showLogoutConfirmationDialog();
        }
    }

    private void showLogoutConfirmationDialog() {
        if (Utilities.isNetworkAvailable(mContext)) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(mContext, R.style.AlertDialogTheme);
            alertbox.setMessage(mContext.getResources().getString(R.string.logout_confirmation_message));
            alertbox.setPositiveButton(getString(R.string.hint_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (Utilities.isNetworkAvailable(mContext)) {
                        //Logout api call
                    } else {
                        mProgressBarHandler.hideProgress();
                        Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.no_internet_message));
                    }
                }
            });

            // do something when the button is clicked
            alertbox.setNegativeButton(getString(R.string.hint_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SessionManager.setBoolean(Constants.KEY_IS_LOGGED_OUT, false);
                    dialogInterface.dismiss();
                }
            });
            alertbox.show();
        } else {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.no_internet_message));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvEdit:
                break;
        }
    }
}
