package com.liang.lagou.code.service;

import com.liang.lagou.code.mapper.LagouAuthCodeMapper;
import com.liang.lagou.pojo.LagouAuthCode;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class CodeServiceImpl implements ICodeService {

    @Autowired
    private LagouAuthCodeMapper lagouAuthCodeMapper;


    @Override
    public int generateCode(String email) {

        Date now = new Date();

        LagouAuthCode lagouAuthCode = new LagouAuthCode();

        lagouAuthCode.setEmail(email);


        //生成6位数字
        String code = "";

        do{
            code = RandomStringUtils.randomNumeric(6);

        }while (code.startsWith("0"));

        lagouAuthCode.setCreatetime(now);

        //谁知超时时间
        long time = 10*60*1000;//30分钟
        Date afterDate = new Date(now .getTime() + time);//30分钟后的时间

        lagouAuthCode.setExpiretime(afterDate);

        return lagouAuthCodeMapper.insertSelective(lagouAuthCode);
    }

    @Override
    public boolean validateCode(String email, String code) {

        if(StringUtils.isBlank(code)){
            return false;
        }

        Example example = new Example(LagouAuthCode.class);

        example.createCriteria().andEqualTo("email",email);

        LagouAuthCode lagouAuthCode = lagouAuthCodeMapper.selectOneByExample(example);

        if(lagouAuthCode == null )
            return false;

        if(isTimeout(lagouAuthCode))
            return false;

        if(!lagouAuthCode.getCode().equalsIgnoreCase(code))
            return false;

        return true;
    }


    private boolean isTimeout(LagouAuthCode lagouAuthCode){

        Date expiretime = lagouAuthCode.getExpiretime();

        //如果现在时刻已经超过了过期时间，说明已经过期了
        if(expiretime.before(new Date())){
            return true;

        }

        return false;

    }
}