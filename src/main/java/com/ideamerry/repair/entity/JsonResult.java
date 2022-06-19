package com.ideamerry.repair.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class JsonResult implements Serializable {
    private int code;
    private String msg;
    private Object obj;

    public JsonResult(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult() {
    }
}
