package com.read.app.repository;

import com.read.app.App;
import com.read.app.model.Field;
import com.read.app.model.LayoutLists;
import com.read.app.service.LayoutsService;
import org.apache.log4j.Logger;
import java.util.List;


public class LayoutRepositoryImp implements LayoutRepository
{

    private static Logger log;

    public LayoutRepositoryImp(){
        log = Logger.getLogger(FiltersRepositoryImp.class);
    }

    @Override
    public List<Field> findLayoutInFields()
    {
        return LayoutLists.getLayoutIn();
    }

    @Override
    public List<Field> findLayoutOutFields()
    {
        return LayoutLists.getLayoutOut();
    }

    @Override
    public Field findInFieldById(Long id)
    {
        return LayoutLists.getLayoutIn().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Field findOutFieldById(Long id)
    {
        return LayoutLists.getLayoutOut().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String saveInField(Field field)
    {
        try
        {
            field.setId(LayoutLists.getNextLayoutInId());
            LayoutLists.getLayoutIn().add(field);
            LayoutsService.updateLayoutXml();
        }
        catch (Exception e)
        {
            log.error("Error al insertar el campo: " + e.getMessage());
            return "Error: Error al insertar el campo";
        }

        return "Success: Campo con id: " + field.getId() + " guardado correctamente";
    }

    @Override
    public String saveOutField(Field field)
    {
        try
        {
            field.setId(LayoutLists.getNextLayoutOutId());
            LayoutLists.getLayoutOut().add(field);
            LayoutsService.updateLayoutXml();
        }
        catch (Exception e)
        {
            log.error("Error al insertar filtro: " + e.getMessage());
            return "Error: Error al insertar filtro";
        }

        return "Success: Filtro con id: " + field.getId() + " guardado correctamente";
    }

    @Override
    public String updateInField(Field field)
    {
        try{
            if(existsIn(field.getId())){
                LayoutLists.getLayoutIn().remove(LayoutLists.getLayoutIn().stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().get());
                LayoutLists.getLayoutIn().add(field);
                LayoutLists.sortLayout();
                LayoutsService.updateLayoutXml();
                return "Success: Campo en el layout de entrada con el id: " + field.getId() + " actualizado correctamente";
            }else{
                return "Error: Error al actualizar el campo en el layout de entrada";
            }
        }catch (Exception e){
            log.error("Error al actualizar el campo en el layout de entrada: " + e.getMessage());
            return "Error: Error al actualizar el campo en el layout de entrada";
        }
    }

    @Override
    public String updateOutField(Field field)
    {
        try{
            if(existsIn(field.getId())){
                LayoutLists.getLayoutOut().remove(LayoutLists.getLayoutOut().stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().get());
                LayoutLists.getLayoutOut().add(field);
                LayoutLists.sortLayout();
                LayoutsService.updateLayoutXml();
                return "Success: Campo en el layout de salida con el id: " + field.getId() + " actualizado correctamente";
            }else{
                return "Error: Error al actualizar el campo en el layout de salida";
            }
        }catch (Exception e){
            log.error("Error al actualizar el campo en el layout de salida: " + e.getMessage());
            return "Error: Error al actualizar el campo en el layout de salida";
        }
    }

    @Override
    public String deleteInField(Long id)
    {
        try{
            if(existsIn(id)){
                LayoutLists.getLayoutIn().remove(LayoutLists.getLayoutIn().stream().filter(field1 -> field1.getId().equals(id)).findFirst().get());
                return "Success: Campo en el layout de salida con el id: " + id + " eliminado correctamente";
            }else{
                return "Error: Error al eliminado el campo en el layout de salida";
            }
        }catch (Exception e){
            log.error("Error al eliminado el campo en el layout de salida: " + e.getMessage());
            return "Error: Error al eliminado el campo en el layout de salida";
        }
    }

    @Override
    public String deleteOutField(Long id)
    {
        try{
            if(existsIn(id)){
                LayoutLists.getLayoutOut().remove(LayoutLists.getLayoutOut().stream().filter(field1 -> field1.getId().equals(id)).findFirst().get());
                return "Success: Campo en el layout de salida con el id: " + id + " eliminado correctamente";
            }else{
                return "Error: Error al eliminado el campo de salida";
            }
        }catch (Exception e){
            log.error("Error al eliminado el campo de salida: " + e.getMessage());
            return "Error: Error al eliminado el campo de salida";
        }
    }

    @Override
    public boolean existsIn(Long id)
    {
        return LayoutLists.getLayoutIn().stream().anyMatch(field -> field.getId().equals(id));
    }

    @Override
    public boolean existsOut(Long id)
    {
        return LayoutLists.getLayoutOut().stream().anyMatch(field -> field.getId().equals(id));
    }
}
