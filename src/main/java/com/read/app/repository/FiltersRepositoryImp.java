package com.read.app.repository;

import com.read.app.model.Filter;
import com.read.app.model.Layout;
import com.read.app.model.LayoutLists;
import com.read.app.service.LayoutsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FiltersRepositoryImp implements FiltersRepository
{
    private static Logger log;

    public FiltersRepositoryImp(){
        log = LogManager.getLogger(FiltersRepositoryImp.class);
    }

    @Override
    public List<Filter> findAll()
    {
        return LayoutLists.getFilters();
    }

    @Override
    public Filter findById(Long id)
    {
        return LayoutLists.getFilters().stream().filter(filter -> filter.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String save(Filter filter)
    {
        try
        {
            filter.setId(LayoutLists.getNextFilterId());
            LayoutLists.getFilters().add(filter);
            LayoutsService.updateFiltersXml();
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
                LayoutLists.getFilters().remove(LayoutLists.getFilters().stream().filter(filter1 -> filter1.getId().equals(filter.getId())).findFirst().get());
                LayoutLists.getFilters().add(filter);
                LayoutLists.sortFilters();
                LayoutsService.updateFiltersXml();
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
                LayoutLists.getFilters().remove(LayoutLists.getFilters().stream().filter(filter -> filter.getId().equals(id)).findFirst().get());
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
        return LayoutLists.getFilters().stream().anyMatch(filter -> filter.getId().equals(id));
    }
}
