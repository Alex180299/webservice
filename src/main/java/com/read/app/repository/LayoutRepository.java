package com.read.app.repository;

import com.read.app.model.Field;

import java.util.List;

public interface LayoutRepository
{
    public List<Field> findLayoutInFields();

    public List<Field> findLayoutOutFields();

    public Field findInFieldById(Long id);

    public Field findOutFieldById(Long id);

    public String saveInField(Field field);

    public String saveOutField(Field field);

    public String updateInField(Field field);

    public String updateOutField(Field field);

    public String deleteInField(Long id);

    public String deleteOutField(Long id);

    public boolean existsIn(Long id);

    public boolean existsOut(Long id);
}
