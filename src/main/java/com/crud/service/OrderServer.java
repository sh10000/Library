package com.crud.service;

import java.util.Map;

public interface OrderServer {
    Integer saveOrder(Map<Integer, Integer> bookQuantityMap);
}
