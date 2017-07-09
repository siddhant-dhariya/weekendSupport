package com.infy.carefirst.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List; 
import java.util.Iterator; 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
/*import org.hibernate.cfg.AnnotationConfiguration;*/
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.infy.carefirst.model.Employee;
import com.infy.carefirst.model.EmployeeID;

public class ManageEmployee {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      try{
        /* factory = new AnnotationConfiguration().
                   configure().addPackage("com.infy.carefirst.model").
                   addAnnotatedClass(Employee.class).
                   buildSessionFactory();*/
         factory=new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
         
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageEmployee ME = new ManageEmployee();

      /* Add few employee records in database */
      String d="17/07/2017";  
      Date date=null;
	try {
		date = new SimpleDateFormat("dd/MM/yyyy").parse(d);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      //Integer empID1 = ME.addEmployee(665277,"Siddhant", "Dhariya", "Provider Portal",date);
	  //Integer empID2 = ME.addEmployee(665277,"Siddhant", "Dhariya", "Provider Portal",date,"Active");

      /* List down all the employees */
      ME.listEmployees();

      /* Update employee's records */
     // ME.updateEmployee(empID1, "Provider Portal");

      /* Delete an employee from the database */
      ME.deleteEmployee(665277);

      /* List down new list of the employees */
      //ME.listEmployees();
   }
   /* Method to CREATE an employee in the database */
   public Integer addEmployee(Integer empID,String fname, String lname, String towerName,Date date,String userStatus){
      Session session = factory.openSession();
      Transaction tx = null;
      int employeeID = 0;
      try{
         tx = session.beginTransaction();
         Employee employee = new Employee();
         EmployeeID id=new EmployeeID(empID, date);
         employee.setEmpID(id);
         employee.setFirstName(fname);
         employee.setLastName(lname);
         employee.setTowerName(towerName);
         employee.setUserStatus(userStatus);;
         id = (EmployeeID) session.save(employee); 
         tx.commit();
      }catch (HibernateException e) {
    	  System.out.println("Exception occured");
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return employeeID;
   }
   /* Method to  READ all the employees */
   public void listEmployees( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List employees = session.createQuery("FROM Employee").list(); 
         for (Iterator iterator = 
                           employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Tower Name: " + employee.getTowerName()); 
            System.out.println("Weekend Support Date "+employee.getEmpID().getWeekendSupportDate());
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   /* Method to UPDATE salary for an employee */
   public void updateEmployee(Integer EmployeeID, String towerName ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                    (Employee)session.get(Employee.class, EmployeeID); 
         employee.setTowerName(towerName);
		 session.update(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   /* Method to DELETE an employee from the records */
   public void deleteEmployee(Integer EmployeeID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                   (Employee)session.get(Employee.class, EmployeeID); 
        employee.setUserStatus("Disabled");
         session.update(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}
