package com.read.app;

import com.read.app.model.FieldsLayout;
import com.read.app.service.ReadLayouts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class App {

    @Autowired
    ReadLayouts readLayouts;

    @PostConstruct
    public void init(){
        System.out.println(readLayouts.readFields().getField().get(0).getName());
        System.out.println(readLayouts.readFilters().getFilter().get(0).getName());
    }

}
