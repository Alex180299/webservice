package com.read.app.service;

import com.read.app.model.Field;
import com.read.app.repository.LayoutRepository;
import com.read.app.repository.LayoutRepositoryImp;

import java.util.List;

public class LayoutServiceImp implements LayoutService {

    private static LayoutRepository layoutRepository = new LayoutRepositoryImp();

    @Override
    public List<Field> findLayoutInFields() {
        return layoutRepository.findLayoutInFields();
    }

    @Override
    public List<Field> findLayoutOutFields() {
        return layoutRepository.findLayoutOutFields();
    }

    @Override
    public Field findInFieldById(Long id) {
        return layoutRepository.findInFieldById(id);
    }

    @Override
    public Field findOutFieldById(Long id) {
        return layoutRepository.findOutFieldById(id);
    }

    @Override
    public String saveInField(Field field) {
        return layoutRepository.saveInField(field);
    }

    @Override
    public String saveOutField(Field field) {
        return layoutRepository.saveOutField(field);
    }

    @Override
    public String updateInField(Field field) {
        return layoutRepository.updateInField(field);
    }

    @Override
    public String updateOutField(Field field) {
        return layoutRepository.updateOutField(field);
    }

    @Override
    public String deleteInField(Long id) {
        return layoutRepository.deleteInField(id);
    }

    @Override
    public String deleteOutField(Long id) {
        return layoutRepository.deleteOutField(id);
    }
}
