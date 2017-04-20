package com.lzy.demo.library;


/**
 * App配置信息
 * Created by zhuofeng on 2015/5/13.
 */
public class AppConfig {

    /** 是否开启debug模式 */
    public static final boolean DEBUG = BuildConfig.DEBUG;

    /** app名称 */
    public static final String appName = "KKCredit";

    public static final String appNameKKL = "KKLife";

    /** app文件存放路径 */
    public static final String filePath = "/KKCreditCache/file";

    /** app图片存放路径 */
    public static final String imagePath = "";

    /** 超时时间 */
    public static final int SOCKET_TIMEOUT = 60 * 1000;

    /**
     * 字符编码
     */
    public static final String ENCODEING      = "UTF-8";
    /**
     * encryption key
     */
    public static final String ENCRYPTION_KEY = "A26A809CA8838A95";


    public static  boolean cancelNetFlag = false;


    /*／
    * 卡卡贷访问私钥
    *
    * */
    public static final String KKCREDITPRIVATEKEY = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAk100nlv/WlrTkKmNdBwURQWneGofoIQoV77TLOVhffSRDNDjsNm6fmgQJeM7RNcITwv+NLAh+jVv0XxWIvt2xwIDAQABAkEAh/470OiVfozTMW1HXR+MlSXipv1IsplDobY4q/YDQnhC92EnCSLhLvxVNMghfNp9ztfR6htiFf9397MnvGPEAQIhAOxenwgEdWAf8SfnuTY8Ff6UydRlFL9zxL4OKZx653G/AiEAn5pEJoiXX7YcaLZRv2RVyM7BWPB3CGCkpeF7OiHvLPkCIBAIFuc3TjK31+Zp/BDmoGNE+i9yr6aQlo6BbWcUmvAHAiEAkwCY4tEOc9adpgi/lMRKixF8XnnleS7il/Lt+CZHUKkCIQChqk9biDo5qAiqZDNh4DgAhQ/0XeFLe/MxgDPTnFWwzQ==";



}
