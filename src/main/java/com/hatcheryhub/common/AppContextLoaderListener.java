package com.hatcheryhub.common;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import com.hatcheryhub.service.SchedulingService;
import com.hatcheryhub.service.impl.SchedulingServiceImpl;

@Component
public class AppContextLoaderListener implements ApplicationListener<ContextRefreshedEvent>{
    private static final Log log = LogFactory.getLog(AppContextLoaderListener.class);
    
    private static boolean eventFlag = false;
    
    @Resource(name = "schedulingService")
    public SchedulingService schedulingService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if(!eventFlag){
            log.info( "Loading application start-up events..." );
            ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
            SchedulingServiceImpl.scheduler = new ConcurrentTaskScheduler(localExecutor);
            try {
                schedulingService.schedule(null);
            } catch (Exception e) {
                log.error("Problem loding application even.", e);
            }
            eventFlag = true;
            log.info( "Application start-up events successfully loaded." );
        }
    }
    
}
