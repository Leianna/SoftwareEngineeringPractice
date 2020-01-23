package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

       //CE: This test is a a valid equivalence class case, but it is not a boundary case
        assertEquals(100, bankAccount.getBalance());

        /**CE: This test case is missing invalid cases like withdrawing a negative number, and over drawing the account
         * This test case seems to test three digit positive inputs, but can also test single digit, double digit,
         * quadrupal digit inputs etc. If the specs for the program included a minimum and maximum withdraw value there
         * should be lower and upper boundary cases to test the function as well.**/
    }

    @Test
    void isEmailValidTest(){

        /**CE:This is a valid equivalence class, where the minimum requirements for an email are satisfied
         * This test is a boarder case**/
        assertTrue(BankAccount.isEmailValid( "a@b.com"));

       /**CE: This is an invalid equivalence class, boarder case, where no input is provided **/
        assertFalse( BankAccount.isEmailValid(""));

//        //underscore, period, or dash not followed by letters or number
//        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com"));
//        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));
//
//        //double underscore, period, or dash
//        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));
//        assertFalse( BankAccount.isEmailValid("abc..def@mail.com"));
//
//        //not start with number or letters
//        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));
//        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));

        //contains other symbol other than underscore, period, or dash before or after @
        /**CE: This test is a valid equivalence class member but it is not a border case**/
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));

        /**CE: This is an invalid equivalence class, This is NOT a boarder case. In this instance a boarder case would
         * contain only one invalid symbol. Since this has at least one invalid symbol it is a member of an equivalence class**/
        assertFalse( BankAccount.isEmailValid("abc%def@mai~l.com"));

//        //domain must be at least two characters
//        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));
//        assertFalse( BankAccount.isEmailValid("abc@mail.c"));
//
//        //not contains period after @
//        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));
//        assertFalse( BankAccount.isEmailValid("abc@mail-com"));
//
//        //contains double period after @
//        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));
//        assertFalse( BankAccount.isEmailValid("abc@mail..com"));
//
//        //contains double @
//        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));
//        assertFalse( BankAccount.isEmailValid("abc@def@mail..com"));


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}