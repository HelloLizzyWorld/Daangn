package com.elite.vo;

public class DaangnReviewVO {

	String rid, id, rstate, rdetail, pid, cors, rcomment ,rcommentList;
	
	
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRstate() {
		return rstate;
	}

	public void setRstate(String rstate) {
		this.rstate = rstate;
	}
	
	

	public String getRcomment() {
		
		
		return rcomment;
	}

	public void setRcomment(String rcomment) {
		this.rcomment = rcomment.replace(",", "<br>");
	}

	public String getRcommentList() {
		return rcommentList;
	}

	public void setRcommentList(String rcommentList) {
		this.rcommentList = rcommentList;
	}

	public String getRdetail() {
		return rdetail;
	}

	public void setRdetail(String rdetail) {
		this.rdetail = rdetail;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCors() {
		return cors;
	}

	public void setCors(String cors) {
		this.cors = cors;
	}

	
	
	
}
