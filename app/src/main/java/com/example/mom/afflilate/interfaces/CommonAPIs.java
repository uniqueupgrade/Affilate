package com.example.mom.afflilate.interfaces;


import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;
import com.example.mom.afflilate.model.ProductDetailsBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface CommonAPIs {
    // Get APIs
    @GET
    Call<LoginBean> getSendOTP(@Url String url);

    @GET
    Call<ArrayList<ProductDetailsBean>> getAllBrandMobilesAndTablets(@Url String url);


    //Post APIs
    @FormUrlEncoded
    @POST
    Call<CommonDataBean> postOTPVerification(@Url String url,
                                             @Field("mobileNumber") String mobileNumber,
                                             @Field("otp") String otp);
}

