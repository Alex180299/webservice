package com.read.app;

import com.read.app.configuration.ConfigurationModule;
import com.read.app.model.*;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.LayoutsService;
import com.read.app.service.ReadLayouts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.quartz.ScheduleBuilder;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class App
{

    private static final Logger log = LogManager.getLogger(App.class.getSimpleName());

    private static ReadLayouts readLayouts = new ReadLayouts();

    public static void main(String[] args)
    {
        ConfigurationModule.configurationFile = "./config/configuration.xml";
        ConfigurationModule.layoutFieldsFile = "./config/layout.xml";
        ConfigurationModule.layoutFiltersFile = "./config/filters.xml";
        init();
    }

    public static void init()
    {
        Configuration configuration;
        try
        {
            try
            {
                LoggerContext context = LoggerContext.getContext(true);
                context.reconfigure();
            }
            catch (Exception e)
            {
                log.fatal("No se puede configurar el archivo logger debiado a : " + e.getMessage() + " la aplicacion se cerrara ");
                System.exit(-1);
            }
            
            log.info("Se ha cargado el logger correctamente");

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
