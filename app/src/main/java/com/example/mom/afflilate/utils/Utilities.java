package com.example.mom.afflilate.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mom.afflilate.R;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Utilities {
    public static void loadNoInternetGIFImage(Context context, ImageView imageView) {
        try {
            Glide.with(context).load(R.raw.no_internet)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showAlertDialog(final Context context, String yesButton, String noButton, final String message) {
        try {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            alertbox.setMessage(message);
            alertbox.setCancelable(false);
            alertbox.setPositiveButton(yesButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    if (message.equalsIgnoreCase(Constants.INVALID_REQUEST)) {
                        if (SessionManager.getBoolean(Constants.KEY_IS_LOGGED_IN)) {
                            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                            if (notificationManager != null) {
                                notificationManager.cancelAll();
                            }

                            Navigation.navigateToLogin(context);
                        }
                    }
                    dialog.dismiss();
                }
            });
            if (!noButton.equals("")) {
                // do something when the button is clicked
                alertbox.setNegativeButton(noButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
            }
            alertbox.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String strDisplayTimer(long millisUntilFinished) {
        return MessageFormat.format("{0}", String.format(Constants.FORMAT,
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
    }

    public static String getTimezone() {
        String localTime = "";
        try {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
            Date currentLocalTime = calendar.getTime();
            DateFormat date = new SimpleDateFormat("ZZZZZ", Locale.US);
            return date.format(currentLocalTime);
        } catch (Exception e) {
            e.printStackTrace();
            return localTime;
        }
    }

    public static void BannerWithExcImageView(Context mContext, String url,
                                              int placeholder, ImageView mIvProfileView, final ProgressBar progressBar) {
        if (!isEmptyString(url)) {
            Glide
                    .with(mContext)
                    .load(Uri.parse(url))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter()
                    .listener(new RequestListener<Uri, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .dontAnimate()
                    .error(mContext.getResources().getDrawable(placeholder))
                    .into(mIvProfileView);
        } else {
            progressBar.setVisibility(View.GONE);
            mIvProfileView.setImageDrawable(mContext.getResources().getDrawable(placeholder));
        }
    }

    public static boolean isEmptyString(String text) {
        try {
            return (text == null || text.trim().equals("null") || text.trim()
                    .length() <= 0);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
