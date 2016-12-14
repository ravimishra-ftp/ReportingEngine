package com.hatcheryhub.service.impl;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.hatcheryhub.dao.ReportingJobDao;
import com.hatcheryhub.entities.ReportingDetail;
import com.hatcheryhub.service.MailService;
import com.hatcheryhub.service.SchedulingService;

@Service("schedulingService")
public class SchedulingServiceImpl implements SchedulingService{
	
	private static final Log log = LogFactory.getLog(SchedulingServiceImpl.class);
	
	@Resource(name = "reportingJobDao")
	ReportingJobDao reportingJobDao;
	
    @Resource(name = "mailService")
    MailService mailService;
	
	public static TaskScheduler scheduler;
	
    @Override
    public void schedule(ReportingDetail reportingDetail) throws Exception {
        String body = null;
        
        if(reportingDetail == null){
            List<ReportingDetail> reportingDetailList = reportingJobDao.getAllReportingJob();
            for(ReportingDetail rd : reportingDetailList){
                body = this.getMailBody(rd.getSqlQuery());
                this.createAndScheduleJob(rd, body);
            }
        }else{
            body = this.getMailBody(reportingDetail.getSqlQuery());
            this.createAndScheduleJob(reportingDetail, body);
        }
    }

    private static final class Job implements Runnable {
        
        private final MailService mailService;
        
        private final String to;
        private final String sub;
        private final String body;
        
        public Job(MailService mailService, String to, String sub, String body){
            this.mailService = mailService;
            this.to = to;
            this.sub = sub;
            this.body = body;
        }
        
        @Override
        public void run() {
            mailService.sendMail(this.to, this.sub, this.body);
            log.info("Main Successfully sent at " + new Date() + " to " + this.to);
        }

    }
    
    private void createAndScheduleJob(ReportingDetail reportingDetail, String body){
        Job job = new Job(mailService, reportingDetail.getEmailId(), "PLEASE DO NOT REPLY", body);
        Trigger trigger = new CronTrigger(reportingDetail.getCronExpression(), TimeZone.getTimeZone("IST"));
        scheduler.schedule(job, trigger);
    }
    
    private String getMailBody(String sqlQuery) throws Exception{
        StringBuilder body = new StringBuilder();
        List<Object[]> bodyList = reportingJobDao.getReportingBody(sqlQuery);
        if(bodyList!=null && bodyList.size() > 0){
            body.append(MailServiceImpl.startBody);
            for(Object[] obj : bodyList){
                body.append("<tr>");
                for(int i=0; i<obj.length; i++){
                    if(obj[i]!=null){
                        body.append("<td>"+String.valueOf(obj[i])+"</td>");
                    }else{
                        body.append("<td>null</td>");
                    }
                }
                body.append("</tr>");
            }
        }
        body.append(MailServiceImpl.endBody);
        return body.toString();
    }

}
