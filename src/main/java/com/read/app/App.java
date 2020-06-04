package com.read.app;

import com.read.app.model.*;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.ReadLayouts;
import lombok.extern.log4j.Log4j2;
import org.quartz.ScheduleBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Log4j2
public class App
{

    private App()
    {
    }

    private static final App INSTANCE = new App();

    public static App getInstance()
    {
        return INSTANCE;
    }

    private static ReadLayouts readLayouts = new ReadLayouts();
    private Layout layout;
    private Filters filters;

    public void start()
    {
        layout = readLayouts.readLayout();
        filters = readLayouts.readFilters();

        sortLayout();
        sortFilters();
    }

    public void sortLayout()
    {
        layout.getLayoutIn().getFields().getField().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
        layout.getLayoutIn().getFields().getField().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
    }

    public void sortFilters()
    {
        filters.getFilter().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
    }

    public void updateLayoutXml()
    {
        sortLayout();
        readLayouts.writeLayout(layout);
    }

    public void updateFiltersXml()
    {
        sortFilters();
        readLayouts.writeFilters(filters);
    }

    public static void init()
    {
        Configuration configuration = null;
        try
        {
            configuration = readLayouts.readConfig();
            ReadLayoutSchedule readLayoutSchedule = new ReadLayoutSchedule();
            ScheduleBuilder scheduleBuilder = null;

            log.info("Configuracion cargada");

            long timeUnit = configuration.getReload().getUnit().toMillis(configuration.getReload().getQuantity());
            scheduleBuilder = simpleSchedule().withIntervalInMilliseconds(timeUnit).repeatForever();
            readLayoutSchedule.start(scheduleBuilder);
        }
        catch (Exception e)
        {
            log.error("Error al extraer la configuración: " + e.getMessage());
        }
    }

    public List<Field> getLayoutIn()
    {
        return layout.getLayoutIn().getFields().getField();
    }

    public List<Field> getLayoutOut()
    {
        return layout.getLayoutOut().getFields().getField();
    }

    public List<Filter> getFilters()
    {
        return filters.getFilter();
    }
}
