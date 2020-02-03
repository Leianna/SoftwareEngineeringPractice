package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        /**
         * Lei/ Equiv: full amount
         */
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        /**
         * Lei/ Equiv: Decimals
         */
        BankAccount bankAccount1 = new BankAccount("a@b.com", 555.55);
        assertEquals(555.55, bankAccount1.getBalance());

        /**
         * Lei/ Border Maximum: 1,000,000 amount
         */
        BankAccount bankAccount2 = new BankAccount("a@b.com", 1000000);
        assertEquals(1000000, bankAccount2.getBalance());

        /**
         * Lei/ Border Minimum: zero amount
         */
        BankAccount bankAccount3 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount3.getBalance());

    }

    @Test
    void withdrawTest()throws InsufficientFundsException {
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
//
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);
        /**
         * Error:  with draw negative
         */
        assertThrows(IllegalArgumentException.class, ()->bankAccount1.withdraw(-0.01));


        /**
         * Error:  with draw negative
         */
        assertThrows(IllegalArgumentException.class, ()->bankAccount1.withdraw(-1.00));


        /**
         * Error: with draw negative
         */
        assertThrows(IllegalArgumentException.class, ()->bankAccount1.withdraw(-100.00));


        /**
         * Error: with draw more than two decimals
         */
        assertThrows(IllegalArgumentException.class, ()->bankAccount1.withdraw(100.23456));

        /**
         * Zeros
         */
        bankAccount.withdraw(22.25);
        assertEquals(0, bankAccount.getBalance());

        /**
         * Error
         */
        assertThrows(InsufficientFundsException.class, ()->bankAccount.withdraw(100));

//        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);

//        assertThrows(IllegalArgumentException.class, ()->bankAccount1.withdraw(-100));
        //bankAccount.withdraw(100);
        //assertEquals(0, bankAccount.getBalance());

        //CE: This test should throw the Insufficeint Funds Exception. I've commented out the original test added the asserThrows
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


//        contains other symbol other than underscore, period, or dash before or after @
//        /**CE: This test is a valid equivalence class member but it is not a border case**/
//        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));
//
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
    void isAmountValidTest()throws IllegalArgumentException {
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);
        //check negative maximum-100, middle -50, minimum -1
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.isAmountValid(-100));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.isAmountValid(-50));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.isAmountValid(-1));

        //check decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.isAmountValid(0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.isAmountValid(0.5555555555));

    }


    @Test
    void depositTest()throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        //deposit maximum 100
        bankAccount.deposit(100);
        assertEquals(300.0, bankAccount.getBalance());
        //deposit medium 50
        bankAccount.deposit(50);
        assertEquals(350.0, bankAccount.getBalance());
        //deposit minimum 1
        bankAccount.deposit(1);
        assertEquals(351.0, bankAccount.getBalance());

        //check negative maximum-100, middle -50, minimum -1
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-100));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-50));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-1));
        //check decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-0.55555555));


    }


    @Test
    void transferTest() throws InsufficientFundsException, IllegalArgumentException{
        BankAccount bankAccountA = new BankAccount("a@b.com", 200);
        BankAccount bankAccountB = new BankAccount("a@b.com", 200);
        //transfer from BankA to BankB,maximum
        bankAccountA.transfer("out",100);
        assertEquals(100.0, bankAccountA.getBalance());
        bankAccountB.transfer("in",100);
        assertEquals(300.0, bankAccountB.getBalance());

        //transfer from BankB to BankA, medium
        bankAccountB.transfer("out",50);
        assertEquals(250.0, bankAccountB.getBalance());
        bankAccountA.transfer("in",50);
        assertEquals(150.0, bankAccountA.getBalance());

        //transfer from BankB to BankA, minimum
        bankAccountB.transfer("out",1);
        assertEquals(249.0, bankAccountB.getBalance());
        bankAccountA.transfer("in",1);
        assertEquals(151.0, bankAccountA.getBalance());

        //transfer from BankB to BankA, tenths
        bankAccountB.transfer("out",0.1);
        assertEquals(248.9, bankAccountB.getBalance());
        bankAccountA.transfer("in",0.1);
        assertEquals(151.1, bankAccountA.getBalance());

        //transfer from BankB to BankA, hundreds
        bankAccountB.transfer("out",0.01);
        assertEquals(248.89, bankAccountB.getBalance());
        bankAccountA.transfer("in",0.01);
        assertEquals(151.11, bankAccountA.getBalance());

        //throw Error if action is empty
        assertThrows(IllegalArgumentException.class, () -> bankAccountA.transfer("",12.22));

        //throw Error if action contains capital letters
        assertThrows(IllegalArgumentException.class, () -> bankAccountA.transfer("In",12.22));
        assertThrows(IllegalArgumentException.class, () -> bankAccountA.transfer("IN",12.22));
    }


    @Test
    void constructorTest()throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("-dni@cc", -0.111));
    }

}