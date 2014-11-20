/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.text.DecimalFormat;


/**
 *
 * @author Owner
 */

public class Employee extends Human{

    DecimalFormat df = new DecimalFormat("0.00");
    //private String firstName; //first name 
    private String lastName; // last name
    private int IDnum;  // Identification number
    private double moSalary; // Monthly Salary
    private Specialization job;
    private String jobString;
    //String.Format tf = new String.Format("%-10s%-10s%-10d");

    /**
     *
     */
    public Employee() //default employee
    {
        
        lastName = "Employee";
        IDnum = 4929;
        moSalary = 3500.00;
        this.job = Specialization.SALES;
    }

    /**
     *
     * @param first
     * @param last
     * @param num
     * @param sal
     * @param job
     */
    public Employee(String first, String last, int num, double sal, String job) {
        
        this.setFirstName(first);
        lastName = last;
        IDnum = num;
        moSalary = sal;
        //job = jobRead(job.charAt(0));
        job = job.toUpperCase();
        this.job = Specialization.valueOf(job);

    }

 
    /**
     *
     * @param lastName
     * @return false if successful
     */
    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return false;
    }

    /**
     *
     * @param IDnum
     * @return false if successful
     */
    public boolean setIDnum(int IDnum) {
        //int size = empList.size();
        int i = 0;
        boolean flag = true;

        if (IDnum == 0) {
           
            return true;
        }



        if (IDnum > 0) {

            this.IDnum = IDnum;
            return false;
        } else {
           
            return true;
        }

    }

    /**
     *
     * @param moSalary
     * @return false if successful
     */
    public boolean setMoSalary(double moSalary) {

        if (moSalary > 0) {
            this.moSalary = moSalary;
            return false;
        } else {
            
            return true;
        }
    }

    /**
     *
     * @param job
     * @return false if successful
     */
    public boolean setJob(String job) {

        //job = jobRead(job.charAt(0));
        if ("NA".equals(job)) {
            
            return true;
        } else {
            this.job = Specialization.valueOf(job);
            return false;
        }

    }

    /**
     *
     * @param c1 character that should be set to a certain type of job
     * @return enum type in a string format
     */
    public String jobRead(char c1) {


        if ((c1 == 'S') || (c1 == 's')) {
            return "SALES";
        }
        if ((c1 == 'H') || (c1 == 'h')) {
            return "HANDYPERSON";
        }
        if ((c1 == 'L') || (c1 == 'l')) {
            return "LAWNDOCTOR";
        }
        if ((c1 == 'm') || (c1 == 'M')) {
            return "MANAGER";
        }
        if ((c1 == 'C') || (c1 == 'c')) {
            return "SECURITY";
        } else {
            return "NA";
        }

    }

    /**
     *
     * @return employee id number
     */
    public int getIDnum() {
        return IDnum;
    }


    /**
     *
     * @return employee last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return employee salary
     */
    public double getMoSalary() {
        return moSalary;
    }

    /**
     *
     * @return
     */
    public Specialization getJob() {
        return job;
    }

    
    
    
    public String toString() {
        
        String fname = this.getFirstName();
        //return lastName + "  \t" +fname+ "   \t\t" + IDnum + "\t\t $" + df.format(moSalary) + " \t " + job;
        String output;
        
        output = String.format("%5d %12s %12s %16s  \t"+job, IDnum,lastName,fname,"$"+df.format(moSalary));
        return output;
        
        

    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    
    
    
}
