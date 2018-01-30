package com.example.baseactivitysdk.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.baseactivitysdk.base.BasePresenter;
import com.example.baseactivitysdk.base.IBaseFragment;
import com.example.baseactivitysdk.base.IBaseModel;
import com.example.baseactivitysdk.base.IBaseView;
import com.example.baseactivitysdk.base.activity.BaseAppCompatActivity;
import com.example.baseactivitysdk.utils.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by miao on 2018/1/30.
 */

public abstract class BaseMVPCompatFragment<P extends BasePresenter,M extends IBaseModel>
        extends BaseAppCompatFragment implements IBaseFragment {

    private P mPresenter;
    private M mIMode;

    public void initData() {
        super.initData();
        mPresenter= (P) initPresenter();
        if (mPresenter!=null){
            mIMode= (M) mPresenter.getModel();
            if (mIMode!=null){
                mPresenter.attachMV(mIMode,this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachMV();
        }
    }

    @Override
    public void showWaitDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void back() {
        this.onBackPressedSupport();
    }

    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment) {
        start(supportFragment);
    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment) {
        startWithPop(supportFragment);
    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment, int
            requestCode) {
        startForResult(supportFragment, requestCode);
    }

    @Override
    public void popToFragment(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popTo(targetFragmentClass, includeTargetFragment);
    }

    @Override
    public void hidekeybord() {
        hideSoftInput();
    }

    @Override
    public void setOnFragmentResult(int ResultCode, Bundle data) {
        setFragmentResult(ResultCode, data);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        ((BaseAppCompatActivity) mActivity).startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        ((BaseAppCompatActivity) mActivity).startDataActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseAppCompatActivity) mActivity).startDataRequestCode(clz, bundle, requestCode);
    }

    @Override
    public boolean isVisiable() {
        return isSupportVisible();
    }

    @Override
    public Activity getBindActivity() {
        return mActivity;
    }
}
