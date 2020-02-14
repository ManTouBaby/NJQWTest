package com.hrw.njqwtest.viewmodel.net;

import com.hrw.njqwtest.api.ApiServer;
import com.hrw.njqwtest.base.BaseRepository;
import com.hrw.njqwtest.base.net.BaseSubscribe;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.net.RetrofitManager;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/19 8:53
 * @desc:
 */
public class TodayLeaderRepository extends BaseRepository {

    public void getZbldInfoByDeptId(String deptId, ResponseListener listener) {
        ApiServer api = RetrofitManager.getInstance().create(ApiServer.class);
        final BaseSubscribe subscribe = new BaseSubscribe();
        subscribe.toDetachAndSubscribe(api.getZbldInfoByDeptId(deptId), listener);
    }
}
