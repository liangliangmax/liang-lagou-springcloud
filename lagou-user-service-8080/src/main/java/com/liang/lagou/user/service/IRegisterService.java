package com.liang.lagou.user.service;

public interface IRegisterService {

    int register(String email,String password);


    boolean isRegister(String email);

}
