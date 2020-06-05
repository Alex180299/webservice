package com.read.app.schedule;

import com.read.app.App;
import com.read.app.service.LayoutsService;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.*;

public class ReadLayoutSchedule implements Job {

    private static final Logger log = Logger.getLogger(ReadLayoutSchedule.class);

    public void start(ScheduleBuilder scheduleBuilder){
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.clear();
            Date runtime = DateBuilder.evenSecondDateAfterNow();

            JobDetail job = JobBuilder.newJob(ReadLayoutSchedule.class)
                    .withIdentity("readXmlProcess")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("Trigger XML process")
                    .withSchedule(scheduleBuilder)
                    .startAt(runtime)
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            log.info("Tarea programada de lectura de layouts iniciada");
        } catch (SchedulerException e) {
            log.error("Error al iniciar la tarea: " + e.getMessage());
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LayoutsService.start();
    }
}
