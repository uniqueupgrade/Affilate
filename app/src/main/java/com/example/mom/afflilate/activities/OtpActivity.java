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
import com.example.mom.afflilate.presenter.LoginActivityPresenter;
import com.example.mom.afflilate.utils.Constants;
import com.example.mom.afflilate.utils.ProgressBarHandler;
import com.example.mom.afflilate.utils.SessionManager;
import com.example.mom.afflilate.utils.Utilities;
import com.example.mom.afflilate.view.LoginActivityViews;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener, LoginActivityViews {

    private Context mContext;
    private TextView mTvSentOtpMsg, mTvChangeNumber, mTvTimer;
    private EditText mEtOTPOne, mEtOTPTwo, mEtOTPThree, mEtOTPFour;
    private Button mBtnResendOtp;

    private CountDownTimer countTimer;
    private LoginActivityPresenter mLoginActivityPresenter;
    private ProgressBarHandler mProgressBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);
        mContext = this;
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
        mEtOTPOne = findViewById(R.id.etOTPOne);
        mEtOTPTwo = findViewById(R.id.etOTPTwo);
        mEtOTPThree = findViewById(R.id.etOTPThree);
        mEtOTPFour = findViewById(R.id.etOTPFour);
        mBtnResendOtp = findViewById(R.id.btnResendOtp);
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
                    Utilities.hideKeyboard(OtpActivity.this);
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

        mTvSentOtpMsg.setText(getString(R.string.mobile_opt_sent_msg) + " " + "96******96");
        displayTimer();
    }

    private void initializePresenters() {
        mProgressBarHandler = new ProgressBarHandler(mContext);
        mLoginActivityPresenter = new LoginActivityPresenter(this, mContext, mProgressBarHandler);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnResendOtp:
                checkValidation();
                break;
        }
    }

    private void checkValidation() {
        if (mEtOTPOne.getText().toString().trim().isEmpty() || mEtOTPTwo.getText().toString().trim().isEmpty() ||
                mEtOTPThree.getText().toString().trim().isEmpty() || mEtOTPFour.getText().toString().trim().isEmpty()) {
            Utilities.showAlertDialog(mContext, getString(R.string.hint_ok), "", getString(R.string.error_otp));
        } else {
            performRequiredAction();
        }
    }

    private void performRequiredAction() {
        if (Utilities.isNetworkAvailable(mContext)) {
            mLoginActivityPresenter.getVerification(SessionManager.getString(Constants.KEY_MOBILE_NO), mEtOTPOne.getText().toString() + mEtOTPTwo.getText().toString() + mEtOTPThree.getText().toString() + mEtOTPFour.getText().toString());
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
                mTvTimer.setText(Utilities.strDisplayTimer(millisUntilFinished));
                mBtnResendOtp.setText(getString(R.string.submit_otp));
            }

            public void onFinish() {
                mTvTimer.setVisibility(View.GONE);
            }
        };
        countTimer.cancel();
        countTimer.start();
    }

    @Override
    public void getSendOtp(LoginBean loginBean) {

    }

    @Override
    public void getVerificationOtp(CommonDataBean commonDataBean) {
        if (commonDataBean.getStatus().equalsIgnoreCase(Constants.KEY_SUCCESS)) {
            if (commonDataBean.getData() != null && commonDataBean.getData().toString().length() > 0) {
                SessionManager.setBoolean(Constants.KEY_IS_LOGGED_IN, true);
                startActivity(new Intent(OtpActivity.this, HomeActivity.class));
                finish();
            }
        }
    }
}
