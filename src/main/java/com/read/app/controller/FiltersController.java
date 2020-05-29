package com.read.app.controller;

import com.read.app.model.Filter;
import com.read.app.service.FiltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FiltersController {

    @Autowired
    private FiltersService filtersService;

    @GetMapping("/filters")
    public List<Filter> getLayoutOutFilters(){
        return filtersService.findAll();
    }

    @PostMapping("/filter")
    public Filter saveFilter(@RequestParam Filter filter){
        return filtersService.save(filter);
    }

    @GetMapping("/filter/{id}")
    public Filter getFilterById(@PathVariable Long id){
        return filtersService.findById(id);
    }

    @DeleteMapping("/filter")
    public String deleteFilter(@RequestParam Filter filter){
        filtersService.delete(filter);
        return "{ success: Eliminado correctamente }";
    }

    @PutMapping("/filter")
    public Filter updateFilter(@RequestParam Filter filter){
        return filtersService.save(filter);
    }

}
