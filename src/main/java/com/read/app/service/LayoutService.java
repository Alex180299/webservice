package com.read.app.service;

import com.read.app.model.Field;

import java.util.List;

public interface LayoutService {

    public List<Field> findLayoutInFields();
    public List<Field> findLayoutOutFields();
    public Field findInFieldById(Long id);
    public Field save(Field field);
    public void delete(Field field);

}
