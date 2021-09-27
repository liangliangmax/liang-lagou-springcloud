package com.liang.lagou.code.service;

public interface ICodeService {

    int generateCode(String email);

    boolean validateCode(String email, String code);

}
