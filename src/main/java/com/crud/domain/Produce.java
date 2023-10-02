package com.crud.domain;

import lombok.Data;

@Data
public class Produce {
    private Integer orderid;
    private Integer produceid;
    private Integer quantity;
    private Double unitprice;
    private Double totalprice;
}
