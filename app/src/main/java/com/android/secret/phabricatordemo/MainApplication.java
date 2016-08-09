package com.android.secret.phabricatordemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.android.secret.phabricatordemo.util.PatchUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by secret on 16/8/8.
 */
public class MainApplication extends Application {
    private static final String TAG = "euler";

    private PatchUtil patchUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        patchUtil = PatchUtil.getInstance(this);
        patchUtil.loadPatch();
        patchUtil.addPatch();
    }

}
