package com.crud.controller.utils;

import lombok.Data;

@Data
public class R<U> {
    private Boolean flag;
    private Object data;
    private String msg;

    public R() {
    }

    public R(boolean flag){
        this.flag=flag;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(boolean flag, Object data, String msg) {
        this.flag=flag;
        this.data=data;
        this.msg=msg;
    }
}
