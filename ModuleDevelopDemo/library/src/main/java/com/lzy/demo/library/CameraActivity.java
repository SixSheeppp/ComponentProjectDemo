package com.lzy.demo.library;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import com.lzy.demo.library.base.BaseActivity;
import com.lzy.demo.library.databinding.CameraActivityLayoutBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 调用相机拍照
 * <p/>
 */
public class CameraActivity extends BaseActivity implements View.OnClickListener, SurfaceHolder.Callback {
    //    @Bind(R.)
    //    SurfaceView surfaceView;
    //    @Bind(R.id.camera_txt_enter)
    //    TextView    txtEnter;
    //    @Bind(R.id.camera_img_takepicture)
    //    ImageView   imgTakepicture;
    //    @Bind(R.id.camera_txt_cancel)
    //    TextView    txtCancel;
    //    @Bind(R.id.camera_img_cameraTrans)
    //    ImageView   imgCameraTrans;
    private String savePath = "/moduledevelop/";
    private int    IS_TOOK  = 0;//是否已经拍照 ,0为否
    private Camera mcamera;
    private Camera.Parameters parameters = null;
    private Bundle            bundle     = null;// 声明一个Bundle对象，用来存储数据
    private Intent intent;
    private boolean startFontCamera = true;
    private SurfaceHolder               mHolder;
    private CameraActivityLayoutBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.camera_activity_layout);
        mBinding = DataBindingUtil.setContentView(this, R.layout.camera_activity_layout);
        checkCamearPermission();
        //init();
    }

    @Override
    public void instantiation() {

        super.instantiation();
        intent = getIntent();
        mHolder = mBinding.cameraSurfaceView.getHolder();
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.setKeepScreenOn(true);// 屏幕常亮
        mHolder.addCallback(this);// 为SurfaceView的句柄添加一个回调函数
        mBinding.cameraTxtEnter.setVisibility(View.INVISIBLE);
        if (this.checkCameraHardware(this) && (mcamera == null)) {
            // 打开camera
            mcamera = getCamera();
            if (mHolder != null) {
                setStartPreview(mcamera, mHolder);
            }
        }
        mBinding.cameraTxtCancel.setVisibility(View.INVISIBLE);
    }

    //这边加一个check permission,适配6。0
    public void checkCamearPermission() {
        //只有api大于23才需要申请
        if (Build.VERSION.SDK_INT >= 23) {
            //权限列表
            String[] permissions = new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            //判断相机是否已经申请权限
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                init();
            } else {
                requestPermissions(permissions, 200);
            }

        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && PackageManager.PERMISSION_GRANTED == grantResults[0]
                && PackageManager.PERMISSION_GRANTED == grantResults[1]
                && PackageManager.PERMISSION_GRANTED == grantResults[2]) {
            init();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void databind() {
        super.databind();
    }

    @Override
    public void eventbind() {
        super.eventbind();
        mBinding.cameraTxtEnter.setOnClickListener(this);
        mBinding.cameraImgTakepicture.setOnClickListener(this);
        mBinding.cameraTxtCancel.setOnClickListener(this);
        mBinding.cameraImgCameraTrans.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.camera_img_takepicture) {
            // 拍照
            if (IS_TOOK == 0) {
                if (mcamera != null) {
                    try {
                        mcamera.takePicture(null, null, new MyPictureCallback());
                        mBinding.cameraImgCameraTrans.setVisibility(View.GONE);
                        IS_TOOK = 1;
                    } catch (Exception e) {

                    }
                    //                        mcamera.takePicture(null, null, new MyPictureCallback());
                    //                        IS_TOOK = 1;
                }
            }
        } else if (id == R.id.camera_txt_enter) {
            if (IS_TOOK == 1) {
                if (bundle == null) {
                    Toast.makeText(getApplicationContext(), "bundle null",
                            Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (isHaveSDCard())
                            saveToSDCard(bundle.getByteArray("bytes"));
                    } catch (IOException e) {
                        Toast.makeText(this, "无法读取所拍照片，请检查是否开启了相关权限", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }

                }
            }
        } else if (id == R.id.camera_txt_cancel) {
            //  取消

            mBinding.cameraImgTakepicture.setVisibility(View.VISIBLE);
            //                txtCancel.setVisibility(View.INVISIBLE);
            //                txtEnter.setVisibility(View.INVISIBLE);
            if (IS_TOOK == 1) {
                try {
                    if (mcamera != null) {
                        IS_TOOK = 0;

                        //需重新配置自动对焦
                        mcamera.cancelAutoFocus();//只有加上了这一句，才会自动对焦。
                        parameters = mcamera.getParameters(); // 获取各项参数
                        //矫正拍照之后图片的旋转的角度
                        parameters.setRotation(getPreviewDegree(CameraActivity.this));
                        if (startFontCamera) {
                            //连续对焦
                            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                        }
                        mcamera.setParameters(parameters);
                        //                        txtCancel.setText("取消");
                        mBinding.cameraTxtEnter.setVisibility(View.INVISIBLE);
                        mBinding.cameraTxtCancel.setVisibility(View.INVISIBLE);
                        mcamera.startPreview();
                        mBinding.cameraImgCameraTrans.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {

                }
            } else {
                finish();
            }

        } else if (id == R.id.camera_img_cameraTrans) {
            CameraTrans(mHolder);
        }


    }

    /**
     * 设置camera显示取景画面,并预览
     *
     * @param camera
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            if (null != camera) {
                // 通过surfaceview显示取景画面
                parameters = camera.getParameters(); // 获取各项参数
                //矫正拍照之后图片的旋转的角度
                if (!startFontCamera) {
                    parameters.setRotation(270);
                } else {
                    parameters.setRotation(getPreviewDegree(CameraActivity.this));
                }
                //            Size largestSize = getBestSupportedSize(parameters
                //                    .getSupportedPreviewSizes());
                //            parameters.setPreviewSize(largestSize.width, largestSize.height);// 设置预览图片尺寸
                Size largestSize = getBestSupportedSize(parameters
                        .getSupportedPictureSizes());// 设置捕捉图片尺寸
                parameters.setPictureSize(largestSize.width, largestSize.height);
                if (startFontCamera) {
                    //连续对焦
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }
                camera.setParameters(parameters);
                camera.setPreviewDisplay(holder);
                // 设置用于显示拍照影像的SurfaceHolder对象
                camera.setDisplayOrientation(getPreviewDegree(CameraActivity.this));
                camera.startPreview();
                camera.cancelAutoFocus();//只有加上了这一句，才会自动对焦。

            } else {
                //
                Toast.makeText(this, "无法打开摄像头，请检查是否给开启了相关权限", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
        }
    }

    /**
     * Check if this device has a camera
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    /**
     * 物理按键事件
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_CAMERA: // 按下拍照按钮
                if (mcamera != null && event.getRepeatCount() == 0) {
                    // 拍照
                    //注：调用takePicture()方法进行拍照是传入了一个PictureCallback对象——当程序获取了拍照所得的图片数据之后
                    //，PictureCallback对象将会被回调，该对象可以负责对相片进行保存或传入网络
                    mcamera.takePicture(null, null, new MyPictureCallback());
                }
            case KeyEvent.KEYCODE_BACK:
                if (IS_TOOK == 0) {
                    finish();
                } else {
                    //  camera.startPreview();
                    mBinding.cameraImgTakepicture.setVisibility(View.VISIBLE);
                    mBinding.cameraTxtCancel.performClick();
                    return false;
                }

                break;

        }

        return super.onKeyDown(keyCode, event);
    }


    /**
     * 检验是否有SD卡
     *
     * @true or false
     */
    public static boolean isHaveSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 将拍下来的照片存放在SD卡中
     *
     * @param data
     * @throws IOException
     */
    public void saveToSDCard(byte[] data) throws IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
        String filename = format.format(date) + ".jpg";
        File fileFolder = new File(Environment.getExternalStorageDirectory()
                + savePath);
        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
            fileFolder.mkdir();
        }
        byte[] buffer;
        buffer = data.clone();
        File jpgFile = new File(fileFolder, filename);
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流
        outputStream.flush();
        outputStream.write(buffer); // 写入sd卡中
        outputStream.close(); // 关闭输出流
        //返回
        Intent intent = new Intent();
        intent.putExtra("ImgPath", Environment.getExternalStorageDirectory() + savePath + filename);
        setResult(40002, intent);
        this.finish();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mHolder = surfaceHolder;
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        try {
            mcamera.stopPreview();
        } catch (Exception e) {
        }
        setStartPreview(mcamera, surfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        releaseCamera();
        //默认回复后置摄像头
        startFontCamera = true;
        mHolder = null;
    }

    private Camera getCamera() {
        Camera camera = null;
        try {

            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
        }
        return camera;
    }

    private void CameraTrans(SurfaceHolder mholder) {
        // 切换前后摄像头
        int cameraCount = 0;
        CameraInfo cameraInfo = new CameraInfo();
        cameraCount = Camera.getNumberOfCameras();// 得到摄像头的个数

        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);// 得到每一个摄像头的信息
            if (startFontCamera) {
                // 现在是后置，变更为前置
                if (cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
                    /**
                     * 记得释放camera，方便其他应用调用
                     */
                    releaseCamera();
                    // 打开当前选中的摄像头
                    mcamera = Camera.open(i);
                    startFontCamera = false;
                    setStartPreview(mcamera, mholder);
                    break;
                }
            } else {
                // 现在是前置， 变更为后置
                if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
                    /**
                     * 记得释放camera，方便其他应用调用
                     */
                    releaseCamera();
                    mcamera = Camera.open(i);
                    startFontCamera = true;
                    setStartPreview(mcamera, mholder);
                    break;
                }
            }

        }
    }


    /**
     * 重构照相类
     *
     * @author
     */
    private final class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                bundle = new Bundle();
                bundle.putByteArray("bytes", data); //将图片字节数据保存在bundle当中，实现数据交换
                mBinding.cameraImgTakepicture.setVisibility(View.INVISIBLE);
                mBinding.cameraTxtCancel.setVisibility(View.VISIBLE);
                //                txtEnter.setVisibility(View.VISIBLE);
                IS_TOOK = 1;
                mcamera.stopPreview();
                mBinding.cameraTxtCancel.setText("重拍");
                mBinding.cameraTxtEnter.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放mCamera
     */
    private void releaseCamera() {
        if (mcamera != null) {
            mcamera.setPreviewCallback(null);
            mcamera.stopPreview();// 停掉原来摄像头的预览
            mcamera.release();
            mcamera = null;
        }
    }


    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
    public static int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    private Size getBestSupportedSize(List<Size> sizes) {
        // 取能适用的最大的SIZE
        Size largestSize = sizes.get(0);
        int largestArea = sizes.get(0).height * sizes.get(0).width;
        for (Size s : sizes) {
            int area = s.width * s.height;
            if (area > largestArea) {
                largestArea = area;
                largestSize = s;
            }
        }
        return largestSize;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private DialogInterface.OnClickListener cameraExceptionListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            CameraActivity.this.finish();
        }
    };
}
