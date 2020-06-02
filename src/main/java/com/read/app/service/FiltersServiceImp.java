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
    public String save(Filter filter) {
        List<Filter> filters = app.getFiltersLayout().getFilter();
        Filter filterValidate = filters.stream().filter(filter1 -> filter1.getId().equals(filter.getId())).findFirst().orElse(null);

        if(filterValidate == null){
            filters.add(filter);
            return "Success: Filtro con id: " + filter.getId() + " guardado correctamente";
        }else{
            return "Error: No se puede guardar el filtro, registro ya existente";
        }
    }

    @Override
    public String update(Filter filter) {
        List<Filter> filters = app.getFiltersLayout().getFilter();
        Filter filterValidate = filters.stream().filter(filter1 -> filter1.getId().equals(filter.getId())).findFirst().orElse(null);

        if(filterValidate == null)
            return "Error: No se puede actualizar el filtro, registro inexistente";
        else{
            filters.remove(filterValidate);
            filters.add(filter);
            return "Success: Filtro con id: " + filter.getId() + " actualizado correctamente";
        }
    }

    @Override
    public String delete(Long id) {
        List<Filter> filters = app.getFiltersLayout().getFilter();
        Filter filterValidate = filters.stream().filter(filter1 -> filter1.getId().equals(id)).findFirst().orElse(null);

        if(filterValidate == null)
            return "Error: No se puede eliminar el filtro, registro inexistente";
        else{
            filters.remove(filterValidate);
            return "Success: Filtro con id: " + id + " eliminado correctamente";
        }
    }
}
