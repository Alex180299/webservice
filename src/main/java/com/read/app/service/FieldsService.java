package com.read.app.service;

import com.read.app.model.Field;

import java.util.List;

public interface FieldsService {

    public List<Field> findAll();
    public Field findById(Long id);
    public Field save(Field field);
    public Field delete(Field field);

}
