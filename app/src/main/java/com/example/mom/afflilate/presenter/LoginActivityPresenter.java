package com.example.mom.afflilate.presenter;

import android.content.Context;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;
import com.example.mom.afflilate.services.CommonAPIServices;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.view.LoginActivityViews;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter {
    private LoginActivityViews mLoginActivityViews;
    private CommonAPIServices mCommonAPIServices;
    private Context mContext;
    private ProgressBarHandler mProgressBarHandler;

    public LoginActivityPresenter(LoginActivityViews loginActivityViews, Context context, ProgressBarHandler progressBarHandler) {
        this.mLoginActivityViews = loginActivityViews;
        this.mContext = context;
        this.mProgressBarHandler = progressBarHandler;

        if (mCommonAPIServices == null) {
            mCommonAPIServices = new CommonAPIServices();
        }
    }

    public void getSendOtp(String mobile) {
        mProgressBarHandler.showProgress(mContext.getResources().getString(R.string.progress_login));
        Call<LoginBean> call = mCommonAPIServices.getCommonAPIAuthorization().getSendOTP(Constants.API_SEND_OTP + mobile);
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
                            mLoginActivityViews.getSendOtp(response.body());
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

    public void getVerification(String mobile, String otp) {
        mProgressBarHandler.showProgress(mContext.getResources().getString(R.string.progress_login));
        Call<CommonDataBean> call = mCommonAPIServices.getCommonAPIAuthorization().getOTPVerification(Constants.API_VERIFICATION_OTP);
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
                            mLoginActivityViews.getVerificationOtp(response.body());
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
