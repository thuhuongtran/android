package com.vtcac.thuhuong.custom_list_view.utils;

public class Contact {
    String name;
    String type; // in Facebook or from Phonebook
    String phone;

    public Contact(String name, String type, String phone) {
        this.name = name;
        this.type = type;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }
}
