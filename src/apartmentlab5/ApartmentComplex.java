/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 *
 * @author Owner
 */
public class ApartmentComplex implements Constants {

    private DecimalFormat df = new DecimalFormat("0.00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private BankAccount operatingBudget = new BankAccount(ANNUAL_BUDGET);
    private ArrayList <Apartment> AptList = new ArrayList<Apartment>();
    private ArrayList <Employee> EmployeeList = new ArrayList<Employee>();     
    private ArrayList <Tenant> TenantList = new ArrayList<Tenant>();
    private ArrayList <Tenant> OldTenants = new ArrayList<Tenant>();
    
    /**
     *
     */
    public ApartmentComplex()
    {
       
    }

    /**
     *
     * @param A
     * @param inFile
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public void readFile(ApartmentComplex A, Scanner inFile) throws FileNotFoundException, ParseException
    {
        

       int tempLeasePer;
       String first;
       String last;
       int num;
        
        
        while(inFile.hasNext())
        {
            
            String tempString = inFile.nextLine();
            char choice = 'N';

            if("Apartment:".equals(tempString))
            {
                choice = 'A';
            }
            
            if("Tenant:".equals(tempString))
            {
                choice = 'T';
                
            }
                
            if("Employee:".equals(tempString))
            {
                choice = 'E';
            }
            
            switch(choice){
                
                case 'A':
                    
                try{    
                int aptNum = inFile.nextInt();
                if(A.checkAptNum(aptNum))
                {
                    aptNum = A.FindNewAptNum();
                }
                Apartment apt = new Apartment(aptNum,inFile.nextInt(),inFile.nextInt(),inFile.nextDouble(),inFile.nextBoolean(),inFile.nextFloat(),inFile.next(),inFile.nextInt());
                
                while(A.add(apt))
                {

                }
                }
                catch(InputMismatchException e)
                {
                    //don't parse anything
                }

                
                break;    
          
                case 'T':
                  try{
                 first = inFile.next();
                 last = inFile.next();
                 Tenant newTenant = new Tenant(first,last,inFile.nextInt(),inFile.next(),inFile.nextInt(),inFile.nextFloat(),inFile.nextFloat());
                 int aptIndex = A.FindAptIndex(newTenant.getAptNum());
                 
                    try {
                        
                        tempLeasePer = A.getAptList().get(aptIndex).getLeasePer();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        
                        
                        tempLeasePer = 6;
                    }
                
                
                    while(A.lease(newTenant, newTenant.getAptNum(),tempLeasePer,false ))
                    {
                        newTenant.setAptNum(A.findNextAvailableApt());
                    }
                  }
                  
                  catch(InputMismatchException e)
                  {
                      //don't parse anything
                  }
                break;
            
                case 'E':
                    try{
                first = inFile.next();
                last = inFile.next();
                num = inFile.nextInt();                
                if(A.FindIDNum(num))
                {
                    num = A.SetNewIDNum();
                }
                Employee newEmployee = new Employee(first,last,num,inFile.nextDouble(),inFile.next());
                A.hire(newEmployee);
                    }
                    
                    catch(InputMismatchException e)
                    {
                        //don't parse anything
                    }
                break;
                    
                default:
                    
                    break;
            }
        }
        
       
    
    }   

  
    
    /**
     * Will lease an  apartment to a tenant after creating a new apartment
     * @param newTenant
     * @param newApartment
     * @param numberOfMonthsLease
     * @return false if successful
     */
    public boolean lease(Tenant newTenant, Apartment newApartment, int numberOfMonthsLease)
   {
       
       newApartment.setEndLease(numberOfMonthsLease);
       int size = AptList.size();
       

       add(newApartment);
       newTenant.setAptNum(newApartment.getAptNum());
       int aptListSize = AptList.size();
       
       newTenant.setAptIndex(aptListSize-1);
       newTenant.setAptNum(AptList.get(aptListSize-1).getAptNum());
       newTenant.setMoPayment((float)newApartment.getMoRent());
       
       int aptIndex = newTenant.getAptIndex();
       
       AptList.get(aptIndex).setOccupied(true);
       add(newTenant);
       
       return false;

   }
   
    /**
     * will check for the apartment number to lease it to the tenant
     * @param newTenant
     * @param aptNumber
     * @param numberOfMonthsLease
     * @param gui 
     * @return false if successful
     */
    public boolean lease(Tenant newTenant, int aptNumber, int numberOfMonthsLease,boolean gui)
   {
       boolean flag = true;
       int i = 0;
       int aptIndex = 0;
       int size = AptList.size();
       
       do
       {
           if(i==size)
          {

              return false;
          }
           if(AptList.get(i).getAptNum() == aptNumber)
           {
               if(AptList.get(i).isOccupied() == false)
               {
                   aptIndex = i;
                   flag = false;
                   
               }
               else
               {
 
                   return true;
               }
              
           }
  
          i++;
       }while(flag);

       newTenant.setAptIndex(aptIndex);
       newTenant.setAptNum(AptList.get(aptIndex).getAptNum());
       newTenant.setMoPayment((float)AptList.get(aptIndex).getMoRent());
       add(newTenant);
       
       AptList.get(aptIndex).setLeasePer(numberOfMonthsLease);
       if(gui)
       {
           AptList.get(aptIndex).setEndLease(numberOfMonthsLease);
       }
           AptList.get(aptIndex).setOccupied(true);
       
       return false;
       

       
   } 
    
    
    /**
     *
     * @return
     */
    public int findNextAvailableApt()
    {
        int size = AptList.size();
        int i;
        for(i=0;i < size;i++)
        {
            if(AptList.get(i).isOccupied())
            {
                
            }
            else
            {
                return AptList.get(i).getAptNum();
            }
        }
        
        return -1;
        
    }
    /**
     * It will remove the tenant from the tenant list
     * @param aptNum
     * @return false if successful(
     */
    public boolean removeTenant(int aptNum)
   {
      int size = TenantList.size();
      int aptIndex;
       for (int i = 0; i < size; i++) 
       {
           if(TenantList.get(i).getAptNum() == aptNum)
           {
               aptIndex = TenantList.get(i).getAptIndex();
               AptList.get(aptIndex).setOccupied(false);;
               OldTenants.add(TenantList.get(i));
               TenantList.remove(i);
               return false;
           }

       }
       
  
       return true;
       
   }

    /**
     *  Adds an employee
     * @param employee
     * @return false if successful
     */
    public boolean hire(Employee employee)
   {
       int size = EmployeeList.size();
       boolean flag = true;
       int i = 0;
       
        if (size == 0) {
                //EmployeeList.add(employee); 
                flag = false;
                
            }
        
       
            while (flag)
            {
            if(i == size - 1)
            {
                flag = false;
            }
            if (EmployeeList.get(i++).getIDnum() == employee.getIDnum()) {
                
                return true;
                
            }
            }
           
      

       
      EmployeeList.add(employee); 
      return false;
       
       
   }
    

    /**
     * Removes the employee
     * @param ID
     * @return false if successful
     */
    public boolean removeEmployee(int ID)
   {
      int size = EmployeeList.size();
      
       for (int i = 0; i < size; i++) 
       {
           if(EmployeeList.get(i).getIDnum() == ID)
           {
              
               
               EmployeeList.remove(i);
               return false;
           }
       }
       

       return true;
   }
    

  

    /**
     *@return false if successful
     */
    public boolean payAllEmployees()
   {
      int size = EmployeeList.size();
      float totalPayout = 0;
      float pay;
      
      if(size == 0)
      {
          return true;
      }
      
      if(operatingBudget.getBalance() < 0)
      {
          return true;
      }
      
      
       for (int i = 0; i < size; i++) 
       {    
           pay = (float)EmployeeList.get(i).getMoSalary();
           totalPayout = totalPayout + pay;

       }
       
       if(operatingBudget.withdraw(totalPayout))
       {
           return true;
       }
       
       for (int i = 0; i < size; i++) 
       {    
           pay = (float)EmployeeList.get(i).getMoSalary();
           EmployeeList.get(i).deposit(pay);
       }       
       
       return false;
      
   }   
    
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean payAnEmployee(int input)
   {
      int size = EmployeeList.size();
      float totalPayout = 0;

      int i = 0;
      int index = 0;
      
      for(i=0;i<size;i++)
      {
          if(EmployeeList.get(i).getIDnum() == input)
          {
              index = i;
          }
      }

      
      if(size == 0)
      {
          return true;
      }
      
      if(operatingBudget.getBalance() < 0)
      {
          return true;
      }
       float pay = (float)EmployeeList.get(index).getMoSalary();
        
       if(operatingBudget.withdraw(pay))
       {
           return true;
       }
       
       EmployeeList.get(index).deposit(pay);


       
       return false;
      
   }   
   
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean FindIDNum(int input)
   {
       int size = EmployeeList.size();
       
       if(input<0)
       {
           return true;
       }
       
       if (size == 0) {
           return false;
           
           
       }
       
       for (int i = 0; i < size; i++) {
           if(EmployeeList.get(i).getIDnum() == input)
           {
               return true;
           }
           
       }
       
       return false;
   }
    
    /**
     *
     * @param input
     * @return
     */
    public int FindAptIndex(int input)
    {
        int size = AptList.size();
        
        for (int i = 0; i < size; i++) {
            
            if(input == AptList.get(i).getAptNum())
            {
                return i;
            }
            
        }
    return -1;
    }
 
    /**
     *
     * @return
     */
    public int SetNewIDNum()
    {
        for (int i = 1; i < 100000; i++) {
            
           if(FindIDNum(i))
           {
               
           }
           else
           {
               return i;
           }
            
        }
        return -1;
    }
    /**
     *
     * @return the Balance of the operating budget
     */
    public float getBalance()
   {
       return operatingBudget.getBalance();
   }  
   
    /**
     *
     * @return number of unoccupied apartments
     */
    public int numberOfUnoccupiedApts()
   {
       int size = AptList.size();
       int total = 0;
       
       for (int i = 0; i < size; i++) 
       {
           if(AptList.get(i).isOccupied() == false)
           {
               total = total + 1;
               
           }
       }
       
   
       return total;
   }
 

   
    /**
     * Adds the total rent of all the tenants deposits that to the balance
     * Also withdraws from each Tenant's Bank Account to pay the rent
     * 
     * @return true of successful
     */
    public boolean collectRent()
   {
      int size = TenantList.size();
      float totalPayout = 0;
      float pay;
      
       for (int i = 0; i < size; i++) 
       {    
           pay = (float)TenantList.get(i).getMoPayment();
           //totalPayout = totalPayout + pay;
           
           if(TenantList.get(i).withdraw(pay))
           {

               //pay = (float)TenantList.get(i).getMoPayment();

           }
           else
           {
               totalPayout = totalPayout + pay;
           }
       }
       
       operatingBudget.deposit(totalPayout);
       
       if(totalPayout == 0)
       {
           return false;
       }
       else
       {
           return true;
       }

   }   
   
    /**
     * Collects rent from an individual Tenant
     * @param index
     * @return false if successful
     */
    public boolean collectRent(int index)
    {
      int size = TenantList.size();
      if(size == 0)
      {
          return true;
      }
      float pay = TenantList.get(index).getMoPayment();
           
           if(TenantList.get(index).withdraw(pay))
           {
                return true;
           }
           else
           {
               operatingBudget.deposit(pay);
               return false;
           }

        
    }
    /**
     * Add a new Apartment to the List
     * @param newApartment
     * @return false if successful
     */
    public boolean add(Apartment newApartment)
   {

           AptList.add(newApartment);
           return false;

   }
  
    /**
     * Adds a new Tenant to the List
     * @param tenant
     * @return false if successful
     * 
     */
    public boolean add(Tenant tenant)
   {
       TenantList.add(tenant);
       return false;
       //return true;
   }
   
   
    /**
     *
     * @return string
     */
    public String[] getApartmentHistory()
   {
       
       int size = OldTenants.size();
       String[] output = new String[size];
       for (int i = 0; i < size; i++) {
           output[i] =  OldTenants.get(i).getFirstName()+ "  " +OldTenants.get(i).getLastName();
       }
  
       return output;
   }
   
    /**
     * returns number of Apartments
     * @return Apartment List size
     */
    public int getNumOfApts()
   {
       return AptList.size();
   }
   
    /**
     * returns number of Tenants
     * @return Tenant list size
     */
    public int getNumOfTenants()
   {
       
       return TenantList.size();
   }

    /**
     * returns number of Employees
     * @return Employee list size
     */
    public int getNumOfEmployees()
   {
       return EmployeeList.size();
   }

    /**
     *
     * @return
     */
    public ArrayList<Apartment> getAptList() {
        return AptList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Employee> getEmployeeList() {
        return EmployeeList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Tenant> getTenantList() {
        return TenantList;
    }
    
    

    /**
     *
     * @return data to be printed out
     */
    public Object[][] getEmpData()
    {
        int size = EmployeeList.size();
        
        Object data[][] = new Object [size][5];
        
        for (int i = 0; i < size; i++) {
            data[i][0]=  EmployeeList.get(i).getIDnum();
            data[i][1]=  EmployeeList.get(i).getLastName();
            data[i][2]=  EmployeeList.get(i).getFirstName();
            data[i][3]=  "$"+df.format(EmployeeList.get(i).getMoSalary());
            data[i][4]=  EmployeeList.get(i).getJob();
            
        }
    
        return data;
    }
    
    /**
     *
     * @return data to be printed out
     */
 
    public Object[][] getTenantData()
    {
        int size = TenantList.size();
        int aptIndex;
        String endLease;
        Object data[][];
        if(size == 0)
        {   
            //data = new Object[1][10];
            Object data2[][] = {{"N/A - NO TENANTS","N/A","N/A","N/A","N/A","N/A","N/A","N/A","N/A","N/A"}};
            
            return data2;
        
        }
        data = new Object[size][10];
        
        
        for (int i = 0; i < size; i++) {
            

            aptIndex = TenantList.get(i).getAptIndex();

            GregorianCalendar tempDate = AptList.get(aptIndex).getEndLease();
            endLease = dateFormat.format(tempDate.getTime());
            
            data[i][0]=  TenantList.get(i).getAptNum();
            data[i][1]=  TenantList.get(i).getLastName();
            data[i][2]=  TenantList.get(i).getFirstName();
            data[i][3]=  TenantList.get(i).getAge();
            data[i][4]=  TenantList.get(i).getGender();
            data[i][5]=  "$"+df.format(TenantList.get(i).getMoPayment());
            data[i][6]=  TenantList.get(i).getGarage();
            data[i][7]=  TenantList.get(i).getCarPort();
            data[i][8]=  AptList.get(TenantList.get(i).getAptIndex()).getLeasePer();
            data[i][9]=  endLease;           
            
        }
    
        return data;
    }
    
     
    /**
     *
     * @return
     */
    public Object[][] getOldTenantData()
    {
        int size = OldTenants.size();
        int aptIndex;
        String endLease;
        Object data[][];
        if(size == 0)
        {   
            //data = new Object[1][10];
            Object data2[][] = {{"N/A - NO TENANTS","N/A","N/A","N/A","N/A","N/A","N/A","N/A","N/A"}};
            
            return data2;
        
        }
        data = new Object[size][9];
        
        
        for (int i = 0; i < size; i++) {
            

            aptIndex = OldTenants.get(i).getAptIndex();

            GregorianCalendar tempDate = new GregorianCalendar();
            tempDate.setTime(new Date());
            endLease = dateFormat.format(tempDate.getTime());
            
            data[i][0]=  OldTenants.get(i).getAptNum();
            data[i][1]=  OldTenants.get(i).getLastName();
            data[i][2]=  OldTenants.get(i).getFirstName();
            data[i][3]=  OldTenants.get(i).getAge();
            data[i][4]=  OldTenants.get(i).getGender();
            data[i][5]=  "$"+df.format(OldTenants.get(i).getMoPayment());
            data[i][6]=  OldTenants.get(i).getGarage();
            data[i][7]=  OldTenants.get(i).getCarPort();
            data[i][8]=  endLease;           
            
        }
    
        return data;
    }
    /**
     *
     * @return data to be printed out
     */
    public Object[][] getAptData()
    {
        int size = AptList.size();
        int aptIndex;
        String endLease;
        Object data[][] = new Object [size][10];
        
        for (int i = 0; i < size; i++) {
            


            
            data[i][0]=  AptList.get(i).getAptNum();
            data[i][1]=  AptList.get(i).getArea()+ "sq. ft";
            data[i][2]=  AptList.get(i).getBedrooms();
            data[i][3]=  AptList.get(i).getBathrooms();
            data[i][4]=  AptList.get(i).isPatio();
            data[i][5]=  AptList.get(i).isOccupied();
            data[i][6]=  "$"+df.format(AptList.get(i).getMoRent());

        }
    
        return data;
    }

    
    /**
     * Puts employees and people into human list
     * @param PeopleList
     */
    public void setPeopleList(ArrayList<Human> PeopleList) {
        int size = EmployeeList.size();
        int i;
        for (i = 0; i < size; i++) {
            PeopleList.add(EmployeeList.get(i));
  
        }
        size = TenantList.size();
                for (i = 0; i < size; i++) {
            PeopleList.add(TenantList.get(i));
  
        }
        
        
    }
    

    
    /**
     *
     * @return bank data
     */
    public Object[][] getBankData()
    {
        int size1 = TenantList.size();
        int size2 = EmployeeList.size();
        int i;
        Object data[][] = new Object [size1+size2][4];
        for(i=0;i<size1;i++)
        {
            data[i][0] = TenantList.get(i).getLastName();
            data[i][1] = TenantList.get(i).getFirstName();            
            data[i][2] = df.format(TenantList.get(i).getBalance());
            data[i][3] = "Tenant";
        }
        
        for(i=0;i<size2;i++)
        {
            data[i+size1][0] = EmployeeList.get(i).getLastName();
            data[i+size1][1] = EmployeeList.get(i).getFirstName();            
            data[i+size1][2] = df.format(EmployeeList.get(i).getBalance());
            data[i+size1][3] = "Employee";
        }

    
    return data; 
    }
    /**
     * 
     *
     */
    public void sortEmployees()
    {
        int j;
        boolean flag = true;
        Employee temp;
        int size = EmployeeList.size();
        int comp;
        while(flag)
        {
            flag = false;
            for(j = 0; j < size-1; j++)
            {
                comp = EmployeeList.get(j).getFirstName().compareTo(EmployeeList.get(j+1).getFirstName());
                if(comp  > 0)
                {
                    temp = EmployeeList.get(j);
                    EmployeeList.set(j,EmployeeList.get(j+1) );
                    EmployeeList.set(j+1,temp);
                    flag = true;
                }
            }
        }
    }
    
    /**
     *
     */
    public void sortTenants()
    {
        int j;
        boolean flag = true;
        Tenant temp;
        int size = TenantList.size();
        int comp;
        while(flag)
        {
            flag = false;
            for(j = 0; j < size-1; j++)
            {
                comp = TenantList.get(j).getFirstName().compareTo(TenantList.get(j+1).getFirstName());
                if(comp  > 0)
                {
                    temp = TenantList.get(j);
                    TenantList.set(j,TenantList.get(j+1) );
                    TenantList.set(j+1,temp);
                    flag = true;
                }
            }
        }
    }
    
    /**
     *
     * @param input
     * @return
     */
    public boolean checkAptNum(int input)
    {
        int size = AptList.size();
        if(input < 0 )
        {
            return true;
        }
        
        if(size == 0)
        {
            return false;
        }
        
        
        for (int i = 0; i < size; i++) {
            if(AptList.get(i).getAptNum() == input)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @return
     */
    public int FindNewAptNum()
    {
        int[] aptNums = new int[1000];
        int i;
        int newAptNum;
        
        for(i = 0; i < 1000; i++)
        {
            aptNums[i] = 100+i;
           if(checkAptNum(aptNums[i]))
           {
               //do nothing
           }
               
           else
           {
               return aptNums[i];
           }
           
        }
        
        return -1;
        
        
        
        
        
    }
    /**
     *
     * @param input
     * @return false if successful
     */
    
    
    public boolean checkUnoccupiedAptNum(int input)
    {
        int size = AptList.size();
        if(size == 0)
        {
            return false;
        }
        
        for (int i = 0; i < size; i++) {
            if(AptList.get(i).getAptNum() == input)
            {
                if(AptList.get(i).isOccupied() == false)
                {
                    return false;
                }
            }
        }
        return true;
    }



}

