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

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
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
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));
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