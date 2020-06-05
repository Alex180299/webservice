package com.read.app.service;

import com.read.app.model.Filter;
import com.read.app.repository.FiltersRepository;
import com.read.app.repository.FiltersRepositoryImp;

import java.util.List;

public class FiltersServiceImp implements FiltersService {

    private static FiltersRepository filtersRepository = new FiltersRepositoryImp();

    @Override
    public List<Filter> findAll() {
        return filtersRepository.findAll();
    }

    @Override
    public Filter findById(Long id) {
        return filtersRepository.findById(id);
    }

    @Override
    public String save(Filter filter) {
        return filtersRepository.save(filter);
    }

    @Override
    public String update(Filter filter) {
        return filtersRepository.update(filter);
    }

    @Override
    public String delete(Long id) {
        return filtersRepository.delete(id);
    }
}
