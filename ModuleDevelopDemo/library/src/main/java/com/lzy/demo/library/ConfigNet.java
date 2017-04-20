package com.lzy.demo.library;

/**
 * Created by zhuofeng on 2015/4/8.
 */
public class ConfigNet {

    /**
     * 正式服务地址
     */
    public final static String KKCreditCheck_HOST = "http://www.kkcredit.cn/ccl/data/ws/rest/app/";//正式服务器 授权
    public final static String MAIN_HOST          = "http://life.kkcredit.cn/kkCreditLife/data/ws/rest/app/";
    public final static String MAIN_HOST_sq       = "http://life.kkcredit.cn/KKLifeService/app/";//王弘扬
    public final static String FUYOURODER         = "http://kkpay.kkcredit.cn/kkdCardRepayment/data/ws/rest/app/";//富友还款接口
    public final static String getCountryFlag     = "http://life.kkcredit.cn/kclstaticfiles/countryflag/";//获得国家旗帜
    public final static String RESOURCE_HOST      = "http://life.kkcredit.cn/kclstaticfiles/";
    public final static String KKLIFEDISCOUNT     = "http://life.kkcredit.cn/KKLifeDiscount/";//信用卡优惠

    /*
    * 测试服务器
    * */
//        public final static String KKLIFEDISCOUNT     = "http://10.139.30.48:8180/KKLifeDiscount/";//信用卡优惠
//        public final static String KKCreditCheck_HOST = "http://beta.kkcredit.cn/ccl/data/ws/rest/app/";//测试服务器 授权
//        public final static String MAIN_HOST_sq       = "http://beta.kkcredit.cn/KKLifeService/app/";//王弘扬
//    public final static String MAIN_HOST          = "http://beta.kkcredit.cn/kkCreditLife/data/ws/rest/app/";
//    public final static String getCountryFlag     = "http://beta.kkcredit.cn/kclstaticfiles/countryflag/";//获得国家旗帜
//    public final static String RESOURCE_HOST      = "http://beta.kkcredit.cn/kclstaticfiles/";
//    public final static String FUYOURODER         = "http://beta.kkcredit.cn/kkdCardRepayment/data/ws/rest/app/";//富友还款接口


//    public final static String KKLIFEDISCOUNT     = "http://10.139.30.48:8888/KKLifeDiscount/";//信用卡优惠
//    public final static String KKCreditCheck_HOST = "http://beta.kkcredit.cn/ccl/data/ws/rest/app/";//测试服务器 授权
//    public final static String MAIN_HOST_sq       = "http://10.139.30.48:8888/KKLifeService/app/";//王弘扬
//    public final static String MAIN_HOST          = "http://10.139.30.48:8888/kkCreditLife/data/ws/rest/app/";
//    public final static String getCountryFlag     = "http://10.139.30.48:8888/kclstaticfiles/countryflag/";//获得国家旗帜
//    public final static String RESOURCE_HOST      = "http://10.139.30.48:8888/kclstaticfiles/";
//    public final static String FUYOURODER         = "http://10.139.30.48:8888/kkdCardRepayment/data/ws/rest/app/";//富友还款接口

//    public final static String MAIN_HOST_sq       = "http://10.139.30.48:8180/KKLifeService/app/";//王弘扬


    //public final static String MAIN_HOST = "http://10.139.30.57:8080/kkCreditLife/data/ws/rest/app/";//田雨佳
    //public final static String MAIN_HOST= "http://10.139.30.136:8080/kkCreditLife/data/ws/rest/app/";//阮林
    //public final static String MAIN_HOST= "http://10.139.30.134:8080/kkCreditLife/data/ws/rest/app/";//陈连云
    //public final static String MAIN_HOST= "http://10.139.30.55:8080/kkCreditLife/data/ws/rest/app/";//蔡培培
//    public final static String MAIN_HOST= "http://10.139.30.132:8080/kkCreditLife/data/ws/rest/app/";//锻凯
//    public final static String KKCreditCheck_HOST = "http://10.139.30.67:8080/ccl/data/ws/rest/app/";//万健


    public final static String ORDERINIT = "order/init";//富友生成订单

    public final static String MODIFYRELATIONCCL = "modifyRelatonCcl";//确认关联卡卡贷接口

    public final static String OAUTHPRECHECK = "third/oauthPreCheck";//跳转卡卡贷接口，验证码手机号码状态,预检

    public final static String OAUTHREGISTERED = "third/oauthRegistered";//跳转卡卡贷接口。验证各种状态的接口

    public final static String OAUTHLOGIN = "third/oauthLogin";//卡卡生活登陆卡卡贷


    public final static String LOADLIST = "loadlist.do";//贷款超市列表


    public final static String COUNTOPENSERVICE = "countOpenService";//统计打开服务的接口

    public final static String ORDERCLOSE = "order/close";//富有订单结束

    public final static String getPerfectInfo = "getPerfectInfo";//获得信用分

    public final static String contentmaker = "contentmaker.do";//发帖接口

    public final static String getScoreInfo = "getScoreInfo";//获得信用分接口

    public final static String getCreditScore = "getCreditScore";//获取信用分接口


    public final static String BINDSERVICE = "umengpush/bindDevice";//绑定友盟推送的token和customid

    public final static String GETMOXIECODEDETAILS = "creditCard/getMoxieCodeDatas";//获取网银导入验证码类型

    public final static String INPUTMOXIECODE = "creditCard/inputMoxieCode";//上传魔蝎验证码给服务器

    public final static String queryGoldInforms   = "queryGoldInforms";//获得黄金汇率接口
    /**
     * 资源服务器
     */
    public final static String RESOURCE_BANK_ICON = RESOURCE_HOST + "bank/";

    /**
     * 获取验证码图片链接及正确的验证码
     */
    public final static String VERIFICATION_CODE = "getcode";
    /**
     * 密码重置
     */
    public final static String RESET_PASSWORD    = "resetpwd";
    /**
     * 注册
     */
    public final static String REGISTER          = "register";
    /**
     * 登录
     */
    public final static String LOGIN             = "login";

    /**
     * 获取资源版本
     */
    public final static String GET_RESOURCE = "resource";
    /**
     * 获取APP版本
     */
    public final static String GET_APP_VER  = "lastversions";
    /**
     * 获取系统枚举
     */
    public final static String GET_ENUMS    = "getenums";

    /**
     * 发送手机验证码(注册)
     */
    public final static String SEND_VERIFY_CODE_REGISTER = "sendvcodenew/{verifyKind}/{mobileNo}/{signStr}/{timestamp}/{vcodeToken}";


    /**
     * 提交征信信息(服务模块)
     */
    public final static String SUBMIT_CREDITINFO_FOR_QUERY = "submitcreditinfoForQuery";

    /**
     * 征信查询答案校验
     */
    public final static String VERIFY_ANSWERS_FORQUERY = "verifyanswersForQuery";


    /**
     * [征信激活]校验查询码是否正确,用于征信查询
     */
    public final static String VERIFY_QUERY_CODE_FORQUERY = "verifyqcodeForQuery";

    /**
     * 征信注册第一步
     */
    public final static String REGCREDIT_ACCOUNT_FIRST  = "regcreditaccount";
    /**
     * 征信注册第二步
     */
    public final static String REGCREDIT_ACCOUNT_SECOND = "creditaccount";

    /**
     * 获取银联验证码
     */
    public final static String GET_BANKCODE  = "bankcardInit";
    /**
     * 提交银联认证
     */
    public final static String SUBMIT_UNAUTH = "submitUnionpayAuthForQuery";

    /*
    * 获取活动banner
    * */
    public final static String getAdvertInfo             = "getAdvertInfo";
    /**
     * 初始化公积金登录界面
     */
    public final static String INIT_ACCFUN_LOGIN         = "initProvidentFundLoginView";
    /**
     * 公积金账号登录
     */
    public final static String ACCFUN_LONGIN             = "publicFundsQueryLogin";
    /**
     * 获得公积金账号明细
     */
    public final static String GET_ACCFUND_DETAIL        = "getProvidentFundAcctDetail";
    /**
     * 征信中心官网
     */
    public final static String CREDIT_REFERENCE_CENTER   = "http://www.pbccrc.org.cn";
    /**
     * 获取征信信息
     */
    public final static String GET_CREDIT_INFO           = "getcreditInfo";
    /**
     * 获取原始征信报告
     */
    public final static String GET_PRIMARY_CREDIT_REPORT = "creditReport";


    /**
     * 上传照片
     */
    public final static String UPLOAD_PHOTO = "uploadPhoto";


    /**
     * 手动添加信用卡
     */
    public final static String ADD_CREDITCARD_BY_MANUALLY = "creditCard/addCreditCardByManually";

    /**
     * 服务模块获取信用卡列表
     */
    public final static String GET_CREDITCARD_LIST = "creditCard/getCreditCardlist";
    /**
     * 获取信用卡详情-消费信息
     */
    public final static String QUERY_BILL_EXPENSE  = "creditCard/queryBillExpense";

    /**
     * 获取魔蝎支持的银行列表
     */
    public final static String GET_MOXIE_BANK_LIST = "creditCard/getMoXieBankList";

    /**
     * 通过账单邮箱添加信用卡信息
     */
    public final static String ADD_CREDITCARD_BY_EMAIL = "creditCard/addCreditCardByEmail";


    /**
     * 获取信用卡详情
     */
    public final static String GET_CREDITCARD_DETAIL = "creditCard/getCreditCardDetail";

    /**
     * 获取魔蝎支持的邮箱列表
     */
    public final static String GET_MOXIE_EMAIL_LIST = "creditCard/getMoXieEmailList";

    /**
     * 删除信用卡信息
     */
    public final static String DELETE_CREDITCARD = "creditCard/deleteCreditCard";


    /**
     * 重新获取信用卡信息
     */
    public final static String REFRESH_CREDITCARD_INFO_LIST = "creditCard/refreshCreditCardInfoList";

    /**
     * 通过网银登录添加信用卡信息
     */
    public final static String ADD_CREDITCARD_BY_BANK = "creditCard/addCreditCardByBankLogin";


    /**
     * 初始化网银登录页
     */
    public final static String INIT_BANK_LOGIN = "initEBankLogin";
    /*
    * 首页信用卡优惠接口
    *
    * */

    public final static String HUDONGINSHOUYE = "HuodongInShouye";



}