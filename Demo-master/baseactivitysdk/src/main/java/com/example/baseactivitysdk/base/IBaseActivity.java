package com.example.baseactivitysdk.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by miao on 2018/1/29.
 */

public interface IBaseActivity extends IBaseView{

    void startNewActivity(@NonNull Class<?> cl);

    void startNewDataActivity(@NonNull Class<?> cl, Bundle bundle);

    void startNewDataResustActivity(@NonNull Class<?> cl,Bundle bundle,int Resume);
}
