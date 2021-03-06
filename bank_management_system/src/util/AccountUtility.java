package util;

import bank.Bank;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.InvalidOptionException;
import exception.WrongInputNameException;
import model.Account;
import ui.UserInterfaceHelper;

import java.util.Scanner;

public class AccountUtility {
    static String name;
    static int id;


    /***
     * @return Id after generation is returned
     */
    public static int getAccountId() {
        id = (int) (Math.random() * 10000);
        return id;
    }

    /**
     * @return an Account object
     */
    public static Account makeNewAccount() throws WrongInputNameException {
        Scanner s = new Scanner(System.in);
        System.out.println(UserInterfaceHelper.ENTER_YOUR_NAME_TEXT);
        name = s.next();
        if(nameValidated(name)) {
            id = getAccountId();
            System.out.println(UserInterfaceHelper.SAVE_FOR_FUTURE_TEXT + id);
            return new Account(name, id);
        }
        else {
            throw new WrongInputNameException();
        }
    }

    private static boolean nameValidated(String name) {
        return name.matches(UserInterfaceHelper.ALPHABET_PATTERN);
    }

    /***
     * @param accountObject : The account object
     */
    public static void printDetails(Account accountObject) {
        System.out.println("Name: " + accountObject.getName());
        System.out.println("Id: " + accountObject.getId(

        ));
        System.out.println("Remaining balance: " + accountObject.getRemainingBalance());
    }

    /***
     * @param bank : A bank object representing a Bank
     * @param id : Id associated to a particular account object.
     * @return The Account object is returned after searching the list of all accounts that are made in the bank
     */
    public static Account searchAccount(Bank bank, int id) throws InvalidAccountException{
        if (bank.accountList.containsKey(id)) {
            return bank.accountList.get(id);
        }else {
            throw new InvalidAccountException();
        }
    }

    /***
     * @param amount The amount is Rupees that is to be credited
     * @param account The account object in which amount has to credited
     */
    public static void credit(int amount, Account account) {
        if (amount <= 0)
            System.out.println(UserInterfaceHelper.INVALID_MONEY_INPUT);
        else
            account.setRemainingBalance(account.getRemainingBalance() + amount);
    }

    /***
     * @param amount The amount is Rupees that is to be debited
     * @param account The account object from which amount has to debited
     * @return true if account has sufficient balance to be debited, else return false
     */
    public static boolean debit(int amount, Account account) throws InsufficientFundException {
        if (account.getRemainingBalance() >= amount) {
            account.setRemainingBalance(account.getRemainingBalance() - amount);
            return true;
        }
        else {
            throw new InsufficientFundException();
        }
    }
}
