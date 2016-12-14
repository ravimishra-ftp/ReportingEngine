package com.hatcheryhub.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hatcheryhub.common.AbstractCommonController;
import com.hatcheryhub.entities.ReportingDetail;
import com.hatcheryhub.service.ReportingJobService;
import com.hatcheryhub.service.SchedulingService;

@Controller
public class ReportingEngineController extends AbstractCommonController{
	
	private static final Log log = LogFactory.getLog(ReportingEngineController.class);

    @Resource(name = "reportingJobService")
    public ReportingJobService reportingJobService;
    
    @Resource(name = "schedulingService")
    public SchedulingService schedulingService;
	
    
    @RequestMapping(value = "/reportingEngine", method = { RequestMethod.GET })
    public ModelAndView reportingEngine(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView model = new ModelAndView("index");
        model.addObject("command", new ReportingDetail());
        model.addObject("msg", "Please Fill all Details");
        return model;
     }
    
    @RequestMapping(value = "/scheduleJob", method = { RequestMethod.POST })
    public ModelAndView scheduleJob(@ModelAttribute("SpringWeb")ReportingDetail reportingDetail) throws Exception{
        
        long startTime = System.currentTimeMillis();
        log.info("Request received  to create reporting job");
        //String errorMsg = validateRequest(reportingDetail);
        //if(errorMsg.length() < 0){
            try {
                reportingJobService.saveReportingJob(reportingDetail);
                schedulingService.schedule(reportingDetail);
                log.info("Reporting job successfully create and added to the scheduler.");
            } catch (Exception e) {
                log.error("Error occurred while create reporting job", e);
            } finally {
                log.info("Reporting job creation took " + (System.currentTimeMillis() - startTime) + " ms");
            }
             ModelAndView model = new ModelAndView("result");
             model.addObject("message", "Job Successfully Scheduled!!!");
             return model;
        /*}else{
            log.info("Validation Error" + errorMsg);
            ModelAndView model = new ModelAndView("index");
            model.addObject("command", new ReportingDetail());
            model.addObject("msg", errorMsg);
            return model;
        }*/
        
     }
}
