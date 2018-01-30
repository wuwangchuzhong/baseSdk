package com.example.baseactivitysdk.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by miao on 2018/1/29.
 */

public class WaitPorgressDialog extends android.app.ProgressDialog {

    public WaitPorgressDialog(Context context) {
        super(context);
    }

    public WaitPorgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }
}
