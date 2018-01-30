package com.example.baseactivitysdk.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseactivitysdk.global.GlobalApplication;
import com.example.baseactivitysdk.utils.AppUtils;
import com.example.baseactivitysdk.widget.WaitPorgressDialog;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by miao on 2018/1/30.
 */

public abstract class BaseAppCompatFragment extends SupportFragment {

    public Context mContext;
    public Activity mActivity;
    public WaitPorgressDialog mWaitPorgressDialog;
    public GlobalApplication mApplication;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        mActivity = (Activity) context;
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getlayoutId(),container,false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getBundle(getArguments());
        initData();
        initView(view,savedInstanceState);
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    public void initData() {
        mWaitPorgressDialog =new WaitPorgressDialog(mActivity);
        mApplication= (GlobalApplication) mActivity.getApplication();
        mContext= AppUtils.getContext();
    }

    public void getBundle(Bundle arguments) {

    }

    public View getLayoutView() {
        return null;
    }

    protected abstract int getlayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void showProgressDialog(String msg) {
        if (mWaitPorgressDialog.isShowing()) {
            mWaitPorgressDialog.dismiss();
        }

        mWaitPorgressDialog.setMessage(msg);
        mWaitPorgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mWaitPorgressDialog != null) {
            mWaitPorgressDialog.dismiss();
        }
    }

}
