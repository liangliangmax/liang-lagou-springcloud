package com.liang.lagou.user.service;

import com.liang.lagou.pojo.LagouUser;

public interface ITokenService {

    String createToken(LagouUser lagouUser);

}
