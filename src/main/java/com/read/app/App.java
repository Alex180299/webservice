package com.read.app;

import com.read.app.configuration.ConfigurationModule;
import com.read.app.model.*;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.LayoutsService;
import com.read.app.service.ReadLayouts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.quartz.ScheduleBuilder;

import java.io.FileInputStream;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class App
{
    private static final Logger log = LogManager.getLogger(App.class.getSimpleName());

    private static ReadLayouts readLayouts = new ReadLayouts();

    public static void main(String[] args)
    {
        ConfigurationModule.configurationFile = "./config/configuration.xml";
        //ConfigurationModule.loggingFile = "./config/log4j.xml";
        ConfigurationModule.layoutFieldsFile = "./config/layout.xml";
        ConfigurationModule.layoutFiltersFile = "./config/filters.xml";
        init();
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

        LayoutsService.start();
    }
}
