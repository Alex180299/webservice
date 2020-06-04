package com.read.app.service;

import com.read.app.App;
import com.read.app.model.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutServiceImp implements LayoutService {

    private App app = App.getInstance();

    @Override
    public List<Field> findLayoutInFields() {
        return app.findLayoutInFields();
    }

    @Override
    public List<Field> findLayoutOutFields() {
        return app.findLayoutOutFields();
    }

    @Override
    public Field findInFieldById(Long id) {
        return app.findInFieldById(id);
    }

    @Override
    public Field findOutFieldById(Long id) {
        return app.findOutFieldById(id);
    }

    @Override
    public String saveInField(Field field) {
        return app.saveInField(field);
    }

    @Override
    public String saveOutField(Field field) {
        return app.saveOutField(field);
    }

    @Override
    public String updateInField(Field field) {
        return app.updateInField(field);
    }

    @Override
    public String updateOutField(Field field) {
        return app.updateOutField(field);
    }

    @Override
    public String deleteInField(Long id) {
        return app.deleteInField(id);
    }

    @Override
    public String deleteOutField(Long id) {
        return app.deleteOutField(id);
    }
}
