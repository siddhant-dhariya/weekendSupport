package com.infy.carefirst.model;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE",schema = "weekendsupport")
public class Employee {
	   
		/*@Id @GeneratedValue
	   @Column(name = "id")
	   private int id;*/
	  
		@EmbeddedId
		private EmployeeID empID;
	
	   public EmployeeID getEmpID() {
			return empID;
		}
		public void setEmpID(EmployeeID empID) {
			this.empID = empID;
		}
	@Column(name = "first_name")
	   private String firstName; 
	   
	   @Column(name = "last_name")
	   private String lastName; 
	   
	   @Column(name = "tower_Name")
	   private String towerName;
	   
	   @Column(name = "user_status")
	   private String userStatus;

	   public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Employee() {}
	   public Employee(String fname, String lname, String towerName) {
	      this.firstName = fname;
	      this.lastName = lname;
	      this.towerName = towerName;
	   }
	  /* public int getId() {
	      return id;
	   }
	   public void setId( int id ) {
	      this.id = id;
	   }*/
	   public String getFirstName() {
	      return firstName;
	   }
	   public void setFirstName( String first_name ) {
	      this.firstName = first_name;
	   }
	   public String getLastName() {
	      return lastName;
	   }
	   public void setLastName( String last_name ) {
	      this.lastName = last_name;
	   }
	public String getTowerName() {
		return towerName;
	}
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}
	  
	}
