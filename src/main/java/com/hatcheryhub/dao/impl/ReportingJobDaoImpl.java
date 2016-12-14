package com.hatcheryhub.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hatcheryhub.dao.ReportingJobDao;
import com.hatcheryhub.entities.ReportingDetail;

public class ReportingJobDaoImpl implements ReportingJobDao{
	
	private static final Log log = LogFactory.getLog(ReportingJobDaoImpl.class);

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportingDetail> getAllReportingJob() throws Exception {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<ReportingDetail> reportingDetailList = (List<ReportingDetail>) session.createQuery("FROM ReportingDetail").list();
		tx.commit();
		session.close();
		log.info("Successfully fetched "+reportingDetailList.size()+" events");
		return reportingDetailList;
	}
	

    @Override
    public void saveReportingJob(ReportingDetail reportingDetail) throws Exception {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(reportingDetail);
        tx.commit();
        session.close();
        log.info("Reporting job creation finished and committed");
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getReportingBody(String sqlQuery) throws Exception {
        List<Object[]> reportingBody = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        reportingBody = session.createSQLQuery(sqlQuery).list();
        tx.commit();
        session.close();
        return reportingBody;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
