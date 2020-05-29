package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Field {

    @JacksonXmlProperty
    private Long id;

    @JacksonXmlProperty
    private String name;

    @JacksonXmlProperty
    private Long sequence;

    @JacksonXmlProperty
    private Long length;

    @JacksonXmlProperty
    private String type;

    public Field(){}

    public Field(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
