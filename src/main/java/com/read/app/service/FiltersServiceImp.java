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
        return app.findAll();
    }

    @Override
    public Filter findById(Long id) {
        return app.findById(id);
    }

    @Override
    public String save(Filter filter) {
        return app.save(filter);
    }

    @Override
    public String update(Filter filter) {
        return app.update(filter);
    }

    @Override
    public String delete(Long id) {
        return app.delete(id);
    }
}
