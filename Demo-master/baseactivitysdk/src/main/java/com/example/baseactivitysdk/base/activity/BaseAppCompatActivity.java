package com.example.baseactivitysdk.base.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.example.baseactivitysdk.R;
import com.example.baseactivitysdk.global.GlobalApplication;
import com.example.baseactivitysdk.utils.AppUtils;
import com.example.baseactivitysdk.utils.StatusBarUtils;
import com.example.baseactivitysdk.widget.WaitPorgressDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/26.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private GlobalApplication mApplication;
    public WaitPorgressDialog mWaitPorgressDialog;
    private int layoutId;
    private Context mContext;
    private boolean isTransAnim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        setContentView(getlayoutId());
        StatusBarUtils.setTransparent(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mWaitPorgressDialog = new WaitPorgressDialog(this);
        ButterKnife.bind(this);
        initData();
        initView(savedInstanceState);
    }

    protected void initData() {
        mApplication = (GlobalApplication) getApplication();
        mContext = AppUtils.getContext();
        isTransAnim = true;
    }

    public abstract void startNewDataActivity(@NonNull Class<?> cl, Build build);

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getlayoutId();

    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
        if (isTransAnim) {
            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim.activity_start_zoom_out);
        }
    }


    public void startDataActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startDataRequestCode(Class<?> clz, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void showProgressDialog(String waitDialog) {
        mWaitPorgressDialog.setMessage(waitDialog);
        mWaitPorgressDialog.show();
    }

    public void hideProgressDialog() {
        mWaitPorgressDialog.hide();
    }

    /**
     * 隐藏键盘
     *
     * @return 隐藏键盘结果
     * <p>
     * true:隐藏成功
     * <p>
     * false:隐藏失败
     */
    protected boolean hiddenKeyboard() {
        //点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        return mInputMethodManager.hideSoftInputFromWindow(this
                .getCurrentFocus().getWindowToken(), 0);
    }

}
