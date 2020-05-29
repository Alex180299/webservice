package com.read.app.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.read.app.model.FieldsLayout;
import com.read.app.model.FiltersLayout;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Log4j2
public class ReadLayouts {

    private static final XmlMapper xmlMapper = new XmlMapper();

    public FieldsLayout readFields(){
        return mapLayout("./config/fields.xml", FieldsLayout.class);
    }

    public FiltersLayout readFilters(){
        return mapLayout("./config/filters.xml", FiltersLayout.class);
    }

    private <T>T mapLayout(String fileName, Class className){
        try {
            return (T) xmlMapper.readValue(new File(fileName), className);
        } catch (Exception e) {
            log.error("Ocurrió un error: " + e.getMessage());
            return null;
        }
    }

}
