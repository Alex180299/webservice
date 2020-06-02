package com.read.app.service;

import com.read.app.App;
import com.read.app.model.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutServiceImp implements LayoutService {

    private App app = App.getInstance();

    @Override
    public List<Field> findLayoutInFields() {
        return app.getLayout().getLayoutIn().getFields().getField();
    }

    @Override
    public List<Field> findLayoutOutFields() {
        return app.getLayout().getLayoutOut().getFields().getField();
    }

    @Override
    public Field findInFieldById(Long id) {
        return app.getLayout().getLayoutIn().getFields().getField().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Field findOutFieldById(Long id) {
        return app.getLayout().getLayoutOut().getFields().getField().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String saveInField(Field field) {
        List<Field> fields = app.getLayout().getLayoutIn().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().orElse(null);

        if(fieldValidate == null){
            fields.add(field);
            return "Success: Campo guardado correctamente";
        }else{
            return "Error: No se puede guardar el campo, registro inexistente";
        }
    }

    @Override
    public String saveOutField(Field field) {
        List<Field> fields = app.getLayout().getLayoutOut().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().orElse(null);

        if(fieldValidate == null){
            fields.add(field);
            return "Success: Campo guardado correctamente";
        }else{
            return "Error: No se puede guardar el campo, registro inexistente";
        }
    }

    @Override
    public String updateInField(Field field) {
        List<Field> fields = app.getLayout().getLayoutIn().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().orElse(null);

        if(fieldValidate != null){
            fields.remove(fieldValidate);
            fields.add(field);
            return "Success: Campo actualizado correctamente";
        }else{
            return "Error: No se puede actualizar el campo, registro inexistente";
        }
    }

    @Override
    public String updateOutField(Field field) {
        List<Field> fields = app.getLayout().getLayoutOut().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().orElse(null);

        if(fieldValidate != null){
            fields.remove(fieldValidate);
            fields.add(field);
            return "Success: Campo actualizado correctamente";
        }else{
            return "Error: No se puede actualizar el campo, registro inexistente";
        }
    }

    @Override
    public String deleteInField(Long id) {
        List<Field> fields = app.getLayout().getLayoutIn().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(id)).findFirst().orElse(null);

        if(fieldValidate != null){
            fields.remove(fieldValidate);
            return "Success: Campo eliminado correctamente";
        }else{
            return "Error: No se puede eliminar el filtro, registro inexistente";
        }
    }

    @Override
    public String deleteOutField(Long id) {
        List<Field> fields = app.getLayout().getLayoutOut().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(id)).findFirst().orElse(null);

        if(fieldValidate != null){
            fields.remove(fieldValidate);
            return "Success: Campo eliminado correctamente";
        }else{
            return "Error: No se puede eliminar el campo, registro inexistente";
        }
    }
}
