package com.cj.tangtuan.utils;

import java.util.UUID;

/**
 * UUIDCreator
 *
 * @author 何辉
 * @date 2016/7/12
 */
public class UUIDCreator {
    public static final String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
