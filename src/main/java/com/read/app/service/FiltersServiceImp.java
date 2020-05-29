package com.read.app.service;

import com.read.app.App;
import com.read.app.model.Filter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltersServiceImp implements FiltersService {

    private App app = App.getInstance();

    @Override
    public List<Filter> findAll() {
        return app.getFiltersLayout().getFilter();
    }

    @Override
    public Filter findById(Long id) {
        return app.getFiltersLayout().getFilter().stream().filter(filter -> filter.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Filter save(Filter filter) {
        app.getFiltersLayout().getFilter().add(filter);
        return filter;
    }

    @Override
    public void delete(Filter filter) {
        app.getFiltersLayout().getFilter().remove(filter);
    }
}
