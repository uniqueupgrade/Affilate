package com.example.mom.afflilate.listeners;

import android.content.Context;
import android.view.View;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.activities.ProductDetailsActivity;

public class BackButtonClickListener implements View.OnClickListener {

    private Context mContext;

    public BackButtonClickListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                BackButtonClickEvent();
                break;

            default:
                break;
        }
    }

    private void BackButtonClickEvent() {
        if (mContext.getClass().getSimpleName().equalsIgnoreCase(ProductDetailsActivity.class.getSimpleName())) {
            ProductDetailsActivity productDetailsActivity = (ProductDetailsActivity) mContext;
            productDetailsActivity.finish();
        }
    }
}
