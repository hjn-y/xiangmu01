package com.yw.entity;

import java.io.Serializable;

public class Role_Permission implements Serializable {
    private int currentId;
    private String i;

    @Override
    public String toString() {
        return "Role_Permission{" +
                "currentId=" + currentId +
                ", i='" + i + '\'' +
                '}';
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }
}
