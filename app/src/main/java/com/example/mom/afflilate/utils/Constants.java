package com.example.mom.afflilate.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

    public static final String BASE_URL_API = "https://uniqueupgrade.in/apitest/sms/";

    public static final int DRAWABLE_LEFT = 1;
    public static final int DRAWABLE_RIGHT = 2;
    public static final int DRAWABLE_TOP = 3;
    public static final int DRAWABLE_BOTTOM = 4;
    public static final int DRAWABLE_LR_BOTH = 5;
    public static final int DRAWABLE_TB_BOTH = 6;

    //Urls
    public static String WEBSITE = "https://uniqueupgrade.in/apitest/get/api/";
    public static String LINK_TERMS_AND_CONDITIONS = Constants.WEBSITE + "termsandconditionsm";
    public static String LINK_FAQ = Constants.WEBSITE + "faqm";
    public static String LINK_SERVICE = Constants.WEBSITE + "servicesm";
    public static String LINK_HOW_IT_WORKS = Constants.WEBSITE + "howitworksm";
    public static String LINK_CONTACT_US = Constants.WEBSITE + "contactusm";

    //API
    public static String API_SEND_OTP = "sendotp.php?mobileNumber=";
    public static String API_VERIFICATION_OTP = "otpverification.php";

    //Keys
    public static String KEY_SUCCESS = "Success";
    public static String KEY_FAIL = "fail";
    public static String KEY_DATA = "data";
    public static String KEY_MESSAGE = "message";
    public static String FORMAT = "%02d:%02d";

    //Date And Time Format
    public static String dateFormat = "dd-MM-yyyy";
    public static SimpleDateFormat dateFormatf = new SimpleDateFormat(dateFormat, Locale.US);

    public static String mDateFormatTime = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat simpleDateFormatTimefAPI = new SimpleDateFormat(mDateFormatTime, Locale.US);

    public static String mDateFormatTimeForBlogs = "MMM dd, yyyy";
    public static SimpleDateFormat simpleDateFormatTimefAPIForBlogs = new SimpleDateFormat(mDateFormatTimeForBlogs, Locale.US);

    public static String mDateFormatAPI = "yyyy-MM-dd";
    public static SimpleDateFormat simpleDateFormatfAPI = new SimpleDateFormat(mDateFormatAPI, Locale.US);

    public static String mDateFormat = "dd/MMM/yyyy";
    public static SimpleDateFormat simpleDateFormatf = new SimpleDateFormat(mDateFormat, Locale.US);

    public static String mDateFormatReview = "dd/MMM/yyyy HH:mm";
    public static SimpleDateFormat simpleDateFormatReview = new SimpleDateFormat(mDateFormatReview, Locale.US);

    public static String mFormatTime = "HH:mm:ss";
    public static SimpleDateFormat simpleFormatTimefAPI = new SimpleDateFormat(mFormatTime, Locale.US);

    //Keys
    public static String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static String KEY_OTP = "otp";
    public static String KEY_IS_LOGGED_OUT = "isLoggedOut";
    public static String INTENT_KEY_COMEFROM = "comeFrom";
    public static String INTENT_KEY_URL = "url";
    public static String INTENT_KEY_TITLENAME = "titlename";
    public static String CHECK_MAINTAINANCE_STR = "Maintainance";
    public static String CHECK_PDF_STR = "pdf";
    public static String INTENT_KEY_LOGIN_TC_PRIVACY = "logintcprivacy";
    public static String INVALID_REQUEST = "Invalid Request";
    public static String KEY_MOBILE_NO = "Mobile Number";
}
