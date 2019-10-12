package com.example.mom.afflilate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private LoginData data;
    @SerializedName("status_message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class LoginData {
        @SerializedName("otp")
        @Expose
        private String otp;

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }
    }
}
