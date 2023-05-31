package com.example.musicplayerdome.login.other;



import com.example.musicplayerdome.abstractclass.LoginContract;
import com.example.musicplayerdome.api.ApiEngine;
import com.example.musicplayerdome.bean.KeyBean;
import com.example.musicplayerdome.bean.QrCheckBean;
import com.example.musicplayerdome.bean.QrImgBean;
import com.example.musicplayerdome.login.bean.LoginBean;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<LoginBean> login(String phone, String password) {
        return ApiEngine.getInstance().getApiService().login(phone, password);
    }

    @Override
    public Observable<LoginBean> loginGuest() {
        return ApiEngine.getInstance().getApiService().loginGuest();
    }

    // 二维码key生成
    @Override
    public Observable<KeyBean> loginGetQrKey(long timestamp) {
        return ApiEngine.getInstance().getApiService().loginGetQrKey(timestamp);
    }
    // 二维码生成接口
    @Override
    public Observable<QrImgBean> loginCreateQrImg(String key, long timestamp, Boolean qrimg) {
        return ApiEngine.getInstance().getApiService().loginCreateQrImg(key, timestamp, qrimg);
    }

    // 二维码检测扫码状态接口
    @Override
    public Observable<QrCheckBean> loginCheckQr(String key, long timestamp, Boolean noCookie) {
        return ApiEngine.getInstance().getApiService().loginCheckQr(key, timestamp, noCookie);
    }

    @Override
    public Observable<LoginBean> getLoginStatus(String cookie) {
        return ApiEngine.getInstance().getApiService().getLoginStatus(cookie);
    }
}
