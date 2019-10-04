package com.example.mom.afflilate.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mom.afflilate.R;

import java.util.Objects;

public class ProgressBarHandler {

    final Handler handler = new Handler();
    private Dialog mProgressDialog;
    private TextView mProgressMessage;
    private Context mContext;

    public ProgressBarHandler(Context context) {

        mContext = context;
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(R.layout.custom_dialog_progress);
        mProgressMessage = mProgressDialog.findViewById(R.id.progressMessage);
        mProgressMessage.setText(context.getResources().getString(R.string.progress_login));
        Objects.requireNonNull(mProgressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setCancelable(false);

        hideProgress();
    }

    public void showProgress(String message) {
        try {
            if (mProgressDialog != null && !((Activity) mContext).isFinishing() && !mProgressDialog.isShowing()) {
                mProgressMessage.setText(message);
                ((Activity) mContext).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgress() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                ((Activity) mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (!((Activity) mContext).isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
