/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.xpf.android.mock.mock;

import com.xpf.android.mock.utils.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by x-sir on 2019/1/23 :)
 * Function:
 */
public class Mocker {

    private static final String TAG = "Mocker";
    private static Map<String, Method> methodMap = new HashMap<>();
    private static Class mService;

    private Mocker() {

    }

    public static Mocker create(Class service) {
        mService = service;
        initMethods(service);
        return new Mocker();
    }

    private static void initMethods(Class service) {
        for (Method method : service.getDeclaredMethods()) {
            MOCK mock = method.getAnnotation(MOCK.class);
            if (mock != null) {
                methodMap.put(mock.value(), method);
            }
        }
        LogUtils.d(TAG, "Mocker methods:" + methodMap.toString());
    }

    private String mockKey(Request request) {
        String path = request.url().uri().getPath();
        LogUtils.i(TAG, "path===" + path);
        for (String key : methodMap.keySet()) {
            if (path.equals(key)) {
                return key;
            }
        }

        return null;
    }

    public Response mockResult(Request request) {
        String key = mockKey(request);
        if (key == null) {
            return null;
        }

        MockResult result = getResult(key, request);

        if (result == null) {
            return null;
        }

        return result.getResponse();
    }

    public MockResult getResult(String key, Request request) {
        Method method = methodMap.get(key);
        MockResult result = null;
        try {
            result = (MockResult) method.invoke(mService.newInstance(), request);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return result;
    }
}


