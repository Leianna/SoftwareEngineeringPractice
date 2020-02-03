package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)&&isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address or stating balance: " + email + "/"+startingBalance+" is invalid, cannot create account");
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
    public void withdraw (double amount) throws InsufficientFundsException{

        if(isAmountValid(amount)){
            if(amount > balance){
                throw new InsufficientFundsException("Not enough money");

            }
            else{
                balance = (double) Math.round((balance - amount)*100)/100;
            }
        }

    }

    //searches for invalid symbols(%,~) within email string
    public static boolean invalidSymbol(String email){
        if(email==""){
            return true;
        }
        String [ ] symbol = {"@@","--","__","..","-@","@-","@_","_@","@.",".@","`","~","!","#","$","%","^","&","*","(",")","=","+","[","{","}","]",";",": ","<",">",",","?","/","'","\"","\\","|"};
        char [ ] symbol2 = {'-','@','_','.'};

        //check if there's wrong symbol
        for(int i=0;i<symbol.length;i++){
            if(email.contains(symbol[i])) {
                return true;
            }
        }


        //check valid location
        int index=0;
        for(int i=0;i<symbol2.length;i++){
            if(email.indexOf(symbol2[i]) != 0 && email.lastIndexOf(symbol2[i])!=email.length()-1 & email.lastIndexOf(symbol2[i])!=email.length()-2) {
                index = index +0;
            }
            else {
                index = index +1;
            }
        }
        if(index>0){
            return true;
        }

        //check if @ is in front of one. and both not in position -1

        if(email.indexOf('@')>email.lastIndexOf('.')||email.indexOf('@')==-1||email.indexOf('.')==-1){
            return true;
        }
        else if(email.indexOf('@')>email.indexOf('.') && email.indexOf('.')>email.lastIndexOf('.')){
            return true;
        }

        //Nothing wrong
        return false;
    }


    public static boolean isEmailValid(String email){
        /**
         * Candice didn't finish this function(if email is invalid), the test are not pass, so i use mines instead
         */

        if(invalidSymbol(email)){
            return false;
        }
        else {
            return true;
        }

    }

    /**
     * check if the amount is valid
     * @param amount
     * @return true of the amount is negative or have more than two decimals
     */
    public static boolean isAmountValid(double amount) throws IllegalArgumentException{
        if(amount>=0 && BigDecimal.valueOf(amount).scale() <= 2){
            return true;
        }else{
            throw new IllegalArgumentException("Can't go below 0 or more than 2 decimals");
        }
    }

    /**
     * add more money to account
     * @param amount must be 0 or greater
     */
    public void deposit(double amount)throws IllegalArgumentException{
        if(isAmountValid(amount)){
            balance = (double) Math.round((balance + amount)*100)/100;
        }
    }

    /**
     * transfer in or transfer out amount from balance
     * @param transferInOrOut - decide in or out in lower case
     * @param amount - amount that need transfer
     * @throws IllegalArgumentException - if the amount is less than 0 or more than 2 decimals
     * @throws InsufficientFundsException -  if the balance is less than the amount when user try to transfer out
     */
    public void transfer(String transferInOrOut, double amount) throws IllegalArgumentException, InsufficientFundsException {

        if(transferInOrOut == "in"){
            deposit(amount);
        }
        else if(transferInOrOut=="out"){
            withdraw(amount);
        }else{
            throw new IllegalArgumentException("Must be lower case in or out");
        }

    }
}
