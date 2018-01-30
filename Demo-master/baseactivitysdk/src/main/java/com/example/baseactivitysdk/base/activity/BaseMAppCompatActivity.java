package com.example.baseactivitysdk.base.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.baseactivitysdk.base.BasePresenter;
import com.example.baseactivitysdk.base.IBaseActivity;
import com.example.baseactivitysdk.base.IBaseView;
import com.example.baseactivitysdk.utils.ToastUtils;

/**
 * Created by miao on 2018/1/26.
 */

public abstract class BaseMAppCompatActivity<P extends BasePresenter,M extends IBaseView> extends
        BaseAppCompatActivity implements IBaseActivity {

    protected P mPresenter;
    protected M mModel;

    @Override
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter!=null){
            mModel= (M) mPresenter.getModel();
            if (mModel!=null){
                mPresenter.attachMV(mModel,this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachMV();
        }
    }

    @Override
    public void startNewActivity(@NonNull Class<?> cl) {
        startActivity(cl);
    }

    @Override
    public void startNewDataActivity(@NonNull Class<?> cl, Bundle bundle) {
       startDataActivity(cl,bundle);
    }

    @Override
    public void startNewDataResustActivity(@NonNull Class<?> cl, Bundle bundle, int Resume) {
       startDataRequestCode(cl,bundle,Resume);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getlayoutId() {
        return 0;
    }

    public void  showToast(String msg){
        ToastUtils.showToast(msg);
    }

    public void showWaitDialog(String waitDialog){
        showProgressDialog(waitDialog);
    }

    public void hideWaitDialog(){
        hideProgressDialog();
    }

    public void hidekeybord(){
        hiddenKeyboard();
    }

    public void back(){
        super.onBackPressed();
    }
}
