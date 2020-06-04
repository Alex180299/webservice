package com.read.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "layout")
public class Layout {

    @JsonProperty("layout-in")
    private LayoutIn layoutIn;
    @JsonProperty("layout-out")
    private LayoutOut layoutOut;

}
