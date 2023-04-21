package com.example.alokozapshopapplication.ui.constant


import com.example.alokozapshopapplication.BuildConfig

/**
 * Created by rajanbhavsar on 21/11/19, 7:13 PM
 * Package Name : com.appname.structure.constant
 * Project Name : BrainvireStructure
 */
object Constant {

    const val CAMERA_INTENT = 1001
    val ONBOARD: String = "ONBOARD"
    val IS_FIRST_TIME_LAUNCH: String = "on_board_screen"
    val MORE: String = "More"
    val LANGUAGE_CODE: String = "language_code"
    val CMS_TITLE: String = "cms-title"
    val CMS_URL: String = "cms-url"
    const val EN_CODE = "en"
    const val AR_CODE = "ar"
    const val SCREEN_TAG = "SCREEN_TAG"
    val PREFERENCE_NAME = BuildConfig.APPLICATION_ID
//    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    const val PAGE = 3
    const val FIRST_PAGE = 0

    const val MEDIA_TYPE_VIDEO = "video"
    const val MEDIA_TYPE_YOUTUBE = "youtube"

    const val mLocationInterval: Long = 2000
    const val mLocationFastestInterval: Long = 1000
    const val mLocationPriority: Int = 100
    const val USER_EXITS_YES = "yes"
    const val USER_EXITS_NO = "no"
    const val RESEND_OTP = "Yes"
    const val OTP = "otp"
    const val USER_DOESNT_EXIST = "USER_DOESNT_EXIST"
    const val USER_ACCOUNT_PENDING = "USER_ACCOUNT_PENDING"
    const val EMAIL_ID = "emailId"
    const val COME_FROM = "comeFrom"
    const val FORGOT_PASSWORD_SCREEN = "comeFromForgotPassWord"
    const val REGISTRATION_SCREEN = "comeFromRegistration"
    const val LOGIN_SCREEN = "comeFromLogin"
    const val SOCIAL_LOGIN = "yes"
    const val SOCIAL_ID = "socialId"
    const val COUNTRY = "country"
    const val SOCIAL_GOOGLE = "google"
    const val SOCIAL_FACEBOOK = "facebook"
    const val SOCIAL_GOOGLE_FACEBOOK = "googleOrFacebook"
    const val SOCIAL_LOGIN_DATA = "userSocialLoginData"
}