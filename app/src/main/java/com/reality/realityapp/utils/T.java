package com.reality.realityapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xkl on 17/10/23.
 */
public class T {

    private static Toast toast;

    public static void showToast(
            String content) {
        toast.setText(content);
        toast.show();
    }

    public static void init(Context context) {
        toast = Toast.makeText(context,
                "",
                Toast.LENGTH_SHORT);
    }
}
