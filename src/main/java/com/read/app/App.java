package com.read.app;

import com.read.app.model.Configuration;
import com.read.app.model.FiltersLayout;
import com.read.app.model.Layout;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.ReadLayouts;
import lombok.extern.log4j.Log4j2;
import org.quartz.ScheduleBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Log4j2
public class App {

    private App(){}

    private static final App INSTANCE = new App();

    public static App getInstance() {
        return INSTANCE;
    }

    private static ReadLayouts readLayouts = new ReadLayouts();
    private Layout layout;
    private FiltersLayout filtersLayout;

    public void start(){
        layout = readLayouts.readLayout();
        filtersLayout = readLayouts.readFilters();

        System.out.println(layout.getLayoutIn().getFields().getField().get(0).getName());
    }

    public static void init(){
        Configuration configuration = null;
        try {
             configuration = readLayouts.readConfig();
        }catch (Exception e){
            log.error("Error al extraer la configuración: " + e.getMessage());
        }

        System.out.println(configuration.getReload().getUnit());
        ReadLayoutSchedule readLayoutSchedule = new ReadLayoutSchedule();
        ScheduleBuilder scheduleBuilder = null;

        long timeUnit = configuration.getReload().getUnit().toMillis(configuration.getReload().getQuantity());
        scheduleBuilder = simpleSchedule().withIntervalInMilliseconds(timeUnit).repeatForever();

        readLayoutSchedule.start(scheduleBuilder);
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
