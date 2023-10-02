package com.crud.domain;

import lombok.Data;

@Data
public class Orders {
    private Integer orderid;
    private String username;
    private Double totalAmont;
    private int statusid;
    private String statusname;
}
