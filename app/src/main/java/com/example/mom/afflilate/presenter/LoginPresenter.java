package com.example.mom.afflilate.presenter;

import android.content.Context;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.interfaces.CommonAPIs;
import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;
import com.example.mom.afflilate.services.APIClientRetrofit;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.view.LoginViews;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginViews mLoginViews;
    private CommonAPIs mCommonAPI;
    private Context mContext;
    private ProgressBarHandler mProgressBarHandler;

    public LoginPresenter(LoginViews loginViews, Context context, ProgressBarHandler progressBarHandler) {
        this.mLoginViews = loginViews;
        this.mContext = context;
        this.mProgressBarHandler = progressBarHandler;

        mCommonAPI = APIClientRetrofit.getClient().create(CommonAPIs.class);
    }

    public void getSendOtp(String mobile) {
        mProgressBarHandler.showProgress(mContext.getResources().getString(R.string.progress_login));
        Call<LoginBean> call = mCommonAPI.getSendOTP(Constants.API_SEND_OTP + mobile);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                try {
                    mProgressBarHandler.hideProgress();
                    if (response.code() == 400 || response.code() == 422) {
                        JSONObject jsonObject = null;
                        if (response.errorBody() != null) {
                            jsonObject = new JSONObject(response.errorBody().string());
                        }
                        Utilities.showAlertDialog(mContext, mContext.getResources().getString(R.string.hint_ok), "",
                                jsonObject.optString(Constants.KEY_MESSAGE));
                    } else {
                        if (response.body() != null)
                            mLoginViews.getSendOtp(response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                mProgressBarHandler.hideProgress();
                Utilities.showAlertDialog(mContext, mContext.getResources().getString(R.string.hint_ok), "",
                        mContext.getString(R.string.something_wrong));
            }
        });
    }

    public void postVerificationOtp(String mobileNumber, String otp) {
        mProgressBarHandler.showProgress(mContext.getResources().getString(R.string.progress_login));
        Call<CommonDataBean> call = mCommonAPI.postOTPVerification(Constants.API_VERIFICATION_OTP, mobileNumber, otp);
        call.enqueue(new Callback<CommonDataBean>() {
            @Override
            public void onResponse(Call<CommonDataBean> call, Response<CommonDataBean> response) {
                try {
                    mProgressBarHandler.hideProgress();
                    if (response.code() == 400 || response.code() == 422) {
                        JSONObject jsonObject = null;
                        if (response.errorBody() != null) {
                            jsonObject = new JSONObject(response.errorBody().string());
                        }
                        Utilities.showAlertDialog(mContext, mContext.getResources().getString(R.string.hint_ok), "",
                                jsonObject.optString(Constants.KEY_MESSAGE));
                    } else {
                        if (response.body() != null)
                            mLoginViews.postVerificationOtp(response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonDataBean> call, Throwable t) {
                mProgressBarHandler.hideProgress();
                Utilities.showAlertDialog(mContext, mContext.getResources().getString(R.string.hint_ok), "",
                        mContext.getString(R.string.something_wrong));
            }
        });
    }
}
