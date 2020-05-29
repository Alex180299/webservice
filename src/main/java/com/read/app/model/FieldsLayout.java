package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class FieldsLayout {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Field> field = new ArrayList<>();

    FieldsLayout(){}

    public FieldsLayout(List<Field> field) {
        this.field = field;
    }

    public List<Field> getField() {
        return field;
    }

    public void setField(List<Field> field) {
        this.field = field;
    }
}
