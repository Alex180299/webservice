package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

    @JacksonXmlProperty(isAttribute = true)
    private Long id;
    @JacksonXmlProperty(isAttribute = true)
    private String layout;
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private Long sequence;
    @JacksonXmlProperty(isAttribute = true)
    private Long fieldId;
    @JacksonXmlProperty(isAttribute = true)
    private String operator;
    @JacksonXmlProperty(isAttribute = true)
    private String value;
    @JacksonXmlProperty(isAttribute = true)
    private String parenthesis;
    @JacksonXmlProperty(isAttribute = true)
    private boolean negated;
    @JacksonXmlProperty(isAttribute = true)
    private String connector;
}
