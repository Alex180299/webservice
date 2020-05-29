package com.read.app.service;

import com.read.app.model.Filter;

import java.util.List;

public interface FiltersService {

    public List<Filter> findAll();
    public Filter findById(Long id);
    public Filter save(Filter filter);
    public void delete(Filter filter);
}
