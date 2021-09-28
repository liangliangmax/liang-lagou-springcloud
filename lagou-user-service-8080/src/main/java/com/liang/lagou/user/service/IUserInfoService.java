package com.liang.lagou.user.service;

import com.liang.lagou.pojo.LagouUser;

public interface IUserInfoService {

    String getEmailByToken(String token);

    LagouUser getUserByEmail(String email);
}
