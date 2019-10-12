package com.example.mom.afflilate.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.SmsReceiver;
import com.example.mom.afflilate.interfaces.SmsListener;
import com.example.mom.afflilate.model.CommonDataBean;
import com.example.mom.afflilate.model.LoginBean;
import com.example.mom.afflilate.presenter.LoginPresenter;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.SessionManager;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.view.LoginViews;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity implements View.OnClickListener, LoginViews {

    private Context mContext;
    private TextView mTvSentOtpMsg, mTvChangeNumber, mTvTimer, mTvDidNotGetOtp;
    private EditText mEtOTPOne, mEtOTPTwo, mEtOTPThree, mEtOTPFour;
    private Button mBtnResendOtp;

    private CountDownTimer countTimer;
    private LoginPresenter mLoginPresenter;
    private ProgressBarHandler mProgressBarHandler;
    private boolean isResend = false;

    public static String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == 'x') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);
        mContext = this;
        startSMSRetrieval();
        setupToolbar();
        initializePresenters();
        initializeViews();
    }

    public void startSMSRetrieval() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void setupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar_top);
            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            setSupportActionBar(toolbar);
            mTitle.setText(getResources().getString(R.string.title_enter_otp));
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeViews() {
        mTvSentOtpMsg = findViewById(R.id.tvSentOtpMsg);
        mTvChangeNumber = findViewById(R.id.tvChangeNumber);
        mTvTimer = findViewById(R.id.tvTimer);
        mTvDidNotGetOtp = findViewById(R.id.tvDidNotGetOtp);
        mEtOTPOne = findViewById(R.id.etOTPOne);
        mEtOTPTwo = findViewById(R.id.etOTPTwo);
        mEtOTPThree = findViewById(R.id.etOTPThree);
        mEtOTPFour = findViewById(R.id.etOTPFour);
        mBtnResendOtp = findViewById(R.id.btnResendOtp);
        mTvChangeNumber.setOnClickListener(this);
        mBtnResendOtp.setOnClickListener(this);
        mEtOTPOne.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    mEtOTPTwo.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mEtOTPTwo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    mEtOTPThree.requestFocus();
                } else {
                    mEtOTPOne.setFocusableInTouchMode(true);
                    mEtOTPOne.setFocusable(true);
                    mEtOTPOne.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mEtOTPThree.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    mEtOTPFour.requestFocus();
                } else {
                    mEtOTPTwo.setFocusableInTouchMode(true);
                    mEtOTPTwo.setFocusable(true);
                    mEtOTPTwo.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mEtOTPFour.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    Utilities.hideKeyboard(Otp.this);
                } else {
                    mEtOTPThree.setFocusableInTouchMode(true);
                    mEtOTPThree.setFocusable(true);
                    mEtOTPThree.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                mEtOTPOne.setText(messageText);
            }
        });

        mTvSentOtpMsg.setText(getString(R.string.mobile_opt_sent_msg) + " " + maskCardNumber(SessionManager.getString(Constants.KEY_MOBILE_NO), "xx******xx"));
        displayTimer();
    }

    private void initializePresenters() {
        mProgressBarHandler = new ProgressBarHandler(mContext);
        mLoginPresenter = new LoginPresenter(this, mContext, mProgressBarHandler);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvChangeNumber:
                startActivity(new Intent(Otp.this, Login.class));
                finish();
                break;
            case R.id.btnResendOtp:
                if (!isResend) {
                    checkValidation();
                }else {
                    performRequiredActionResend();
                }
                break;
        }
    }

    private void performRequiredActionResend() {
        if (Utilities.isNetworkAvailable(mContext)) {
            mLoginPresenter.getSendOtp(SessionManager.getString(Constants.KEY_MOBILE_NO));
        } else {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.no_internet_message));
        }
    }

    private void checkValidation() {
        if (mEtOTPOne.getText().toString().trim().isEmpty() || mEtOTPTwo.getText().toString().trim().isEmpty() ||
                mEtOTPThree.getText().toString().trim().isEmpty() || mEtOTPFour.getText().toString().trim().isEmpty()) {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.error_please_enter_the_otp));
        } else if (!(mEtOTPOne.getText().toString()+ mEtOTPTwo.getText().toString() + mEtOTPThree.getText().toString() +
                mEtOTPFour.getText().toString()).equalsIgnoreCase(SessionManager.getString(Constants.KEY_OTP))) {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.error_otp));
        } else {
            performRequiredActionVerification();
        }
    }

    private void performRequiredActionVerification() {
        if (Utilities.isNetworkAvailable(mContext)) {
            mLoginPresenter.postVerificationOtp(SessionManager.getString(Constants.KEY_MOBILE_NO), SessionManager.getString(Constants.KEY_OTP));
        } else {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.no_internet_message));
        }
    }

    // This method is used to set the timer of 3 minutes whenever api hit for the OTP
    private void displayTimer() {
        mTvTimer.setVisibility(View.VISIBLE);
        long minutesMills = TimeUnit.MINUTES.toMillis(2); // set minutes here
        countTimer = new CountDownTimer(minutesMills, 1000) {
            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                mTvDidNotGetOtp.setVisibility(View.GONE);
                mTvTimer.setText(Utilities.strDisplayTimer(millisUntilFinished));
                mBtnResendOtp.setText(getString(R.string.submit_otp));
            }

            public void onFinish() {
                isResend = true;
                mTvTimer.setVisibility(View.GONE);
                mBtnResendOtp.setText(getString(R.string.resend_otp));
                mTvDidNotGetOtp.setVisibility(View.VISIBLE);
            }
        };
        countTimer.cancel();
        countTimer.start();
    }

    @Override
    public void getSendOtp(LoginBean loginBean) {
        if (loginBean.getMessage().equalsIgnoreCase(Constants.KEY_SUCCESS)) {
            if (loginBean.getData() != null && loginBean.getData().toString().length() > 0) {
                SessionManager.setString(Constants.KEY_OTP, loginBean.getData().getOtp());
                isResend = false;
                displayTimer();
            }
        }
    }

    @Override
    public void postVerificationOtp(CommonDataBean commonDataBean) {
        if (commonDataBean.getMessage().equalsIgnoreCase(Constants.KEY_SUCCESS)) {
            if (commonDataBean.getData() != null && commonDataBean.getData().toString().length() > 0) {
                SessionManager.setBoolean(Constants.KEY_IS_LOGGED_IN, true);
                startActivity(new Intent(Otp.this, HomeActivity.class));
                finish();
            }
        }
    }
}
