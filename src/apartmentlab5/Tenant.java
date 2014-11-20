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
public class Tenant extends Human implements Constants {
    
    private DecimalFormat df = new DecimalFormat("0.00");
    //private String firstName;
    private String lastName;
    private int Age;

    private Gender gender;
    private int aptNum;
    private float carPort; // cost for rented car port-0 if not rented
    private float garage; // cost for renting garage- 0 if not rented
    private float MoPayment; // monthly payment=rent+carport/garage rented
    private int aptIndex;
   

    /**
     *
     */
    public Tenant()
    {
        //firstName = "Kevin";
        lastName = "Morgan";
        Age = 23;
        this.gender = Gender.MALE;
        aptNum = 123;
        carPort = 0;
        garage = GARAGE_COST;
        this.setAccount(DEFAULT_TENANT_BUDGET);
   
    }
    
    /**
     *
     * @param first
     * @param last
     * @param age
     * @param sex
     * @param aptnum
     * @param carP
     * @param garage
     */
    public Tenant(String first, String last, int age, String sex, int aptnum, float carP, float garage)
    {
        this.setFirstName(first);
        lastName = last;
        if (this.setAge(age)) 
        {
            Age = 25;
        }
        //Age = age;
        
        gender = Gender.valueOf(sex);
        aptNum = aptnum;
        carPort = carP;
        this.garage = garage;
        this.setAccount(DEFAULT_TENANT_BUDGET);
    }
   

    /**
     *
     * @param last
     * @return false if successful
     */
    public boolean setLastName(String last)
    {
        lastName = last;
        return false;
    }
    
    /**
     *
     * @param age
     * @return false if successful
     */
    public boolean setAge(int age)
    {   
        if((age>=18) && (age<100))
        {
           Age = age;
           return false;
        }
        else
            return true;
    }    
    
    /**
     *
     * @param gender
     * @return false if successful
     */
    public boolean setGender(char gender)
    {
        if((gender=='M')||(gender=='m'))
        {
            this.gender = Gender.MALE;
            return false;
        }
        if((gender == 'F')|| (gender == 'f'))
        {
            this.gender = Gender.FEMALE;
            return false;
        }
        else
            return true;
    }
    
    /**
     *
     * @param gender
     */
    public void setGender(String gender)
    {
        if("MALE".equals(gender))
        {
            this.gender = Gender.MALE;
        }
        
        else
        {
            this.gender = Gender.FEMALE;
        }
    
    
        }
    
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean setAptNum(int input)
    {
        aptNum = input;
        return false;
    }

    
    /**
     *
     * @return tenant's apt number
     */
    public int getAptNum() {
        return aptNum;
    }

    /**
     *
     * @param check
     * @return false if successful
     */
    public boolean setCarPort(char check) {
        
        if(check == 'Y' || check == 'y')
        {
            carPort = CAR_PORT_COST;
            return false;
        }
        else
        {
            carPort = 0;
            return false;
        }
        
       
    }
    
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean setCarPort(float input)
    {
        if(input == CAR_PORT_COST)
        {
            carPort = CAR_PORT_COST;
            return false;
        }
    
        else
        {   
            carPort = 0;
            return true;
        }
    }
    

    /**
     *
     * @param check
     * @return false if successful
     */
    public boolean setGarage(char check) {
        
        
        if((check == 'Y') || (check == 'y'))
        {
            garage = GARAGE_COST;
            return false;
        }

        else    
        {
            garage = 0;
            return false;
        }
        
    }
    
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean setGarage(float input)
    {
        if(input == GARAGE_COST)
        {
            garage = GARAGE_COST;
            return false;
        }
    
        else
        {
            return true;
        }
    }

    /**
     *
     * @param input
     */
    public void setCarPort(String input) {
        if("YES".equals(input))
        {
            carPort = CAR_PORT_COST;
        }
        else
        {
            carPort = 0;
        }
    
    }

    /**
     *
     * @param input
     */
    public void setGarage(String input) {
        if("YES".equals(input))
        {
            garage = GARAGE_COST;
        }
        else
        {
            garage = 0;
        }
    
    }

    /**
     *
     * @return yes for a carport-no otherwise
     */
    public String getCarPort() {
        
        if(carPort == 0)
        {
            return "No";
        }
        else
        {
            return "Yes";
        }
        

    }

    /**
     *
     * @return yes if garage is attained
     */
    public String getGarage() {
        if(garage == 0)
        {
            return "No";
        }
        else
        {
            return "Yes";
        }
    }

    
    
    /**
     *
     * @param aptIndex
     */
    public void setAptIndex(int aptIndex) {
        this.aptIndex = aptIndex;
    }

    /**
     *
     * @return aptIndex
     */
    public int getAptIndex() {
        return aptIndex;
    }

    /**
     *
     * @param MoPayment
     * @return false if successful
     */
    public boolean setMoPayment(float MoPayment) {
        
        
        this.MoPayment = MoPayment + this.carPort + this.garage;
        return false;
    
    }

    /**
     *
     * @return monthyl payment
     */
    public float getMoPayment() {
        return MoPayment;
    }

    

    /**
     *
     * @return last name of tenant
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * 
     * @return Age
     */
    public int getAge() {
        return Age;
    }

    /**
     * 
     * @return  gender
     */
    public Gender getGender() {
        return gender;
    }
    
   
    
    /**
     *
     * @param leasePer
     * @param endLease
     * @return String format on printing tenant data
     */
    public String toString(int leasePer, String endLease)
    {
        String output;
         String carPort;
        String garage;
        String fName = this.getFirstName();

            
        output = String.format("%4d %12s %12s %12d\t"+gender+"\t%10s %10s %10s %15d \t\t%10s", aptNum,lastName,fName,Age,"$"+df.format(MoPayment),this.getCarPort(),this.getGarage(),leasePer,endLease);
        //return lastName+"\t\t"+fName+"\t\t"+Age+"\t"+gender +"\t$"+df.format(MoPayment)+" \t"+carPort+"\t\t"+garage+ "\t"+aptNum;
         return output;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    
    
}
