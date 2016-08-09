package com.android.secret.phabricatordemo.util;

import android.app.Application;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by secret on 16/8/10.
 * singleton
 */
public class PatchUtil {

    private PatchManager patchManager;

    private static PatchUtil patchUtil;

    private static final String APATCH_PATH = "/out.apatch";

    private PatchUtil(Application application) {
        patchManager = new PatchManager(application);
        patchManager.init("1.0");
    }

    /**
     * get singleton instance of {@link PatchUtil}
     * @param application
     * @return
     */
    public static PatchUtil getInstance(Application application) {
        if (null == patchUtil) {
            patchUtil = new PatchUtil(application);
        }

        return patchUtil;
    }

    /**
     * load .apatch file
     */
    public void loadPatch() {
        if (null != patchManager)
            patchManager.loadPatch();
    }

    /**
     * add .apatch file
     */
    public void addPatch() {

        if (null != patchManager) {

            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;

            try {
                patchManager.addPatch(patchFileString);
                this.deleteApatchFile(patchFileString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * delete file after addPatch successfully
     * @param patchFileString file path
     * @return true if delete successfully or false
     */
    private boolean deleteApatchFile(String patchFileString) {
        //add successfully and delete .apatch file in sdcard
        File file = new File(patchFileString);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

}
