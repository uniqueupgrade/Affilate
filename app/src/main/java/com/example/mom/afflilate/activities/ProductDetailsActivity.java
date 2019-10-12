package com.example.mom.afflilate.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.adaapter.ProductDetailsListAdapter;
import com.example.mom.afflilate.listeners.BackButtonClickListener;
import com.example.mom.afflilate.listeners.TryAgainButtonClickListener;
import com.example.mom.afflilate.model.ProductDetailsBean;
import com.example.mom.afflilate.presenter.ProductDetailsPresenter;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.utils.VectorDrawableUtilities;
import com.example.mom.afflilate.view.ProductDetailsViews;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Objects;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsViews {

    private Context mContext;
    private String mProductName;
    private ShimmerFrameLayout mShimmerFrameLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mNoDataView, mNoInternetView;
    private ImageView mIvNoInternet;
    private ListView mLvProductItem;
    private ArrayList<ProductDetailsBean> mAlProductDetailsBean;
    private ProductDetailsPresenter mProductDetailsPresenter;
    private ProductDetailsListAdapter mProductDetailsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mContext = this;
        getIntentProductData();
        setupToolbar();
        initializeView();
    }

    private void getIntentProductData() {
        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.INTENT_KEY_PRODUCT_NAME)) {
                mProductName = getIntent().getStringExtra(Constants.INTENT_KEY_PRODUCT_NAME);
            }
        }
    }

    private void setupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar_top);
            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            ImageView ivBack = toolbar.findViewById(R.id.ivBack);
            ivBack.setImageDrawable(VectorDrawableUtilities.setVectorForPreLollipop(R.drawable.ic_back_top, this));
            ivBack.setOnClickListener(new BackButtonClickListener(this));
            setSupportActionBar(toolbar);
            mTitle.setText(mProductName);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeView() {
        mShimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mShimmerFrameLayout.setVisibility(View.VISIBLE);
        mNoDataView = findViewById(R.id.no_data);
        mLvProductItem = findViewById(R.id.lvProductItem);
        mProductDetailsPresenter = new ProductDetailsPresenter(this, mContext);
        loadInternetGIF();
    }

    private void loadInternetGIF() {
        mNoInternetView = findViewById(R.id.no_internet);
        Button mTryAgainButton = mNoInternetView.findViewById(R.id.btnTryAgain);
        mIvNoInternet = mNoInternetView.findViewById(R.id.imgNoInternet);
        TryAgainButtonClickListener mTryAgainButtonClickListener = new TryAgainButtonClickListener(mContext);
        mTryAgainButton.setOnClickListener(mTryAgainButtonClickListener);
        Utilities.loadNoInternetGIFImage(mContext, mIvNoInternet);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShimmerFrameLayout.startShimmerAnimation();
        if (mProductName.equalsIgnoreCase(getString(R.string.electronics))) {
            getElectronicsAPICallWS();
        } else if (mProductName.equalsIgnoreCase(getString(R.string.fashoin))) {
            getFashionAPICallWS();
        } else if (mProductName.equalsIgnoreCase(getString(R.string.home_and_furniture))) {
            getHomeFurnitureAPICallWS();
        } else if (mProductName.equalsIgnoreCase(getString(R.string.mobile_and_tablets))) {
            getMobileAndTabletsAPICallWS();
        } else if (mProductName.equalsIgnoreCase(getString(R.string.tv_and_appliances))) {
            getTvAndAppliancesAPICallWS();
        }
    }

    private void getElectronicsAPICallWS() {
    }

    private void getFashionAPICallWS() {
    }

    private void getHomeFurnitureAPICallWS() {
    }

    private void getMobileAndTabletsAPICallWS() {
        if (Utilities.isNetworkAvailable(mContext)) {
            if (!mShimmerFrameLayout.isShown()) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
            mNoInternetView.setVisibility(View.GONE);
            mNoDataView.setVisibility(View.GONE);
            mProductDetailsPresenter.getAllBrandMobilesAndTablets();
        } else {
            mShimmerFrameLayout.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
            mLvProductItem.setVisibility(View.GONE);
            mNoInternetView.setVisibility(View.VISIBLE);
        }
    }

    private void getTvAndAppliancesAPICallWS() {
    }

    @Override
    public void getAllBrandMobilesAndTablets(ArrayList<ProductDetailsBean> alProductDetailsBean) {
        mShimmerFrameLayout.stopShimmerAnimation();
        mShimmerFrameLayout.setVisibility(View.GONE);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mLvProductItem.setVisibility(View.VISIBLE);
        mAlProductDetailsBean = alProductDetailsBean;
        setupMemberAdapter();
    }

    private void setupMemberAdapter() {
        if (mAlProductDetailsBean.size() == 0) {
            mNoDataView.setVisibility(View.VISIBLE);
        } else {
            mNoDataView.setVisibility(View.GONE);
            mProductDetailsListAdapter = new ProductDetailsListAdapter(mContext, mAlProductDetailsBean);
            mLvProductItem.setAdapter(mProductDetailsListAdapter);
            mLvProductItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });
        }
    }
}
