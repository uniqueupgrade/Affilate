package com.example.mom.afflilate.presenter;

import android.content.Context;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.interfaces.CommonAPIs;
import com.example.mom.afflilate.model.LoginBean;
import com.example.mom.afflilate.model.ProductDetailsBean;
import com.example.mom.afflilate.services.APIClientRetrofit;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.view.ProductDetailsViews;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsPresenter {
    private ProductDetailsViews mProductDetailsViews;
    private CommonAPIs mCommonAPI;
    private Context mContext;

    public ProductDetailsPresenter(ProductDetailsViews productDetailsViews, Context context) {
        this.mProductDetailsViews = productDetailsViews;
        this.mContext = context;
        mCommonAPI = APIClientRetrofit.getClient().create(CommonAPIs.class);
    }

    public void getAllBrandMobilesAndTablets() {
        Call<ArrayList<ProductDetailsBean>> call = mCommonAPI.getAllBrandMobilesAndTablets(Constants.API_GET_ALL_BRAND_MOBILES_AND_TABLETS);
        call.enqueue(new Callback<ArrayList<ProductDetailsBean>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductDetailsBean>> call, Response<ArrayList<ProductDetailsBean>> response) {
                try {
                    if (response.code() == 400 || response.code() == 422) {
                        JSONObject jsonObject = null;
                        if (response.errorBody() != null) {
                            jsonObject = new JSONObject(response.errorBody().string());
                        }
                        Utilities.showAlertDialog(mContext, mContext.getResources().getString(R.string.hint_ok), "",
                                jsonObject.optString(Constants.KEY_MESSAGE));
                    } else {
                        if (response.body() != null)
                            mProductDetailsViews.getAllBrandMobilesAndTablets(response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductDetailsBean>> call, Throwable t) {
                Utilities.showAlertDialog(mContext, mContext.getResources().getString(R.string.hint_ok), "",
                        mContext.getString(R.string.something_wrong));
            }
        });
    }
}
