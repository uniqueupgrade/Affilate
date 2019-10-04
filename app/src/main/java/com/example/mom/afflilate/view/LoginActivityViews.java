package com.example.mom.afflilate.view;

import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;

public interface LoginActivityViews {
    void getSendOtp(LoginBean loginBean);

    void getVerificationOtp(CommonDataBean commonDataBean);
}
