package com.liang.lagou.mail.util;

import javax.mail.MessagingException;
import java.io.File;
import java.util.Map;

public interface IMailUtil {
    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String moduleName, String to, String subject, String content, String fromAlias, String[] cc, String[] bcc) throws Exception;

    /**
     * 发送HTML邮件
     * @param to
     * @param subject
     * @param content
     * @param fromAlias 邮箱中发件人的昵称 xxx<xxx@xxx.com>
     * @throws MessagingException
     */
    public void sendHtmlMail(String moduleName, String to, String subject, String content, String fromAlias, String[] cc, String[] bcc) throws Exception;

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String moduleName, String to, String subject, String content, String fromAlias, String fileName, String fileType, String fileBase64Str, String[] cc, String[] bcc) throws Exception;

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String moduleName, String to, String subject, String content, String fromAlias, String fileName, File file, String[] cc, String[] bcc) throws Exception;


    /**
     * 发送正文中有静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     * @throws MessagingException
     */
    public void sendResourceMail(String moduleName, String to, String subject, String content, String fromAlias, Map<String, String> rscIdMap, String[] cc, String[] bcc) throws Exception;

}
