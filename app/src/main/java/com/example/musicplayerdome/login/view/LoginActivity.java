package com.example.musicplayerdome.login.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.musicplayerdome.R;
import com.example.musicplayerdome.abstractclass.LoginContract;
import com.example.musicplayerdome.base.BaseActivity;
import com.example.musicplayerdome.bean.KeyBean;
import com.example.musicplayerdome.bean.QrCheckBean;
import com.example.musicplayerdome.bean.QrImgBean;
import com.example.musicplayerdome.databinding.ActivityLoginBinding;
import com.example.musicplayerdome.databinding.ActivityLoginQrcodeBinding;
import com.example.musicplayerdome.login.other.LoginPresenter;
import com.example.musicplayerdome.login.bean.LoginBean;
import com.example.musicplayerdome.util.ActivityStarter;
import com.example.musicplayerdome.util.ClickUtil;
import com.example.musicplayerdome.util.InputUtil;
import com.example.musicplayerdome.util.PrivacyUtils;
import com.example.musicplayerdome.util.SettingSPUtils;
import com.example.musicplayerdome.util.SharePreferenceUtil;
import com.example.musicplayerdome.util.XToastUtils;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 登录界面
 * 只能手机号登录
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private static final String TAG = "LoginActivity";
//    ActivityLoginBinding binding;
    ActivityLoginQrcodeBinding binding;
    String phoneNumber;
    String password;


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login_qrcode);
    }

    @Override
    protected void initView(){
        setMargins(binding.title,0,getStatusBarHeight(this),0,0);
//        binding.btnLogin.setOnClickListener(this);
//        binding.register.setOnClickListener(this);
//        binding.forgetPwd.setOnClickListener(this);
//        binding.btnLogin.setOnClickListener(this);
//        binding.register.setOnClickListener(this);
//        binding.forgetPwd.setOnClickListener(this);


        SettingSPUtils spUtils = new SettingSPUtils(LoginActivity.this);
        if (!spUtils.isAgreePrivacy()) {
            PrivacyUtils.showPrivacyDialog(this, (dialog, which) -> {
                dialog.dismiss();
                spUtils.setIsAgreePrivacy(true);
            });
        }
        // 解析二维码
        mPresenter.loginGetQrImg();

    }
    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        setBackBtn(getString(R.string.colorBlack));
        setLeftTitleText(R.string.login_phone_number);
        if (!TextUtils.isEmpty(SharePreferenceUtil.getInstance(this).getAccountNum())) {
            phoneNumber = SharePreferenceUtil.getInstance(this).getAccountNum();
//            binding.etPhone.setText(phoneNumber);
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        //设置状态栏为白底黑字
        ImmersionBar.with(LoginActivity.this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true);
    }

    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick(1000, v)) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_login:
//                phoneNumber = binding.etPhone.getText().toString().trim();
//                password = binding.etPwd.getText().toString().trim();
                if (InputUtil.checkMobileLegal(phoneNumber) && InputUtil.checkPasswordLegal(password)) {
                    showDialog();
                    Log.d(TAG, "login账号密码 : " + phoneNumber + " ," + password);
//                    mPresenter.login(phoneNumber, password);
                    mPresenter.loginGuest();
                }
                break;
            case R.id.register:
            case R.id.forget_pwd:
                XToastUtils.info(R.string.in_developing);
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {
        hideDialog();
        Log.d(TAG, "onLoginSuccess : " + bean);
        SharePreferenceUtil.getInstance(this).saveUserInfo(bean, phoneNumber);
        ActivityStarter.getInstance().startMainActivity(this);
        this.finish();
    }

    @Override
    public void onLoginFail(String error) {
        hideDialog();
        Log.w(TAG, "bean : " + error);
        if (error.equals("HTTP 502 Bad Gateway")) {
            XToastUtils.error(R.string.enter_correct_password);
        } else {
            XToastUtils.error(error);
        }
    }

    @Override
    public void onGetQrImgSuccess(QrImgBean qrImgBean) {
        hideDialog();
        Log.d(TAG,"onGetQrImgSuccess"+qrImgBean);
        // 解析base64编码的二维码
//        String base64String="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";
        String base64String = qrImgBean.getData().getQrimg().split("data:image/png;base64,")[1];
        // 将字符串转换为字节数组
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        // 使用位图工厂将字节数组转换为位图对象
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        binding.ivQrcode.setImageBitmap(decodedByte);


        // 轮询检查是否扫描成功二维码
        mPresenter.loginCheckQrScan();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
