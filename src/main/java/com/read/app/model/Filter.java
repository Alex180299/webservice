package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter
{

    @NotNull
    @Positive
    @JacksonXmlProperty(isAttribute = true)
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50, message = "El nombre del layout debe contener entre 1 y 50 caracteres")
    @JacksonXmlProperty(isAttribute = true)
    private String layout;
    @NotNull
    @NotEmpty
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @NotNull
    @Positive
    @JacksonXmlProperty(isAttribute = true)
    private Long sequence;
    @NotNull
    @Positive
    @JacksonXmlProperty(isAttribute = true)
    private Long fieldId;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10, message = "El operador debe contener entre 1 y 10 caracteres")
    @JacksonXmlProperty(isAttribute = true)
    private String operator;
    @NotNull
    @NotEmpty
    @JacksonXmlProperty(isAttribute = true)
    private String value;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 1, message = "El paréntesis debe contener solamente 1 caracter")
    @JacksonXmlProperty(isAttribute = true)
    private String parenthesis;
    @NotNull
    @JacksonXmlProperty(isAttribute = true)
    private boolean negated;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10, message = "El conector debe contener entre 1 y 10 caracteres")
    @JacksonXmlProperty(isAttribute = true)
    private String connector;
}
