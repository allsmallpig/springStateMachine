package com.statemachine.demo;

/**
 * @author litiezhu
 * @packageName com.statemachine.demo
 * @date 2020/3/5 20:10
 * @Description
 */
public enum OrderEnvents {
    PAY(1,"支付"), //支付
    RECEIVE(2,"收货");//收货


    public int getCode() {
        return code;
    }

    public OrderEnvents setCode(int code) {
        this.code = code;
        return this;
    }

    public String getValue() {
        return value;
    }

    public OrderEnvents setValue(String value) {
        this.value = value;
        return this;
    }

    OrderEnvents() {
    }

    private int code;
    private String value;

    OrderEnvents(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
