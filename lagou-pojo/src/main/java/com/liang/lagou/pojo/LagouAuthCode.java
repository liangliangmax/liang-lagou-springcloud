package com.liang.lagou.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "lagou_auth_code")
public class LagouAuthCode implements Serializable {

    @Id
    private int id;

    private String email;

    private String code;

    private Date createtime;

    private Date expiretime;
}
