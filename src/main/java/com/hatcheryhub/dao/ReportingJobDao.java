package com.hatcheryhub.dao;

import java.util.List;

import com.hatcheryhub.entities.ReportingDetail;


public interface ReportingJobDao {
	public void saveReportingJob(ReportingDetail reportingDetail) throws Exception;
	public List<ReportingDetail> getAllReportingJob() throws Exception;
	public List<Object[]> getReportingBody(String sqlQuery) throws Exception;
}
