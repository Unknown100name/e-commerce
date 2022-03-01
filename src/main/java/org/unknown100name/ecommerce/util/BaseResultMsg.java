package org.unknown100name.ecommerce.util;

/**
 * @author unknown100name
 * @description 通用返回 message
 * @since 2022/1/3
 */
public class BaseResultMsg {

    private BaseResultMsg(){
        throw new IllegalAccessError("Utility class");
    }

    public static final String SUCCESS_OTHERS = "操作成功";

    public static final String ERROR_VERTIFY_CODE = "验证码错误";
    public static final String ERROR_VERTIFY_CODE_TOO_QUICK = "请不要过快刷新验证码";

    public static final String ERROR_EXISTED_USERNAME = "用户名已存在";
    public static final String ERROR_NICK_OR_PASSWORD = "用户不存在或密码不正确";
    public static final String ERROR_NICK_OR_ID_CARD = "用户名不存在或身份证错误";

    public static final String SUCCESS_FORGET_PASSWORD = "重置密码成功，默认密码为身份证后六位";
    public static final String SUCCESS_RESET_PASSWORD = "重置密码成功";
    
    public static final String ERROR_UNKNOWN = "网络开小差了";
    public static final String ERROR_PARAM = "参数错误，请尝试使用正常途径访问接口";

}
