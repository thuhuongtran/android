package com.vtcac.thuhuong.lab6.beans;

import java.io.Serializable;

public class BeanMessage implements Serializable{
    public String name;
    public String msgText;
    public String type;

    public BeanMessage(String name, String msgText, String type) {
        this.name = name;
        this.msgText = msgText;
        this.type = type;
    }
}
