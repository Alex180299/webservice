package com.read.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Layout {

    @JsonProperty("layout-in")
    private LayoutIn layoutIn;
    @JsonProperty("layout-out")
    private LayoutOut layoutOut;

}
