package com.example.mom.afflilate.services;

import android.os.Build;

import com.example.mom.afflilate.BuildConfig;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.Utilities;

import okhttp3.Request;

public class AuthorizationHeaders {

    public static Request getRequestBuilder(Request original) {
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader(Constants.KEY_HEADER_CONTENTTYPE, Constants.CONTENTTYPE_VALUE)
                .addHeader(Constants.KEY_HEADER_TIMEZONE, Utilities.getTimezone())
                .addHeader(Constants.KEY_HEADER_DEVICETYPE, Constants.DEVICE_TYPE)
                .addHeader(Constants.KEY_HEADER_VERSION, BuildConfig.VERSION_NAME)
                .addHeader(Constants.KEY_DEVICE_NAME, Build.MODEL)
                .addHeader(Constants.KEY_HEADER_USERTYPE, Constants.USER_TYPE);
        return requestBuilder.build();
    }
}
