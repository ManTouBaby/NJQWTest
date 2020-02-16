package com.hrw.njqwtest.ui.flash;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseViewModelActivity;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.utils.SPHelper;
import com.hrw.njqwtest.base.utils.ToastHelper;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.databinding.ActivityFlashBinding;
import com.hrw.njqwtest.ui.main.MainActivity;
import com.hrw.njqwtest.viewmodel.model.LoginUserModel;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 14:02
 * @desc:
 */
public class FlashActivity extends BaseViewModelActivity<LoginUserModel, ActivityFlashBinding> {
    Handler handler =new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //得到当前界面的装饰视图
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


    }

    @Override
    protected Class<LoginUserModel> returnClass() {
        return LoginUserModel.class;
    }

    @Override
    protected int getResLayout() {
        return R.layout.activity_flash;
    }

    @Override
    protected void init() {

        mViewModel.getLoginUserLiveData().observe(this, new Observer<LoginUserBO>() {
            @Override
            public void onChanged(LoginUserBO userBO) {
//                goTo(MainActivity.class);
                System.out.println(userBO.getPoliceName());
            }
        });
    }

    @Override
    protected void getDates() {
        mViewModel.loginUser("shiju", new ResponseListener<LoginUserBO>() {
            @Override
            public void onDataSuccess(LoginUserBO data, String msg) {
                SPHelper.getInstance().manager.putObject("LOGIN_USER", data);
                finish();
                goTo(MainActivity.class);
            }

            @Override
            public void onNetFault(String errorMsg, int errorCode) {
//                ToastHelper.showShort(getContext(), "onNetFault==" + errorMsg);
                closeFlash(errorMsg);
            }

            @Override
            public void onDataFault(String errorMsg, int errorCode) {
//                ToastHelper.showShort(getContext(), "onDataFault==" + errorMsg);
                closeFlash(errorMsg);
            }
        });
    }

    private void closeFlash(final String msg) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastHelper.showShort(getContext(), msg+"\r\n程序将在3S后退出" );
                        finish();
                    }
                });
            }
        }, 3000);
    }
}
