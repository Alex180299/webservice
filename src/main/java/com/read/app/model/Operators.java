package com.read.app.model;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Operators {
    GREATER_THAN(">"),
    LESS_THAN("<");

    private String value;

    Operators(String value){
        this.value = value;
    }


    public static Operators forValue(String value){
        switch (value){
            case ">": return GREATER_THAN;
            case "<": return LESS_THAN;
            default: return null;
        }
    }
}
