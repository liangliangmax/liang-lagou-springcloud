package com.liang.lagou.mail.service;

import com.liang.lagou.mail.dto.MailInfoDto;
import org.apache.commons.lang3.StringUtils;

public interface IMailService {

    void sendMail(MailInfoDto mailInfoDto) throws Exception;

    /**
     * 默认的方法，将字符串按照regex进行分割，如果字符串是空，则直接返回空数组
     * @param str
     * @param regex
     * @return
     */
    default String[] parseArray(String str, String regex){

        if(StringUtils.isNotBlank(str)){

            String[] split = str.split(regex);

            return split;
        }

        return new String[]{};
    }

    /**
     * 提供一个默认是逗号分割的方法
     * @param str
     * @return
     */
    default String[] parseArray(String str){

        return parseArray(str,",");
    }
}
