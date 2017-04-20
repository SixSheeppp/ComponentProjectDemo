package com.lzy.demo.library;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;


/**
 * ProgressDialog工具类
 * Created by zhuofeng on 2015/4/8.
 */
public class VCProgressDialog {

    private static ProgressDialog progressDialog;

    public static ProgressDialog createProgressDialog(Context paramContext, String text) {
        progressDialog = new ProgressDialog(paramContext);
        if (text == null || "".equals(text))
            text = "Loading...";
        progressDialog.setMessage(text);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void dismiss() {
        if (progressDialog == null || !isShow())
            return;
        progressDialog.dismiss();
        progressDialog = null;
    }

    public static ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public static boolean isShow() {
        if (progressDialog != null) {
            boolean bool = progressDialog.isShowing();
            return bool;
        }
        return false;
    }

    /**
     * 展示loading
     *
     * @param paramContext
     * @param paramBoolean 是否可以手动取消
     * @param text         dialog message
     */
    public static void show(Context paramContext, String text, boolean paramBoolean) {

        dismiss();

        progressDialog = createProgressDialog(paramContext, text);
        progressDialog.setCancelable(paramBoolean);
        progressDialog.show();

        ProgressDialogSetting(progressDialog);
    }

    /**
     * 展示loading
     *
     * @param paramContext
     * @param text         dialog message
     */
    public static ProgressDialog show(Context paramContext, String text) {

        dismiss();

        progressDialog = createProgressDialog(paramContext, text);
        progressDialog.show();

        ProgressDialogSetting(progressDialog);
        return progressDialog;
    }


    public static void ProgressDialogSetting(ProgressDialog progressDialog) {

        WindowManager.LayoutParams localLayoutParams = progressDialog.getWindow().getAttributes();
        localLayoutParams.dimAmount = 0.4F;
        progressDialog.getWindow().setAttributes(localLayoutParams);
        progressDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progressDialog.setContentView(R.layout.loading);
    }
}
