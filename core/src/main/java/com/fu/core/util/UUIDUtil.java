package com.fu.core.util;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
