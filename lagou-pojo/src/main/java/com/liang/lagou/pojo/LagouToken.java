package com.liang.lagou.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data

@Table(name = "lagou_token")
public class LagouToken implements Serializable {

    @Id
    private int id;

    private String email;

    private String token;
}
