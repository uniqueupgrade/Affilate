package com.example.mom.afflilate.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.listeners.BackButtonClickListener;
import com.example.mom.afflilate.listeners.TryAgainButtonClickListener;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.utils.VectorDrawableUtilities;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    boolean isResourceLoaded;
    private Context mContext;
    private WebView mWebView;
    private String mUrl;
    private View mViewNoInternet;
    private Toolbar toolbar;
    private ProgressBarHandler mProgressBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().hasExtra(Constants.INTENT_KEY_URL)) {
            if (getIntent().getStringExtra(Constants.INTENT_KEY_URL) != null) {
                mUrl = getIntent().getStringExtra(Constants.INTENT_KEY_URL).replace(" ", "%20");
            }
        }
        setupToolbar();
        initializeViews();
    }

    private void setupToolbar() {
        try {
            toolbar = findViewById(R.id.toolbar_top);
            TextView title = toolbar.findViewById(R.id.toolbar_title);
            ImageView ivBack = toolbar.findViewById(R.id.ivBack);
            ImageView ivHome = toolbar.findViewById(R.id.ivHome);

            ivBack.setImageDrawable(VectorDrawableUtilities.setVectorForPreLollipop(R.drawable.ic_back_top, this));
            if (getIntent().hasExtra(Constants.INTENT_KEY_TITLENAME)) {
                if (getIntent().getStringExtra(Constants.INTENT_KEY_TITLENAME) != null) {
                    title.setText(getIntent().getStringExtra(Constants.INTENT_KEY_TITLENAME));
                }
            }
            ivHome.setVisibility(View.VISIBLE);
            ivHome.setImageDrawable(VectorDrawableUtilities.setVectorForPreLollipop(R.drawable.ic_home_black, this));
            shareButtonClick(ivHome);
            ivBack.setOnClickListener(new BackButtonClickListener(this));
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareButtonClick(ImageView ivHoView) {
//        ivHoView.setOnClickListener(v -> {
//            Intent intent = new Intent(WebViewActivity.this, DashBoardActivity.class);
//            startActivity(intent);
//            ActivityCompat.finishAffinity(WebViewActivity.this);
//        });
    }

    private void initializeViews() {
        hideToolBar();
        mProgressBarHandler = new ProgressBarHandler(mContext);
        mViewNoInternet = findViewById(R.id.no_internet);
        final ImageView mImgNoInternet = findViewById(R.id.imgNoInternet);
        Button mBtnTryAgain = findViewById(R.id.btnTryAgain);
        TryAgainButtonClickListener mTryAgainButtonClickListener = new TryAgainButtonClickListener(mContext);
        mBtnTryAgain.setOnClickListener(mTryAgainButtonClickListener);
        Utilities.loadNoInternetGIFImage(mContext, mImgNoInternet);

        mWebView = findViewById(R.id.webView);
        mProgressBar = findViewById(R.id.progressbar);

        if (mUrl == null)
            return;

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mProgressBarHandler.hideProgress();
                view.loadUrl(url);
                return false;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (isResourceLoaded)
                    mProgressBarHandler.hideProgress();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBarHandler.showProgress(getString(R.string.progress_login));
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                if (Utilities.isNetworkAvailable(mContext)) {
                    try {
                        mWebView.setVisibility(View.VISIBLE);
                        mProgressBarHandler.hideProgress();
                        isResourceLoaded = true;
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        mProgressBarHandler.hideProgress();
                    }
                } else {
                    mProgressBarHandler.hideProgress();
                    mWebView.setVisibility(View.GONE);
                    mViewNoInternet.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                mProgressBarHandler.hideProgress();
                mWebView.setVisibility(View.GONE);
                Log.d("", "onReceivedError() called with: view = [" + view + "], request = [" + request + "], error = [" + error + "]");
                if (!Utilities.isNetworkAvailable(mContext)) {
                    mViewNoInternet.setVisibility(View.VISIBLE);
                }
                super.onReceivedError(view, request, error);
            }
        });
        setWebViewOrVideovView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressBarHandler.hideProgress();
    }

    public void setWebViewOrVideovView() {
        if (Utilities.isNetworkAvailable(mContext)) {
            loadUrl();
        } else {
            mProgressBarHandler.hideProgress();
            mWebView.setVisibility(View.GONE);
            mViewNoInternet.setVisibility(View.VISIBLE);
        }
    }

    private void hideToolBar() {
        if (getIntent().hasExtra(Constants.INTENT_KEY_TITLENAME)) {
            if (getIntent().getStringExtra(Constants.INTENT_KEY_TITLENAME) != null) {
                if (getIntent().getStringExtra(Constants.INTENT_KEY_TITLENAME).equalsIgnoreCase(Constants.CHECK_MAINTAINANCE_STR)) {
                    toolbar.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void loadUrl() {
        mViewNoInternet.setVisibility(View.GONE);
        mUrl = mUrl.replace(" ", "%20");

        if (mUrl.endsWith(Constants.CHECK_PDF_STR)) {
            mUrl = mUrl.replace("https", "http");
            mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + mUrl);
        } else if (mUrl.endsWith("doc") || mUrl.endsWith("docx")) {
            mUrl = mUrl.replace("https", "http");
            mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + mUrl);
        } else {
            mWebView.loadUrl(mUrl);
        }
        mProgressBarHandler.hideProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}