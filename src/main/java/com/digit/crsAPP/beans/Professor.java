package com.digit.crsAPP.beans;

public class Professor {
	int pid;
	String pname;
	String ppass;
	int exp;
	String course;
	int courseID;
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getPpass() {
		return ppass;
	}
	public void setPpass(String ppass) {
		this.ppass = ppass;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Professor(int pid, String pname, int exp) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.exp = exp;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
}
