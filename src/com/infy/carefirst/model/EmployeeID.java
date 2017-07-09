package com.infy.carefirst.model;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
 
@Embeddable
public class EmployeeID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="empID")
	private Integer empID;
	@Column(name="weekendSupportDate")
	private Date weekendSupportDate;
	
	public EmployeeID(){}
	public EmployeeID(Integer empID, Date weekendSupportDate) {
		this.empID = empID;
		this.weekendSupportDate = weekendSupportDate;
	}
	
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public Date getWeekendSupportDate() {
		return weekendSupportDate;
	}
	public void setWeekendSupportDate(Date weekendSupportDate) {
		this.weekendSupportDate = weekendSupportDate;
	}

}
