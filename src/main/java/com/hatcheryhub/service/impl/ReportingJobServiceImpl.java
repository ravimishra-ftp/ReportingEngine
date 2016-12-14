package com.hatcheryhub.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hatcheryhub.common.CronExpressionUtils;
import com.hatcheryhub.dao.ReportingJobDao;
import com.hatcheryhub.entities.ReportingDetail;
import com.hatcheryhub.service.ReportingJobService;

@Service("reportingJobService")
public class ReportingJobServiceImpl implements ReportingJobService{
	
	private static final Log log = LogFactory.getLog(ReportingJobServiceImpl.class);
	
	@Resource(name = "reportingJobDao")
	ReportingJobDao reportingJobDao;

    @Override
    public void saveReportingJob(ReportingDetail reportingDetail) throws Exception {
        reportingDetail.setCronExpression(this.getCronExpression(reportingDetail));
        reportingJobDao.saveReportingJob(reportingDetail);
        log.info("Generated Cron Expression: "+reportingDetail.getCronExpression());
    }
    
    private String getCronExpression(ReportingDetail rd){
        String cronExpression = null;
        String time[] = rd.getTime().split("-");
        if(rd.getFrequency().equals("1")){
            cronExpression = CronExpressionUtils.dailyAtHourAndMinute(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
        }else if(rd.getFrequency().equals("2")){
            cronExpression = CronExpressionUtils.weeklyOnHourAndMinuteOnGivenDaysOfWeek(Integer.parseInt(time[0]), Integer.parseInt(time[1]), rd.getDaysOfWeek());
        }else if(rd.getFrequency().equals("3")){
            cronExpression = CronExpressionUtils.monthlyOnDayAndHourAndMinute(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(rd.getDateOfMonth()));
        }else if(rd.getFrequency().equals("4")){
            String[] dateAndMonth = rd.getDateAndMonth().split("-"); 
            cronExpression = CronExpressionUtils.yearlyOnHourAndMinuteAndMonthAndDayOfMonth(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(dateAndMonth[0]), Integer.parseInt(dateAndMonth[1]));
        }
        return cronExpression;
    }

}
