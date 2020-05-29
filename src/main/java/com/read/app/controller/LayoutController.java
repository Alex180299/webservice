package com.read.app.controller;

import com.read.app.model.Field;
import com.read.app.service.LayoutServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/layout")
public class LayoutController {

    @Autowired
    LayoutServiceImp layoutServiceImp;

    @GetMapping("/in/fields")
    public List<Field> getLayoutInFields(){
        return layoutServiceImp.findLayoutInFields();
    }

    @GetMapping("/out/fields")
    public List<Field> getLayoutOutFields(){
        return layoutServiceImp.findLayoutOutFields();
    }

    @PostMapping("/in/field")
    public Field saveInField(@RequestParam Field field){
        return layoutServiceImp.save(field);
    }

    @GetMapping("/in/field/{id}")
    public Field getInFieldById(@PathVariable Long id){
        return layoutServiceImp.findInFieldById(id);
    }

    @DeleteMapping("/in/field")
    public String deleteInField(@RequestParam Field field){
        layoutServiceImp.delete(field);
        return "{ success: Eliminado correctamente }";
    }

    @PutMapping("/in/field")
    public Field updateInField(@RequestParam Field field){
        return layoutServiceImp.save(field);
    }


}
