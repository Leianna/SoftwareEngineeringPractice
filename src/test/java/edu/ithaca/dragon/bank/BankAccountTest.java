package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        /**
         * full amount
         */
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        /**
         * Decimals
         */
        BankAccount bankAccount1 = new BankAccount("a@b.com", 111.11);
        assertEquals(111.11, bankAccount1.getBalance());

        /**
         * Round up to hundredths
         */
        BankAccount bankAccount2 = new BankAccount("a@b.com", 55.5555);
        assertEquals(55.56, bankAccount2.getBalance());

        /**
         * zero amount
         */
        BankAccount bankAccount3 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount3.getBalance());

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

        /**
         * First decimals
         */
        bankAccount.withdraw(55.50);
        assertEquals(44.50, bankAccount.getBalance());

        /**
         * Second decimals
         */
        bankAccount.withdraw(22.25);
        assertEquals(22.25, bankAccount.getBalance());

        /**
         * Error: negative
         */
        bankAccount.withdraw(-22.25);
        assertEquals(22.25, bankAccount.getBalance());

        /**
         * Zeros
         */
        bankAccount.withdraw(22.25);
        assertEquals(0, bankAccount.getBalance());

        /**
         * Error
         */
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){

        /**CE:This is a valid equivalence class, where the minimum requirements for an email are satisfied
         * This test is a boarder case**/
        assertTrue(BankAccount.isEmailValid( "a@b.com"));

       /**CE: This is an invalid equivalence class, boarder case, where no input is provided **/
        assertFalse( BankAccount.isEmailValid(""));

        /**
         * Represent: location of symbols
         * valid symbols located before, middle ,and  after: [- _ . @]
         */
        assertFalse( BankAccount.isEmailValid("-abssc@mail.com"));
        assertFalse( BankAccount.isEmailValid("abssc-@mail.com"));
        assertFalse( BankAccount.isEmailValid("abssc@mail.com-"));

        /**
         * Represent:double symbols
         * including valid and invalid symbols in different location
         * located before, middle ,and  after
         */
        assertFalse( BankAccount.isEmailValid("a..bcdef@mail.com"));
        assertFalse( BankAccount.isEmailValid("abcde;;f@mail.com"));
        assertFalse( BankAccount.isEmailValid("abcdef@mail.co##m"));


        //contains other symbol other than underscore, period, or dash before or after @
//        /**CE: This test is a valid equivalence class member but it is not a border case**/
//        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));

//        /**CE: This is an invalid equivalence class, This is NOT a boarder case. In this instance a boarder case would
//         * contain only one invalid symbol. Since this has at least one invalid symbol it is a member of an equivalence class**/
//        assertFalse( BankAccount.isEmailValid("abc%def@mai~l.com"));

        /**
         * Represent:inavlid symbols
         * include all inavlid symbols: ~`!#$%^&*()+={}[]\|;:"'<,?/>
         * located before, middle ,and  after
         */
        assertFalse( BankAccount.isEmailValid("a%bcdef@mail.com"));
        assertFalse( BankAccount.isEmailValid("abcde^f@mail.com"));
        assertFalse( BankAccount.isEmailValid("abcdef@mail.co$m"));


        //domain must be at least two characters/contains period after @
        /**
         * number of characters in different position
         */
        assertFalse( BankAccount.isEmailValid("abc@mail.c"));
        assertFalse( BankAccount.isEmailValid("abc@.com"));
        assertFalse( BankAccount.isEmailValid("@mail.com"));


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