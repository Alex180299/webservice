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
        return app.getLayout().getLayoutIn().getFields().getField();
    }

    @Override
    public List<Field> findLayoutOutFields() {
        return app.getLayout().getLayoutOut().getFields().getField();
    }

    @Override
    public Field findInFieldById(Long id) {
        return app.getLayout().getLayoutIn().getFields().getField().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Field save(Field field) {
        app.getLayout().getLayoutIn().getFields().getField().add(field);
        return field;
    }

    @Override
    public void delete(Field field) {
        app.getLayout().getLayoutIn().getFields().getField().remove(field);
    }
}
