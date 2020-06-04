package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "filters")
public class Filters {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Filter> filter = new ArrayList<>();

    public Filters(){}

    public Filters(List<Filter> filter) {
        this.filter = filter;
    }

    public List<Filter> getFilter() {
        return filter;
    }

    public void setFilter(List<Filter> filter) {
        this.filter = filter;
    }
}
