package com.read.app;

import com.read.app.model.FiltersLayout;
import com.read.app.model.Layout;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.ReadLayouts;
import org.springframework.beans.factory.annotation.Autowired;

public class App {

    private App(){}

    private static final App INSTANCE = new App();

    public static App getInstance() {
        return INSTANCE;
    }

    ReadLayouts readLayouts = new ReadLayouts();
    private Layout layout;
    private FiltersLayout filtersLayout;

    public void start(){
        layout = readLayouts.readLayout();
        filtersLayout = readLayouts.readFilters();

        System.out.println(layout.getLayoutIn().getFields().getField().get(0).getName());
    }

    public static void init(){
        ReadLayoutSchedule readLayoutSchedule = new ReadLayoutSchedule();
        readLayoutSchedule.start();
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public FiltersLayout getFiltersLayout() {
        return filtersLayout;
    }

    public void setFiltersLayout(FiltersLayout filtersLayout) {
        this.filtersLayout = filtersLayout;
    }
}
