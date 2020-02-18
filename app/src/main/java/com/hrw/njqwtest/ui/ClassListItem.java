package com.hrw.njqwtest.ui;

import com.hrw.njqwtest.base.wdiget.recyclerview.SmartMultipleBean;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/17 10:45
 * @desc:
 */
public class ClassListItem extends SmartMultipleBean {
    public String name;
    public String age;

    public ClassListItem(String name, String age, int itemType) {
        this.name = name;
        this.age = age;
        this.itemType = itemType;
    }
}
