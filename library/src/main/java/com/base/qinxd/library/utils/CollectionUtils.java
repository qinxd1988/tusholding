package com.base.qinxd.library.utils;

import java.util.List;

/**
 * Created by xd on 2017/4/25.
 */

public class CollectionUtils {

    public static boolean isListEmpty(List list) {

        if (list == null || list.size() == 0) {

            return true;

        }

        return false;

    }

}
