/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

/**
 *
 * public class for BankAccount to be used by all objects
 * @author Owner
 */
public class BankAccount {
    
    private float balance;
    

    /**
     *
     */
    public BankAccount()    
    {
        balance = (float) 0.00;
    }

    /**
     *
     * @param initialBalance
     */
    public BankAccount(float initialBalance)
    {
        balance = initialBalance;
    }

    /**
     *
     * @return balance 
     */
    public float getBalance() {
        return balance;
    }
    
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean setBalance(float input)
    {
        if(input >= 0)
        {
            balance = input;
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
    public boolean deposit(float input)
    {
         if(input > 0)
        {
            balance = input + balance;
            return true;
        }
        
        else
            return false;
        
    }
    
    /**
     *
     * @param input
     * @return false if successful
     */
    public boolean withdraw(float input)
    {
        if(input > 0)
        {
            balance = balance - input;
            if(balance < 0)
            {
                balance = balance + input;
                return true;
            }
            return false;
        }
        
         else
        {
            return true;
        }
    }

    @Override
    public String toString() {
        return "BankAccount " + "balance=" + balance + '}';
    }


    




}
