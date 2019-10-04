package com.example.mom.afflilate.interfaces;


import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CommonAPIs {
    // Get APIs
    @GET
    Call<LoginBean> getSendOTP(@Url String url);

    @GET
    Call<CommonDataBean> getOTPVerification(@Url String url);
}

