package com.example.baseactivitysdk.base;

import android.support.annotation.NonNull;

/**
 * Created by miao on 2018/1/29.
 */

public interface IBaseView {
    @NonNull
    BasePresenter initPresenter();

    void showToast(String msg);

    void showWaitDialog(String waitMsg);

    void hideWaitDialog();

    void hidekeybord();

    void back();
}
