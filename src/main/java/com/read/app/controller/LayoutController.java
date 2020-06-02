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

    @GetMapping("/in/field/{id}")
    public Field getInFieldById(@PathVariable Long id){
        return layoutServiceImp.findInFieldById(id);
    }

    @GetMapping("/in/field/{id}")
    public Field getOutFieldById(@PathVariable Long id){
        return layoutServiceImp.findOutFieldById(id);
    }

    @PostMapping("/in/field")
    public String saveInField(@RequestParam Field field){
        return "{ " + layoutServiceImp.saveInField(field)+" }";
    }

    @PostMapping("/out/field")
    public String saveOutField(@RequestParam Field field){
        return "{ " + layoutServiceImp.saveOutField(field)+" }";
    }

    @DeleteMapping("/in/field/{id}")
    public String deleteInField(@PathVariable Long id){
        return "{ " + layoutServiceImp.deleteInField(id) + " }";
    }

    @DeleteMapping("/out/field/{id}")
    public String deleteOutField(@PathVariable Long id){
        return "{ " + layoutServiceImp.deleteOutField(id) + " }";
    }

    @PutMapping("/in/field")
    public String updateInField(@RequestParam Field field){
        return "{ " + layoutServiceImp.updateInField(field) + " }";
    }

    @PutMapping("/out/field")
    public String updateOutField(@RequestParam Field field){
        return "{ " + layoutServiceImp.updateOutField(field) + " }";
    }

}
