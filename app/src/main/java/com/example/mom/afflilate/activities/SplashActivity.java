package com.example.mom.afflilate.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mom.afflilate.BuildConfig;
import com.example.mom.afflilate.R;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.Navigation;
import com.example.mom.afflilate.utils.SessionManager;

import java.text.MessageFormat;

public class SplashActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mImgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_splash);
            mContext = this;
            initializeViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the view
     */
    private void initializeViews() {
        TextView mTvVersion = findViewById(R.id.tvVersion);
        mTvVersion.setText(MessageFormat.format("{0} {1}", getString(R.string.version), BuildConfig.VERSION_NAME));
        try {
            mImgSplash = findViewById(R.id.imgSplash);
            redirectTOLoginOrDashboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectTOLoginOrDashboard() {
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
                        Navigation.navigateToDashboard(mContext);
                        finishAffinity();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }

                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }
}
