package com.hatcheryhub.common;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hatcheryhub.entities.ReportingDetail;

public abstract class AbstractCommonController {
	
    protected final Log log = LogFactory.getLog(this.getClass());

  //Instead of manual validation Spring validation can also be used. 
    protected String validateRequest(ReportingDetail rd){
        Pattern isValideEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        StringBuilder error = new StringBuilder();
        if(rd != null){
            if(StringUtils.isEmpty(rd.getSqlQuery())) {
                error.append("<br>Fill SQL Query"); 
                log.info("@sql");
                }
            if(StringUtils.isEmpty(rd.getEmailId()) || !isValideEmail.matcher(rd.getEmailId()).find()) {
                error.append("<br>Fill Correct Email");
                log.info("@Email");
                }
            if(StringUtils.isEmpty(rd.getFrequency())) {
                error.append("<br>Select Frequency");
                log.info("@Frequency");
            }else{
                if(rd.getFrequency().equals("1") && StringUtils.isEmpty(rd.getTime())) {
                    error.append("<br>Fill Time");
                    log.info("@Time");
                    }
                if(rd.getFrequency().equals("2") && (StringUtils.isEmpty(rd.getTime()) || rd.getDaysOfWeek().length < 0)) {
                    error.append("<br>Select any Week Day");
                    log.info("@Time&Day");
                    }
                if(rd.getFrequency().equals("3") && (StringUtils.isEmpty(rd.getTime()) || StringUtils.isEmpty(rd.getDateOfMonth()))) {
                    error.append("<br>Fill Time & Date of Month");
                    log.info("@Time&Date");
                    }
                if(rd.getFrequency().equals("4") && (StringUtils.isEmpty(rd.getTime()) || StringUtils.isEmpty(rd.getDateAndMonth()))) {
                    error.append("<br>Fill Time, Date & Month");
                    log.info("@time&Date&Month");
                    }
            }
            if(StringUtils.isEmpty(rd.getFrequency()) && StringUtils.isEmpty(rd.getTime())){
                error.append("<br>Fill Time");
                log.info("@Time");
            }
        }else{
            error.append("All fields are manadatory");
            log.info("@All");
        }
        
        return error.toString();
    }
}
