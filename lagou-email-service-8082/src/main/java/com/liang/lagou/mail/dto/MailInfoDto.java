package com.liang.lagou.mail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MailInfoDto implements Serializable {

    /**
     * 接受者
     * 这里不用数组形式，是考虑到如果用数组的话虽然也是群发效果，但是地址栏会显示很多人，可能会泄露隐私
     */
    private String to;

    /**
     * 抄送
     * 如果多个中间用英文逗号隔开
     */
    private String cc;

    /**
     * 密送
     * 如果多个中间用英文逗号隔开
     */
    private String bcc;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 附件base64字符串
     */
    private String fileBase64Str;

}
