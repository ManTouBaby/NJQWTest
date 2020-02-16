package com.hrw.njqwtest.viewmodel.model;

import com.hrw.njqwtest.base.BaseViewModel;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.viewmodel.net.LoginRepository;

import androidx.lifecycle.MutableLiveData;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:55
 * @desc:
 */
public class LoginUserModel extends BaseViewModel {
    private MutableLiveData<LoginUserBO> loginUserLiveData;

    private LoginRepository mLoginRepository;

    public LoginUserModel() {
        loginUserLiveData = new MutableLiveData<>();
        mLoginRepository = new LoginRepository();
    }

    public MutableLiveData<LoginUserBO> getLoginUserLiveData() {
        return loginUserLiveData;
    }

    private void setLoginUserLiveData(LoginUserBO loginUserBO) {
        this.loginUserLiveData.setValue(loginUserBO);
    }

    public void loginUser(String userName, ResponseListener<LoginUserBO> listener) {
        mLoginRepository.loginUser(userName, listener);
    }
}
