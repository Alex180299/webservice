package com.read.app.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.read.app.model.Configuration;
import com.read.app.model.Filters;
import com.read.app.model.Layout;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Log4j2
public class ReadLayouts {

    private static final XmlMapper xmlMapper = new XmlMapper();
    static {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Layout readLayout(){
        return mapLayout("./config/layout.xml", Layout.class);
    }

    public Filters readFilters(){
        return mapLayout("./config/filters.xml", Filters.class);
    }

    public Layout writeLayout(Layout layout){
        return serializeLayout("./config/layout.xml", layout);
    }

    public Filters writeFilters(Filters filtersLayout){
        return serializeLayout("./config/filters.xml", filtersLayout);
    }

    public Configuration readConfig(){
        return mapLayout("./config/configuration.xml", Configuration.class);
    }

    private <T>T mapLayout(String fileName, Class className){
        try {
            return (T) xmlMapper.readValue(new File(fileName), className);
        } catch (Exception e) {
            log.error("Ocurrió un error: " + e.getMessage());
            return null;
        }
    }

    private <T>T serializeLayout(String fileName, T entity){
        try {
            xmlMapper.writeValue(new File(fileName), entity);
            return entity;
        } catch (IOException e) {
            log.error("Ocurrió un error: " + e.getMessage());
            return null;
        }
    }

}
