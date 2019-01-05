package com.accp.chatroom.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.chatroom.pojo.TblJob;


public interface ITblJobDao {

	public List<TblJob> queryAllJob();

	public int saveJob(@Param("job") TblJob job);

	public int deleteJob(@Param("jname") String jname, @Param("jgroup") String jgroup);

	public int updateJobState(@Param("jname") String jname, @Param("jgroup") String jgroup,
			@Param("jstate") Integer jstate);
	
	public void deleteMessage();
}
