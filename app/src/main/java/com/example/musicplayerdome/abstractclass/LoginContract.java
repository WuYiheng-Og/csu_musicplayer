package com.example.musicplayerdome.abstractclass;


import com.example.musicplayerdome.base.BasePresenter;
import com.example.musicplayerdome.bean.KeyBean;
import com.example.musicplayerdome.bean.QrCheckBean;
import com.example.musicplayerdome.bean.QrImgBean;
import com.example.musicplayerdome.login.bean.LoginBean;

import io.reactivex.Observable;

public interface LoginContract {

    interface Model extends BaseModel {
        Observable<LoginBean> login(String phone, String password);

        Observable<LoginBean> loginGuest();

        Observable<KeyBean> loginGetQrKey(long timestamp);
        Observable<QrImgBean> loginCreateQrImg(String key, long timestamp, Boolean qrimg);
        Observable<QrCheckBean> loginCheckQr(String key, long timestamp, Boolean noCookie);

        Observable<LoginBean>  getLoginStatus(String cookie);
    }

    interface View extends BaseView {

        
        void onLoginSuccess(LoginBean bean);

        void onLoginFail(String e);

        void onGetQrImgSuccess(QrImgBean qrImgBean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String phone, String password);
    }
}
