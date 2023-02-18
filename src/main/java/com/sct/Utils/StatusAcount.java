package com.sct.Utils;

import java.util.Date;

public enum StatusAcount {
	ACOUNTSUCCESS("SUCCESS",1,new Date()),
	ACOUNTFAIL("FAIL",2,new Date()),
	ACOUNTLOCK("LOCK",3,new Date()),
	ACOUNTNOTEXIT("NOTEXIT",4,new Date());
	
	private String status_name;
    private int code;
    private Date curredate;
	
	private StatusAcount(String status_name, int code, Date curredate) {
        this.status_name = status_name;
        this.code = code;
        this.curredate = curredate;
    }

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Date getCurredate() {
		return curredate;
	}

	public void setCurredate(Date curredate) {
		this.curredate = curredate;
	}
	
	
}
