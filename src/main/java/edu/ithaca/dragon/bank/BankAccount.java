package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException {

        if(amount < 0.00){
            //do nothing
        }

        else if(amount > balance){
            throw new InsufficientFundsException("Not enough money");

        }
        else{
        balance -= amount;
        }

    }

    //searches for invalid symbols(%,~) within email string
    public static boolean invalidSymbol(String email){
        if (email.indexOf('%')!=-1) {

            return true;
        }
        if (email.indexOf('~')!=-1) {

            return true;
        }
        else{
            return false;
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }

        else if(invalidSymbol(email)){

            return false;
        }

        else {
            return true;
        }
    }
}
