package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field
{

    @JacksonXmlProperty(isAttribute = true)
    private Long id;
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private Long sequence;
    @JacksonXmlProperty(isAttribute = true)
    private Long length;
    @JacksonXmlProperty(isAttribute = true)
    private String type;

}
