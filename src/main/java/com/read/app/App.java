package com.read.app;

import com.read.app.model.*;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.ReadLayouts;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.quartz.ScheduleBuilder;

import java.io.FileInputStream;
import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class App
{
    private static final Logger LOG = LogManager.getLogger(App.class.getSimpleName());
    private static Layout layout;
    private static Filters filters;

    static
    {
        try
        {
            LoggerContext context = LoggerContext.getContext(false);
            context.start(new XmlConfiguration(context, new ConfigurationSource(new FileInputStream("./config/log4j2.xml"))));
        }
        catch (Exception e)
        {
            LOG.fatal("No se puede configurar el archivo logger debiado a : " + e.getMessage() + " la aplicacion se cerrara ");
            System.exit(-1);
        }
        LOG.info("Se ha cargado el logger correctamente");
    }

    private App()
    {
    }

    private static final App INSTANCE = new App();

    public static App getInstance()
    {
        return INSTANCE;
    }

    private static ReadLayouts readLayouts = new ReadLayouts();


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

    public static void main(String[] args)
    {
        Configuration configuration = null;
        try
        {
            configuration = readLayouts.readConfig();
            ReadLayoutSchedule readLayoutSchedule = new ReadLayoutSchedule();
            ScheduleBuilder scheduleBuilder = null;

            LOG.info("Configuracion cargada");

            long timeUnit = configuration.getReload().getUnit().toMillis(configuration.getReload().getQuantity());
            scheduleBuilder = simpleSchedule().withIntervalInMilliseconds(timeUnit).repeatForever();
            readLayoutSchedule.start(scheduleBuilder);
        }
        catch (Exception e)
        {
            LOG.error("Error al extraer la configuración: " + e.getMessage());
        }

        getInstance().start();
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
