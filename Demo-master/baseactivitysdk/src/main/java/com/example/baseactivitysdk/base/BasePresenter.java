package com.example.baseactivitysdk.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.baseactivitysdk.RxManager;

/**
 * Created by miao on 2018/1/29.
 */

public abstract  class BasePresenter<M,V> {
    public M mIModel;
    public V mIView;
    public RxManager rxManager=new RxManager();

    public abstract M getModel();

    /**
     * 绑定
     * @param m
     * @param v
     */
    public void attachMV(@NonNull M m,@NonNull V v){
        this.mIModel=m;
        this.mIView=v;
        this.onStart();
    }

    /**
     * 解绑
     */
    public void detachMV(){
        rxManager.unSubscribe();
        mIModel=null;
        mIView=null;
    }

    /**
     * 绑定后立即初始化
     */
    protected abstract void onStart();

}
