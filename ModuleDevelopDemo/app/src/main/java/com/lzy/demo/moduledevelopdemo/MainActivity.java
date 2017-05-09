package com.lzy.demo.moduledevelopdemo;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.lzy.demo.modulea.ModuleAMainActivity;
import com.lzy.demo.moduleb.ModuleBMainActivity;
import com.lzy.demo.moduledevelopdemo.databinding.ActivityMainBinding;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这边的ActivityMainBinding 必需要自己强写   名字规则参考布局文件的名字，并不会自动生成
        final ActivityMainBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_main);


        //这边是绑定用户user
        final User user = new User("", "");
        binding.setUser(user);


        //可以参考布局中实时修改对象里的参数 @{}和@={}的区别
        binding.et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tvFirstname.setText(user.lastName);
            }
        });


        //这边binding就直接能拿到对象了，可以直接对他做点击事件，不建议单独写个类出来
        binding.mainBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ModuleAMainActivity.class));
            }
        });

        binding.mainBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ModuleBMainActivity.class));
            }
        });




    }


}
