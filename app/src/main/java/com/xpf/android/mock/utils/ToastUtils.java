package com.xpf.android.mock.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by x-sir on 2018/8/2 :)
 * Function: Toast utils.
 */
public class ToastUtils {

    /**
     * toast short.
     *
     * @param msg
     */
    public static void showShort(Context context, String msg) {
        if (context == null) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast long.
     *
     * @param msg
     */
    public static void showLong(Context context, String msg) {
        if (context == null) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
