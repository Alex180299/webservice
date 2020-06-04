package com.read.app;

import com.read.app.model.*;
import com.read.app.schedule.ReadLayoutSchedule;
import com.read.app.service.ReadLayouts;
import lombok.extern.log4j.Log4j2;
import org.quartz.ScheduleBuilder;

import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Log4j2
public class App
{
    //static Logger logger = Logger.getLogger(App.class);

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
        layout.getLayoutIn().getFields().getField().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);

        filters = readLayouts.readFilters();
        filters.getFilter().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
    }

    public static void init()
    {
        Configuration configuration = null;
        try
        {
            configuration = readLayouts.readConfig();
        }
        catch (Exception e)
        {
            log.error("Error al extraer la configuración: " + e.getMessage());
        }

        ReadLayoutSchedule readLayoutSchedule = new ReadLayoutSchedule();
        ScheduleBuilder scheduleBuilder = null;
        log.info("Configuracion cargada");
        long timeUnit = configuration.getReload().getUnit().toMillis(configuration.getReload().getQuantity());
        scheduleBuilder = simpleSchedule().withIntervalInMilliseconds(timeUnit).repeatForever();

        readLayoutSchedule.start(scheduleBuilder);
    }

    private void updateLayoutXml()
    {
        layout.getLayoutIn().getFields().getField().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
        readLayouts.writeLayout(layout);
    }

    private void updateFiltersXml()
    {
        filters.getFilter().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
        readLayouts.writeFilters(filters);
    }

    public List<Filter> findAll()
    {
        return getFilters().getFilter();
    }

    public Filter findById(Long id)
    {
        return getFilters().getFilter().stream().filter(filter -> filter.getId().equals(id)).findFirst().orElse(null);
    }

    public String save(Filter filter)
    {
        List<Filter> filters = getFilters().getFilter();
        filter.setId((long) filters.size() + 1);
        filters.add(filter);
        updateFiltersXml();
        return "Success: Filtro con id: " + filter.getId() + " guardado correctamente";

    }

    public String update(Filter filter)
    {
        List<Filter> filters = getFilters().getFilter();
        Filter filterValidate = filters.stream().filter(filter1 -> filter1.getId().equals(filter.getId())).findFirst().orElse(null);

        if (filterValidate == null)
        {
            return "Error: No se puede actualizar el filtro, registro inexistente";
        }
        else
        {
            filters.remove(filterValidate);
            filters.add(filter);
            updateFiltersXml();
            return "Success: Filtro con id: " + filter.getId() + " actualizado correctamente";
        }
    }

    public String delete(Long id)
    {
        List<Filter> filters = getFilters().getFilter();
        Filter filterValidate = filters.stream().filter(filter1 -> filter1.getId().equals(id)).findFirst().orElse(null);

        if (filterValidate == null)
        {
            return "Error: No se puede eliminar el filtro, registro inexistente";
        }
        else
        {
            filters.remove(filterValidate);
            updateFiltersXml();
            return "Success: Filtro con id: " + id + " eliminado correctamente";
        }
    }

    public List<Field> findLayoutInFields()
    {
        return getLayout().getLayoutIn().getFields().getField();
    }


    public List<Field> findLayoutOutFields()
    {
        return getLayout().getLayoutOut().getFields().getField();
    }

    public Field findInFieldById(Long id)
    {
        return getLayout().getLayoutIn().getFields().getField().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    public Field findOutFieldById(Long id)
    {
        return getLayout().getLayoutOut().getFields().getField().stream().filter(field -> field.getId().equals(id)).findFirst().orElse(null);
    }

    public String saveInField(Field field)
    {
        List<Field> fields = getLayout().getLayoutIn().getFields().getField();
        field.setId((long) fields.size() + 1);
        fields.add(field);
        updateLayoutXml();
        return "Success: Campo con el id: " + field.getId() + " guardado correctamente";

    }

    public String saveOutField(Field field)
    {
        List<Field> fields = getLayout().getLayoutOut().getFields().getField();
        field.setId((long) fields.size() + 1);
        fields.add(field);
        updateLayoutXml();
        return "Success: Campo con el id: " + field.getId() + " guardado correctamente";
    }

    public String updateInField(Field field)
    {
        List<Field> fields = getLayout().getLayoutIn().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().orElse(null);

        if (fieldValidate != null)
        {
            fields.remove(fieldValidate);
            fields.add(field);
            updateLayoutXml();
            return "Success: Campo actualizado correctamente";
        }
        else
        {
            return "Error: No se puede actualizar el campo, registro inexistente";
        }
    }

    public String updateOutField(Field field)
    {
        List<Field> fields = getLayout().getLayoutOut().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(field.getId())).findFirst().orElse(null);

        if (fieldValidate != null)
        {
            fields.remove(fieldValidate);
            fields.add(field);
            updateLayoutXml();
            return "Success: Campo actualizado correctamente";
        }
        else
        {
            return "Error: No se puede actualizar el campo, registro inexistente";
        }
    }

    public String deleteInField(Long id)
    {
        List<Field> fields = getLayout().getLayoutIn().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(id)).findFirst().orElse(null);

        if (fieldValidate != null)
        {
            fields.remove(fieldValidate);
            updateLayoutXml();
            return "Success: Campo eliminado correctamente";
        }
        else
        {
            return "Error: No se puede eliminar el filtro, registro inexistente";
        }
    }

    public String deleteOutField(Long id)
    {
        List<Field> fields = getLayout().getLayoutOut().getFields().getField();
        Field fieldValidate = fields.stream().filter(field1 -> field1.getId().equals(id)).findFirst().orElse(null);

        if (fieldValidate != null)
        {
            fields.remove(fieldValidate);
            updateLayoutXml();
            return "Success: Campo eliminado correctamente";
        }
        else
        {
            return "Error: No se puede eliminar el campo, registro inexistente";
        }
    }

    public Layout getLayout()
    {
        return layout;
    }

    public Filters getFilters()
    {
        return filters;
    }
}
