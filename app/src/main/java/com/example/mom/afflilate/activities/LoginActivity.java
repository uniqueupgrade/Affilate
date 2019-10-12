package com.example.mom.afflilate.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;
import com.example.mom.afflilate.presenter.LoginActivityPresenter;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.SessionManager;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.utils.ValidationRules;
import com.example.mom.afflilate.utils.VectorDrawableUtilities;
import com.example.mom.afflilate.view.LoginActivityViews;

public class LoginActivity extends Activity implements View.OnClickListener, LoginActivityViews {

    private Context mContext;
    private Button mBtnRequestOtp;
    private TextView mTvMobileNoValidation, mTvTerms;
    private EditText mEtMobileNo;
    private CheckBox mCbTerms;
    private LoginActivityPresenter mLoginActivityPresenter;
    private ProgressBarHandler mProgressBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mContext = this;
        initializePresenters();
        initializeViews();
    }

    private void initializePresenters() {
        mProgressBarHandler = new ProgressBarHandler(mContext);
        mLoginActivityPresenter = new LoginActivityPresenter(this, mContext, mProgressBarHandler);
    }

    private void initializeViews() {
        mEtMobileNo = findViewById(R.id.et_mobilenumber);
        mTvMobileNoValidation = findViewById(R.id.tv_mobilevalidation);

        mBtnRequestOtp = findViewById(R.id.btnRequestOtp);
        mCbTerms = findViewById(R.id.cb_terms);
        mTvTerms = findViewById(R.id.tv_terms);

        mBtnRequestOtp.setOnClickListener(this);

        setTNCText();
        setImageDrawable();
    }

    private void setImageDrawable() {
        VectorDrawableUtilities.setVectorForPreLollipop(mEtMobileNo, R.drawable.ic_reg_mobile, this, Constants.DRAWABLE_LEFT);
        mCbTerms.setButtonDrawable(VectorDrawableUtilities.setVectorForPreLollipop(R.drawable.checkbox_selector, this));
    }

    private void setTNCText() {
        SpannableString ss = new SpannableString(getResources().getString(R.string.agree_terms));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {
                Intent mIntent = new Intent(mContext, WebViewActivity.class);
                mIntent.putExtra("titlename", getResources().getString(R.string.terms_and_conditions));
                mIntent.putExtra(Constants.INTENT_KEY_LOGIN_TC_PRIVACY, true);
                mIntent.putExtra("url", Constants.LINK_TERMS_AND_CONDITIONS);
                startActivity(mIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 9, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvTerms.setText(ss);
        mTvTerms.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRequestOtp:
                Utilities.hideKeyboard(LoginActivity.this);
                mTvMobileNoValidation.setVisibility(View.GONE);
                checkValidation();
                break;
            default:
                break;
        }
    }

    private void checkValidation() {
        if (mEtMobileNo.getText().toString().trim().isEmpty()) {
            mTvMobileNoValidation.setVisibility(View.VISIBLE);
            mTvMobileNoValidation.setText(getString(R.string.error_mobile_number));
        } else if (!ValidationRules.isValidIndianMobile(mEtMobileNo.getText().toString())) {
            mTvMobileNoValidation.setVisibility(View.VISIBLE);
            mTvMobileNoValidation.setText(getString(R.string.error_mobile));
        } else if (!mCbTerms.isChecked()) {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.error_terms));
        } else {
            performRequiredAction();
        }
    }

    private void performRequiredAction() {
        if (Utilities.isNetworkAvailable(mContext)) {
            SessionManager.setString(Constants.KEY_MOBILE_NO, mEtMobileNo.getText().toString());
            mLoginActivityPresenter.getSendOtp(mEtMobileNo.getText().toString().trim());
        } else {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.no_internet_message));
        }
    }

    @Override
    public void getSendOtp(LoginBean loginBean) {
        if (loginBean.getMessage().equalsIgnoreCase(Constants.KEY_SUCCESS)) {
            if (loginBean.getData() != null && loginBean.getData().toString().length() > 0) {
                SessionManager.setString(Constants.KEY_OTP, loginBean.getData().getOtp());
                startActivity(new Intent(LoginActivity.this, OtpActivity.class));
                finish();
            }
        }
    }

    @Override
    public void postVerificationOtp(CommonDataBean commonDataBean) {

    }
}
