package com.read.app.schedule;

import com.read.app.App;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Calendar;
import java.util.stream.Collectors;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Log4j2
public class ReadLayoutSchedule implements Job {

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
        } catch (SchedulerException e) {
            log.error("Error al iniciar la tarea: " + e.getMessage());
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        App app = App.getInstance();
        app.start();
    }
}
