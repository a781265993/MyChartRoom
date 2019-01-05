package com.accp.chatroom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.chatroom.dao.ITblJobDao;
import com.accp.chatroom.pojo.TblJob;



@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, isolation = Isolation.READ_COMMITTED)
public class TblJobBiz {

	@Autowired
	private ITblJobDao jobDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void addJob(TblJob job) {
		jobDao.saveJob(job);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void removeJob(String jname, String jgroup) {
		jobDao.deleteJob(jname, jgroup);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void modifyJobState(String jname, String jgroup, Integer jstate) {
		jobDao.updateJobState(jname, jgroup, jstate);
	}

	public List<TblJob> findJobList() {
		return jobDao.queryAllJob();
	}
	
	public void deleteMessage() {
		jobDao.deleteMessage();
	}
}
