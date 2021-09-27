package com.liang.lagou.code.controller;

import com.liang.lagou.code.CodeApi;
import com.liang.lagou.code.service.ICodeService;
import com.liang.lagou.pojo.RestApiResult;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController implements CodeApi {

    @Autowired
    private ICodeService codeService;


    @Override
    public RestApiResult generateCode(String email) {
        if(StringUtils.isBlank(email)){
            return RestApiResult.ERROR(-1,"邮箱为空");
        }

        return RestApiResult.OK(codeService.generateCode(email));
    }

    @Override
    public RestApiResult validateCode(String email, String code) {
        return RestApiResult.OK(codeService.validateCode(email,code));
    }
}
