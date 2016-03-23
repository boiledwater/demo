package com.demo.schedule;

/**
 * Created by hulizhong on 2016/3/24.
 */
class Item {
    public String name;
    public String value;
    public int status = status_available;
    public static int status_appointed = 1;
    public static int status_can_not_appoint = 2;
    public static int status_available = 3;

    public Item(String name) {
        this(name, null);
    }

    public Item(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
