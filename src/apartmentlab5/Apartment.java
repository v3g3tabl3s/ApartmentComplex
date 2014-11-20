/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Owner
 */
public class Apartment {
    
    
    
    final int AREA_MIN = 700;
    final int AREA_MAX = 1150;
    private int aptNum; // 123-Apartment number 1-building 2-the floor 3-aprt on the floor
    private int area;   // area in square feet
    private int bedrooms;   // number of bedrooms
    private double bathrooms;  // number of bathrooms
    private boolean patio;    // Whether there is a patio
    private double moRent;     // Monthly rent
    private GregorianCalendar endLease;    //Leased up to certain date
    private int leasePer;   // Lease period in months
    private boolean occupied;
    private SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private DecimalFormat bf = new DecimalFormat("0.0");
    private DecimalFormat mon = new DecimalFormat("0.00");
    
    /**
     *
     */
    public Apartment()
    {
        aptNum = 111;
        area = 1000;
        bedrooms = 2;
        bathrooms = 1.5;
        patio = true;
        moRent = 800;
        endLease = new GregorianCalendar();
        leasePer = 12;
        occupied = false;
    }
    
    /**
     *
     * @param Num
     * @param area
     * @param bedrms
     * @param bathrms
     * @param pat
     * @param rent
     * @param date
     * @param leaseper
     * @throws ParseException
     */
    public Apartment(int Num, int area, int bedrms, double bathrms, boolean pat, double rent, String date, int leaseper) throws ParseException
    {
        
        aptNum = Num;
        if (this.setArea(area)) 
        {
            this.area = 875;
        }
       
        
        if (this.setBedrooms(bedrms)) 
        {
            bedrooms = 3;
        }
      
        
        if (this.setBathrooms(bathrms)) 
        {
            bathrooms = 1.5;
        }
    
        
        patio = pat;
        
        if (this.setMoRent(rent)) 
        {
            moRent = 800;
        }
       
        
        occupied = false;
        Date tempDate = df.parse(date);
        endLease = new GregorianCalendar();
        endLease.setTime(tempDate);
        if(this.setLeasePer(leaseper))
        {
            leasePer = 6;
        }
      
    }

    /**
     *
     * @param AptList 
     * @param aptNum
     * @return false if successful
     */
    public boolean setAptNum(ArrayList<Apartment> AptList, int aptNum) {
        
        int size = AptList.size();
        int i = 0;
        
        for(i=0; i<size ; i++)
        {
            if(aptNum == AptList.get(i).getAptNum())
            {
                return true;
            }
        }
        
        
        if(aptNum > 0)
        {
            this.aptNum = aptNum;
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
     * @return
     */
    public boolean setAptNum(int input) {
        
        if(input > 0)
        {
            aptNum = input;
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     *
     * @param area
     * @return false if successful
     */
    public boolean setArea(int area) 
    {
        if((area >= AREA_MIN) && (area <= AREA_MAX))
        {
           this.area = area;
           return false;
        }
        else
        {
            
            return true;
        }
    }
    /**
     *
     * @param bedrooms
     * @return false if successful
     */
    public boolean setBedrooms(int bedrooms) 
    {
        if((bedrooms >= 1) && (bedrooms <= 5))
        {
            this.bedrooms = bedrooms;
            return false;
        }
        else
        {
            return true;
        }
    
    }

    /**
     *
     * @param bathrooms
     * @return false if successful
     */
    public boolean setBathrooms(double bathrooms) 
    {
        if((bathrooms >= 1) && (bathrooms <= 4))
        {
            this.bathrooms = bathrooms;
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
     * @return false if successful
     */
    public boolean setPatio(int input) 
    {
        if((input == 1) || (input == 0))
        {
            if(input == 1)
            {
                patio = true;
                return false;
            }
            
            else
            {
                patio = false;
                return false;
            }
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
    public void setPatio(String input) {
        if("YES".equals(input))
        {
            patio = true;
            
        }
        else
        {
            patio = false;
            
        }
       
    }

    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean setMoRent(double input) 
    {
        if(input > 0)
        {
            moRent = input;
            return false;
        }
    
        else
        {
            return true;
        }
    }
    

    /**
     *
     * @param occupied
     */
    public void setOccupied(boolean occupied) 
    {
        this.occupied = occupied;
    }

    /**
     *
     * @param leasePer
     * @return false if successful
     */
    public boolean setLeasePer(int leasePer) 
    {
        
        if(leasePer > 0)
        {
            this.leasePer = leasePer;
            return false;
        }
        else
        {
          
            return true;
        }
    }

    /**
     *
     * @param lease
     */
    public void setEndLease(int lease) 
    {
        endLease.setTime(new Date());
        endLease.add(GregorianCalendar.MONTH, lease);  //endLease is current date plus lease period
        
    }
    
    /**
     *
     */
    public void setEndLease()
    {
        
    }
    

    /**
     *
     * @return apt number
     */
    public int getAptNum() {
        return aptNum;
    }

    /**
     *
     * @return apt area
     */
    public int getArea() {
        return area;
    }

    /**
     *
     * @return apt bedrooms
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     *
     * @return apt bathrooms
     */
    public double getBathrooms() {
        return bathrooms;
    }

    /**
     *
     * @return whether is a patio or not
     */
    public boolean isPatio() {
        return patio;
    }

    /**
     *
     * @return monthly rent
     */
    public double getMoRent() {
        return moRent;
    }

    /**
     *
     * @return if its occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     *
     * @return end lease date
     */
    public GregorianCalendar getEndLease() {
        return endLease;
    }

    /**
     *
     * @return lease period
     */
    public int getLeasePer() {
        return leasePer;
    }
    
    
    
    
    
    public String toString()
    {
        //return df.format(endLease.getTime());
        //return "\n" + aptNum;
    
    String patio;
    String occupied;
    String output;
       if(this.patio == true)
       {
           patio = "Yes";
       }
       else
       {   
           patio = "No";
       }
       if(this.occupied == true)
       {
           occupied = "Yes";
       }       
      else
       {
           occupied = "No";
       }
       output = String.format("#%d %7d SqFt %7d %12s %12s %10s %17s", aptNum,area,bedrooms,bf.format(bathrooms),patio,occupied,"$"+mon.format(moRent));
       return output;
       //return "#" +  this.aptNum +"\t"+ area+" Sq ft"
       // +"\t" + bedrooms +"\t  " + bf.format(bathrooms) + "\t\t"+patio+"\t"+occupied+"\t\t$"+df.format(endLease.getTime());
               
       
    }
    
    }

   

