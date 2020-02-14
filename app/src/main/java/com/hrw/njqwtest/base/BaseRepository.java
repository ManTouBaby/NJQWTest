package com.hrw.njqwtest.base;

import com.hrw.njqwtest.base.net.BaseSubscribe;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/18 18:47
 * @desc:
 */
public class BaseRepository {
    protected BaseSubscribe mSubscribe;

    public BaseRepository() {
        mSubscribe = new BaseSubscribe();
    }
}
