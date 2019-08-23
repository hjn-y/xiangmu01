package com.yw.entity;

import java.io.Serializable;

public class User_Role implements Serializable {
    private int uid;
    private int rid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "User_Role{" +
                "uid=" + uid +
                ", rid=" + rid +
                '}';
    }
}
