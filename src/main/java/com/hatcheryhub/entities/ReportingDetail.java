package com.hatcheryhub.entities;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="REPORTING_DETAIL")
public class ReportingDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
	private int id;
	
	@Column(name="SQL_QUERY") 
	private String sqlQuery;

	@Column(name="EMAIL_ID") 
    private String emailId;
	
	@Column(name="CRON_EXPRESSION") 
    private String cronExpression;
	
	@Transient 
	private String frequency;
	
	@Transient
	private String time;
	
	@Transient 
    private String [] daysOfWeek;
	
	@Transient
	private String dateOfMonth;
    
	@Transient
	private String dateAndMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getDateOfMonth() {
        return dateOfMonth;
    }

    public void setDateOfMonth(String dateOfMonth) {
        this.dateOfMonth = dateOfMonth;
    }

    public String getDateAndMonth() {
        return dateAndMonth;
    }

    public void setDateAndMonth(String dateAndMonth) {
        this.dateAndMonth = dateAndMonth;
    }

    @Override
    public String toString() {
        return "ReportingDetail [id=" + id + ", sqlQuery=" + sqlQuery
                + ", emailId=" + emailId + ", cronExpression=" + cronExpression
                + ", frequency=" + frequency + ", time=" + time
                + ", daysOfWeek=" + Arrays.toString(daysOfWeek)
                + ", dateOfMonth=" + dateOfMonth + ", dateAndMonth="
                + dateAndMonth + "]";
    }

}
