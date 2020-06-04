package com.read.app.repository;

import com.read.app.App;
import com.read.app.model.Field;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class LayoutRepositoryImp implements LayoutRepository
{
    private App app = App.getInstance();
    List<Field> fieldsIn = app.getLayout().getLayoutIn().getFields().getField();
    List<Field> fieldsOut = app.getLayout().getLayoutOut().getFields().getField();

    @Override
    public List<Field> findLayoutInFields()
    {
        return fieldsIn;
    }

    @Override
    public List<Field> findLayoutOutFields()
    {
        return fieldsOut;
    }

    @Override
    public Field findInFieldById(Long id)
    {
        return fieldsIn.stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Field findOutFieldById(Long id)
    {
        return fieldsOut.stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String saveInField(Field field)
    {
        try
        {
            field.setId((long) fieldsIn.size() + 1);
            fieldsIn.add(field);
            app.updateLayoutXml();
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
            field.setId((long) fieldsOut.size() + 1);
            fieldsOut.add(field);
            app.updateLayoutXml();
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
                fieldsIn.remove(fieldsIn.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().get());
                fieldsIn.add(field);
                app.sortLayout();
                app.updateLayoutXml();
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
                fieldsOut.remove(fieldsOut.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().get());
                fieldsOut.add(field);
                app.sortLayout();
                app.updateLayoutXml();
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
                fieldsIn.remove(fieldsIn.stream().filter(field1 -> field1.getId().equals(id)).findFirst().get());
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
                fieldsOut.remove(fieldsOut.stream().filter(field1 -> field1.getId().equals(id)).findFirst().get());
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
        return fieldsIn.stream().anyMatch(field -> field.getId().equals(id));
    }

    @Override
    public boolean existsOut(Long id)
    {
        return fieldsOut.stream().anyMatch(field -> field.getId().equals(id));
    }
}
