package com.hrw.njqwtest.api;

import com.hrw.njqwtest.BuildConfig;
import com.hrw.njqwtest.bean.BarrierItemBO;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.bean.TodayLeaderBO;
import com.hrw.njqwtest.base.net.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 14:14
 * @desc:
 */
public interface ApiServer {
    @POST(BuildConfig.UrlNode + "login/getUserByUserName")
    Observable<BaseResponse<LoginUserBO>> getUserByUserName(@Query("userName") String userName);

    @GET(BuildConfig.UrlNode + "login/getZbldInfoByDeptId")
    Observable<BaseResponse<List<TodayLeaderBO>>> getZbldInfoByDeptId(@Query("deptId") String deptId);

    @GET(BuildConfig.UrlNode +"login/getBarrierByPoliceNum")
    Observable<BaseResponse<List<BarrierItemBO>>> getBarrierByPoliceNum(@Query("policeNum") String policeNum);
}
