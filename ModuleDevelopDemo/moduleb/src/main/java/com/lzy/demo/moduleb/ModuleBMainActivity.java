package com.lzy.demo.moduleb;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.lzy.demo.library.ACache;
import com.lzy.demo.library.CameraActivity;
import com.lzy.demo.library.CommonUtils;
import com.lzy.demo.library.base.BaseActivity;
import com.lzy.demo.moduleb.databinding.ModulebActivityMainBinding;

import java.io.IOException;

public class ModuleBMainActivity extends BaseActivity {

    private ModulebActivityMainBinding mModulebBinding;
    private String base64 = "";
    //常量定义
    public static final int SELECT_A_PICTURE              = 20;
    public static final int SELECET_A_PICTURE_AFTER_KIKAT = 50;
    private UserInfo mInfo;
    private ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取acache的实例
        mACache = ACache.get(this);
        //根据userid来读取对象
        UserInfo info = (UserInfo) mACache.getAsObject("userid");
        if (info == null){
            mInfo = new UserInfo();
        }else{
            mInfo = info;
        }
        mModulebBinding = DataBindingUtil.setContentView(this, R.layout.moduleb_activity_main);
        mModulebBinding.setUserinfo(mInfo);
        //这边用来测试双向绑定
        mModulebBinding.etModuleb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("LZY", "onTextChanged: "+ mInfo.getUsername());

            }
            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        //调用相机拍照
        mModulebBinding.modulebBtTakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ModuleBMainActivity.this, CameraActivity.class), 201);
            }
        });


        //保存acache
        mModulebBinding.modulebBtSavedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mACache.put("userid",mInfo);
            }
        });

        //读取acache
        mModulebBinding.modulebBtGetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo info = (UserInfo) mACache.getAsObject("userid");
                if (info!=null){
                    //这边读取出来的数据每个都需要做判空，因为存储的时候是直接trycatch住了
                    String userImg = info.getUserImg();
                    Bitmap bitmap = CommonUtils.base64toBitmap(userImg);
                    byte[] aByte = CommonUtils.getByte(bitmap);
                    Glide.with(ModuleBMainActivity.this).load(aByte).into(mModulebBinding.modulebIv);
                    //这边对象里面的username是用了databinding的双向绑定
                    mModulebBinding.modulebTv.setText(info.getUsername());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 40002 && data != null) {
            //这是调用相机返回的方法
            Bundle extras = data.getExtras();
            String imgPath = extras.getString("ImgPath");
            base64 = CommonUtils.imgToBase64(imgPath);
            Bitmap bitmap = CommonUtils.base64toBitmap(base64);
            int bitmapDegree = getBitmapDegree(imgPath);
            Bitmap bitmap1 = rotateBitmapByDegree(bitmap, bitmapDegree);
            String s = CommonUtils.bitmapToBase64(bitmap1);

            mInfo.setUserImg(s);
        }

    }








    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }


}
