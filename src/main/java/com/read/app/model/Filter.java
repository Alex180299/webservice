package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

    private Long id;

    private String layout;

    private String name;

    private Long sequence;

    private Long fieldId;

    private Operators operator;

    private String value;

    private String parenthesis;

    private boolean negated;

    private String connector;

    public void setOperator(String value){
        this.operator = Operators.forValue(value);
    }
}
