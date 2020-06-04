package com.read.app.controller;

import com.read.app.model.Filter;
import com.read.app.service.FiltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FiltersController
{

    @Autowired
    private FiltersService filtersService;

    @GetMapping("/filters")
    public List<Filter> getLayoutOutFilters()
    {
        return filtersService.findAll();
    }

    @PostMapping("/filter")
    public String saveFilter(@RequestParam Filter filter)
    {
        return "{ " + filtersService.save(filter) + " }";
    }

    @GetMapping("/filter/{id}")
    public Filter getFilterById(@PathVariable Long id)
    {
        return filtersService.findById(id);
    }

    @DeleteMapping("/filter/{id}")
    public String deleteFilter(@PathVariable Long id)
    {
        return "{ + " + filtersService.delete(id) + " }";
    }

    @PutMapping("/filter")
    public String updateFilter(@RequestParam Filter filter)
    {
        return "{ + " + filtersService.update(filter) + " }";
    }

}
