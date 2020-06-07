package com.read.app.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.read.app.App;
import com.read.app.configuration.ConfigurationModule;
import com.read.app.model.Configuration;
import com.read.app.model.Filters;
import com.read.app.model.Layout;
import com.read.app.repository.FiltersRepositoryImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ReadLayouts {

    private static final Logger log = LogManager.getLogger(FiltersRepositoryImp.class);
    private static final XmlMapper xmlMapper = new XmlMapper();
    static {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Layout readLayout(){
        return mapLayout(ConfigurationModule.layoutFieldsFile, Layout.class);
    }

    public Filters readFilters(){
        return mapLayout(ConfigurationModule.layoutFiltersFile, Filters.class);
    }

    public Layout writeLayout(Layout layout){
        return serializeLayout(ConfigurationModule.layoutFieldsFile, layout);
    }

    public Filters writeFilters(Filters filtersLayout){
        return serializeLayout(ConfigurationModule.layoutFiltersFile, filtersLayout);
    }

    public Configuration readConfig(){
        return mapLayout(ConfigurationModule.configurationFile, Configuration.class);
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
