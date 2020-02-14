package com.hrw.njqwtest.ui.flash;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseActivity;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.utils.SPUtil;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.ui.main.MainActivity;
import com.hrw.njqwtest.viewmodel.model.LoginUserModel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 14:02
 * @desc:
 */
public class FlashActivity extends BaseActivity {
    private LoginUserModel mLoginUserModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //得到当前界面的装饰视图
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_flash);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        mLoginUserModel = ViewModelProviders.of(this).get(LoginUserModel.class);

        mLoginUserModel.getLoginUserLiveData().observe(this, new Observer<LoginUserBO>() {
            @Override
            public void onChanged(LoginUserBO userBO) {
//                goTo(MainActivity.class);
                System.out.println(userBO.getPoliceName());

            }
        });
    }

    @Override
    protected void getDates() {
        mLoginUserModel.loginUser("shiju", new ResponseListener<LoginUserBO>() {
            @Override
            public void onDataSuccess(LoginUserBO data, String msg) {
                SPUtil.MANAGER().putObject("LOGIN_USER", data);
                finish();
                goTo(MainActivity.class);
            }

            @Override
            public void onNetFault(String errorMsg, int errorCode) {

            }

            @Override
            public void onDataFault(String errorMsg, int errorCode) {

            }
        });
    }
}
