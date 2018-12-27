package com.vtcac.thuhuong.lab6.beans;

import java.io.Serializable;

public class BeanContact implements Serializable{
    public String type;
    public String name;

    public BeanContact(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
