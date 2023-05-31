package com.example.musicplayerdome.login.other;


import android.util.Log;

import com.example.musicplayerdome.abstractclass.LoginContract;
import com.example.musicplayerdome.bean.KeyBean;
import com.example.musicplayerdome.bean.QrCheckBean;
import com.example.musicplayerdome.bean.QrImgBean;
import com.example.musicplayerdome.login.bean.LoginBean;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter extends LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        this.mModel = new LoginModel();
    }

    @Override
    public void login(String phone, String password) {
        mModel.login(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        mView.onLoginSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "登录请求错误 : 请检查api文件夹中的ApiService的BASE_URL地址是否正确，如果是使用本地服务，请确保在同一个wift（不能是手机端的热点）下");
                        mView.onLoginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete!");
                    }
                });
    }

    public void loginGuest() {
        mModel.loginGuest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        mView.onLoginSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "登录请求错误 : 请检查api文件夹中的ApiService的BASE_URL地址是否正确，如果是使用本地服务，请确保在同一个wift（不能是手机端的热点）下");
                        mView.onLoginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete!");
                    }
                });
    }

    // 二维码验证的key
    private String key;

    // 1.二维码key生成
    public void loginGetQrImg() {
        mModel.loginGetQrKey(System.currentTimeMillis())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<KeyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(KeyBean keyBean) {
                        Log.d(TAG, "onNext: "+keyBean);
                        key = keyBean.getData().getUnikey();
                        mModel.loginCreateQrImg(key, System.currentTimeMillis(), true)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<QrImgBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        Log.d(TAG, "onSubscribe");
                                    }

                                    @Override
                                    public void onNext(QrImgBean qrImgBean) {
                                        Log.d(TAG, "onNext: "+qrImgBean);
                                        mView.onGetQrImgSuccess(qrImgBean);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e(TAG, "二维码图片获取失败 : 请检查api文件夹中的ApiService的BASE_URL地址是否正确，如果是使用本地服务，请确保在同一个wift（不能是手机端的热点）下");
                                        mView.onLoginFail(e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "二维码key获取失败 : 请检查api文件夹中的ApiService的BASE_URL地址是否正确，如果是使用本地服务，请确保在同一个wift（不能是手机端的热点）下");
                        mView.onLoginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete!");
                    }
                });
    }

    public void loginCheckQrScan() {
        mModel.loginCheckQr(key, System.currentTimeMillis(), true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QrCheckBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(QrCheckBean bean) {
//                        Log.d(TAG, "onNext: "+bean);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Log.d(TAG, "状态响应码: " + bean.getCode());
                                int code = bean.getCode();
                                if (code == 800) {
                                    mView.onLoginFail("二维码已过期，请重新获取");
                                    timer.cancel();// 清除定时器
                                }
                                if (code == 803) {
                                    timer.cancel();
                                    // 获取当前登录状态
                                    mModel.getLoginStatus(bean.getCookie())
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Observer<LoginBean>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {

                                                }

                                                @Override
                                                public void onNext(LoginBean loginBean) {
                                                    Log.d(TAG, "onNext: " + loginBean);
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Log.e(TAG, "登录状态获取失败 : 请检查api文件夹中的ApiService的BASE_URL地址是否正确，如果是使用本地服务，请确保在同一个wift（不能是手机端的热点）下");
                                                    mView.onLoginFail(e.getMessage());
                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            });

                                }
                            }
                        },5000, 5000);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "二维码检查失败 : 请检查api文件夹中的ApiService的BASE_URL地址是否正确，如果是使用本地服务，请确保在同一个wift（不能是手机端的热点）下");
                        mView.onLoginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete!");
                    }
                });
    }
}
