package com.lzy.demo.moduleb;


import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.lzy.demo.library.base.BaseActivity;
import com.lzy.demo.moduleb.databinding.ModulebActivityMainBinding;

public class ModuleBMainActivity extends BaseActivity {

    private ModulebActivityMainBinding mModulebBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModulebBinding = DataBindingUtil.setContentView(this, R.layout.moduleb_activity_main);
        // TODO: 2017/5/8 这边明天测试acache，
    }
}
