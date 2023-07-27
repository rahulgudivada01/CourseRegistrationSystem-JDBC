package com.digit.crsAPP.beans;

public class Course {
	int cid;
	String cname;
	int fees;
	int dur_months;
	
	
	
	public Course(int cid, String cname, int fees, int dur_months) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.fees = fees;
		this.dur_months = dur_months;
	}
	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * @return the fees
	 */
	public int getFees() {
		return fees;
	}
	/**
	 * @param fees the fees to set
	 */
	public void setFees(int fees) {
		this.fees = fees;
	}
	/**
	 * @return the dur_months
	 */
	public int getDur_months() {
		return dur_months;
	}
	/**
	 * @param dur_months the dur_months to set
	 */
	public void setDur_months(int dur_months) {
		this.dur_months = dur_months;
	}
}
