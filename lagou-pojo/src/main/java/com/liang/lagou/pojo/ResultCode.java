package com.liang.lagou.pojo;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "成功"),
    ERROR(500,"失败"),


    REQUEST_METHOD_NOT_SUPPORT(405,"不被允许的方法"),

    SERVICE_FALLBACK(901,"服务正忙，请稍后访问"),


    /**
     * 100x 参数类相关
     */
    PARAM_INCORRECT(1000,"参数错误"),

    PARAM_IS_EMPTY(1001,"参数为空"),

    FILE_PARSE_ERROR(1002,"文件解析错误"),

    NOT_A_PHONE(1003,"不是手机号"),

    NOT_A_EMAIL(1004,"不是邮箱"),

    FILE_IS_EMPTY(1005,"文件内容为空"),

    ILLEGAL_EXCEL_TEMPLATE_FILE(1006,"错误的excel模板文件"),


    /**
     * 200x 结果相关
     */
    NO_SUCH_RESULT(2001,"未找到对应的记录"),

    DUPLICATE_NAME(2002,"名称重复"),

    BATCH_INSERT_ERROR(2003,"批量新增失败"),

    EXCEL_CONTENT_IS_INVALID(2004,"excel有错误内容"),

    REGEXP_NOT_MATCH(2005,"格式不匹配"),

    ORDER_SCATTER_ERROR(2006,"订单打散失败"),

    ORDER_SCATTER_SUCCESS(2007,"订单打散成功"),

    NO_RESUBMIT(2008,"请勿重复提交"),

    NO_PROVINCE_CITY(2009,"未找到省市信息"),

    CHANNEL_APPLY_STATUS(2010,"渠道已被申请"),

    REPEAT_CHANNEL_APPLY_STATUS(2011,"不能重复审批"),

    IN_APPROVAL(2012,"单据正在审批中"),

    INVALID_GOODS_CATEGORY(2013,"错误的赠品类别"),

    ORDER_DUPLICATE(2014,"订单有重复"),

    PROMOTION_CLOSED(2015,"活动已结束，请先调整活动时间"),


    /**
     * 1000x库存相关
     */
    STOCK_IS_NEGATIVE(10001,"库存为负"),
    STOCK_IS_LOCK(10002,"库存正在被占用，请稍后重试"),
    OUT_OF_STOCK(10003,"库存不足"),

    UNKNOWN_OP_TYPE(10004,"未知操作类型"),

    GIFT_STOCK_NOT_ENOUGH(10005,"赠品数量不足"),

    /**
     * 2000x 登录相关
     */
    PASSWORD_INCORRECT(20001,"密码错误"),

    VALIDATE_INCORRECT(20003,"用户名或密码错误"),
    VERIFICATION_CODE_ERROR(20004,"验证码校验失败"),

    CANNOT_GET_ROLES_INFO(20005,"获取不到角色信息"),

    /**
     * 3000x 任务执行相关
     */
    TASK_IS_RUNNING(30001,"任务正在执行，请勿重复提交"),



    ;

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
