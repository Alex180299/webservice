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
public class Field
{
    @NotNull
    @Positive
    @JacksonXmlProperty(isAttribute = true)
    private Long id;
    @JacksonXmlProperty(isAttribute = true)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50, message = "El nombre del campo debe contener entre 1 y 50 caracteres")
    private String name;
    @NotNull
    @Positive
    @JacksonXmlProperty(isAttribute = true)
    private Long sequence;
    @NotNull
    @Positive
    @JacksonXmlProperty(isAttribute = true)
    private Long length;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20, message = "El tipo de dato debe contener entre 1 y 20 caracteres")
    @JacksonXmlProperty(isAttribute = true)
    private String type;

}
