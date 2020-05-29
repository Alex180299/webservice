package com.read.app.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class FiltersLayout {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Filter> filter = new ArrayList<>();

    public FiltersLayout(){}

    public FiltersLayout(List<Filter> filter) {
        this.filter = filter;
    }

    public List<Filter> getFilter() {
        return filter;
    }

    public void setFilter(List<Filter> filter) {
        this.filter = filter;
    }
}
