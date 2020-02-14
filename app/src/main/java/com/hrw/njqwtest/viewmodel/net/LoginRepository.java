package com.hrw.njqwtest.viewmodel.net;

import com.hrw.njqwtest.api.ApiServer;
import com.hrw.njqwtest.base.BaseRepository;
import com.hrw.njqwtest.base.net.BaseSubscribe;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.net.RetrofitManager;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/19 9:06
 * @desc:
 */
public class LoginRepository extends BaseRepository {
    public void loginUser(String userName, ResponseListener listener) {
        ApiServer api = RetrofitManager.getInstance().create(ApiServer.class);
        final BaseSubscribe subscribe = new BaseSubscribe();
        subscribe.toDetachAndSubscribe(api.getUserByUserName(userName), listener);
    }
}
