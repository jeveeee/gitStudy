package com.ideamerry.repair.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Jscode2session implements Serializable {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
