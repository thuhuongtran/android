package com.vtcac.thuhuong.lab6_button.utils;

import java.io.Serializable;

public class Contact implements Serializable{
    public String type;
    public String name;

    public Contact(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
