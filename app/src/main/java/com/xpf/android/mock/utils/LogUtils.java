package com.xpf.android.mock.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.xpf.android.mock.BuildConfig;

import java.util.Collection;

/**
 * Created by x-sir on 2018-07-31 :)
 * Function:logger printer.
 */
public class LogUtils {

    private static final String TAG = "LogUtils";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void iLogging(String msg) {
        if (DEBUG) {
            Logger.i(msg);
        }
    }

    public static void iLogging(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).i(msg);
        }
    }

    public static void wLogging(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).w(msg);
        }
    }

    public static void wLogging(String msg) {
        if (DEBUG) {
            Logger.w(msg);
        }
    }

    public static void eLogging(String msg) {
        if (DEBUG) {
            Logger.e(msg);
        }
    }

    public static void eLogging(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).e(msg);
        }
    }

    public static void vLogging(String msg) {
        if (DEBUG) {
            Logger.v(msg);
        }
    }

    public static void vLogging(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).v(msg);
        }
    }

    public static void dLogging(String msg) {
        if (DEBUG) {
            Logger.d(msg);
        }
    }

    public static void dLogging(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).d(msg);
        }
    }

    public static void json(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).json(msg);
        }
    }

    public static void json(String msg) {
        if (DEBUG) {
            Logger.json(msg);
        }
    }

    public static void xml(String msg) {
        if (DEBUG) {
            Logger.xml(msg);
        }
    }

    public static void xml(String tag, String msg) {
        if (DEBUG) {
            Logger.t(tag).xml(msg);
        }
    }

    public static void collection(Collection<?> collection) {
        if (DEBUG) {
            Logger.d(collection);
        }
    }

    public static void collection(String tag, Collection<?> collection) {
        if (DEBUG) {
            Logger.t(tag).d(collection);
        }
    }
}
