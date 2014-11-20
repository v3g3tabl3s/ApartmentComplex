/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

//import java.util.Collections;
/**
 *
 * @author Owner
 */
public abstract class Human implements FirstName, Comparable {
    
    private String firstName;
    private BankAccount Account;
    
    
    /**
     *
     */
    public Human()
    {
        firstName = "Kevin";
        Account = new BankAccount(5000);
    }
    
    
    
    /**
     *
     * @return balance 
     */
    public float getBalance()
    {
        return Account.getBalance();
    }
    
    /**
     *
     * @return balance as a string
     */
    public String getBalanceString()
    {
        return Account.toString();
    }
    
    /**
     *
     * @return first name of human
     */
    public String getFirstName()
    {
        return firstName;
    }
        
    
    /**
     *
     * @param amount
     * @return false if successful
     */
    public boolean withdraw(float amount)
    {
        return Account.withdraw(amount);
    }
    
    /**
     *
     * @param amount
     * @return false if successful
     */
    public boolean deposit(float amount)
    {
        return Account.deposit(amount);
    }
    
    @Override
    public boolean setFirstName(String input)
    {
        if(input == null)
        {
            return true;
        }
        else
        {
            firstName = input;
            return false; 
        }
        
    }

    /**
     * Will set a bank account for Human
     * @param balance
     * @return false if successful
     */
    public boolean setAccount(float balance) 
    {
        return Account.setBalance(balance);
    }
    

    

}
