package com.space.app.base.tools;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * 对话框工具类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public class ToolAlert extends BaseTool {
    /**
     * 弹出较短的Toast消息
     *
     * @param msg 消息内容
     */
    public static void showShort(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出较长的Toast消息
     *
     * @param msg 消息内容
     */
    public static void showLong(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 弹出自定义时长的Toast消息
     *
     * @param msg 消息内容
     */
    public static void show(Context mContext, String msg, int duration) {
        Toast.makeText(mContext, msg, duration).show();
    }


}
