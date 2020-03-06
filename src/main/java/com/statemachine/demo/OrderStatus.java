package com.statemachine.demo;

import lombok.Data;

/**
 * @author litiezhu
 * @packageName com.statemachine.demo
 * @date 2020/3/5 20:08
 * @Description
 */
public enum OrderStatus {
    CREATED(1,"订单创建"),  //订单创建
    WAITING_FOR_RECEIVE(2,"待收货"), //待收货
    FINISHED(3,"订单完结"); //订单完结

    public int getCode() {
        return code;
    }

    public OrderStatus setCode(int code) {
        this.code = code;
        return this;
    }

    public String getValue() {
        return value;
    }

    public OrderStatus setValue(String value) {
        this.value = value;
        return this;
    }

    OrderStatus() {
    }

    private int code;
    private String value;

    OrderStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
