package com.read.app;

import com.read.app.model.*;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.LayoutsService;
import com.read.app.service.ReadLayouts;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.quartz.ScheduleBuilder;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class App
{
    private static final Logger log = Logger.getLogger(App.class);
    private static ReadLayouts readLayouts = new ReadLayouts();

    public static void main(String[] args)
    {
        init();
    }

    public static void init()
    {
        DOMConfigurator.configure("./config/log4j2.xml");

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

        LayoutsService.start();
    }
}
