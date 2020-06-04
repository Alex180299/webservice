package com.read.app.repository;

import com.read.app.App;
import com.read.app.model.Filter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class FiltersRepositoryImp implements FiltersRepository
{
    private static App app = App.getInstance();

    @Override
    public List<Filter> findAll()
    {
        return app.getFilters();
    }

    @Override
    public Filter findById(Long id)
    {
        return app.getFilters().stream().filter(filter -> filter.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String save(Filter filter)
    {
        try
        {
            filter.setId((long) app.getFilters().size() + 1);
            app.getFilters().add(filter);
            app.updateFiltersXml();
        }
        catch (Exception e)
        {
            log.error("Error al insertar filtro: " + e.getMessage());
            return "Error: Error al insertar filtro";
        }

        return "Success: Filtro con id: " + filter.getId() + " guardado correctamente";
    }

    @Override
    public String update(Filter filter)
    {
        try{
            if (exists(filter.getId()))
            {
                app.getFilters().remove(app.getFilters().stream().filter(filter1 -> filter1.getId().equals(filter.getId())).findFirst().get());
                app.getFilters().add(filter);
                app.sortFilters();
                app.updateFiltersXml();
                return "Success: Filtro con id: " + filter.getId() + " actualizado correctamente";
            }
            else
            {
                return "Error: Error al actualizar el filtro";
            }
        }catch (Exception e){
            log.error("Error al actualizar el filtro: " + e.getMessage());
            return "Error: Error al actualizar el filtro";
        }
    }

    @Override
    public String delete(Long id)
    {
        try{
            if(exists(id)){
                app.getFilters().remove(app.getFilters().stream().filter(filter -> filter.getId().equals(id)).findFirst().get());
                return "Success: Filtro con id: " + id + " actualizado correctamente";
            }else{
                return "Error: Error el registro no existe";
            }
        }catch(Exception e){
            log.error("Error al eliminar el filtro: " + e.getMessage());
            return "Error: Error al eliminar el filtro";
        }
    }

    @Override
    public boolean exists(Long id)
    {
        return app.getFilters().stream().anyMatch(filter -> filter.getId().equals(id));
    }
}
