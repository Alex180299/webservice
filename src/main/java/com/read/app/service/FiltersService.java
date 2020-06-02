package com.read.app.service;

import com.read.app.model.Filter;

import java.util.List;

public interface FiltersService {

    public List<Filter> findAll();
    public Filter findById(Long id);
    public String save(Filter filter);
    public String update(Filter filter);
    public String delete(Long id);
}
