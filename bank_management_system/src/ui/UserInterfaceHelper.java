package ui;

import bank.Bank;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.InvalidOptionException;
import model.Account;
import util.AccountUtility;

import java.util.Scanner;

/***
 * Class used for handling all user interface related elements
 */

public class UserInterfaceHelper {
    public static final String OPTION1 = "1 -> make a new account";
    public static final String OPTION2 = "2 -> already existing account";
    public static final String OPTION3 = "3 -> search for account";
    public static final String OPTION4 = "4 -> exit";
    public static final String ALPHABET_PATTERN = "^[a-zA-Z]+$";
    public static final String ACCOUNT_SEARCH_TEXT = "Enter the account id you want to search";
    public static final String ACCOUNT_DOES_NOT_EXIST_TEXT = "This account does not exist !Please create a new account";
    public static final String ENTER_YOUR_NAME_TEXT = "Enter your name";
    public static final String SAVE_FOR_FUTURE_TEXT = "Please note your id for future references ";
    public static final String PROMPT_FOR_ID_INPUT = "Enter your id";
    public static final String CREDIT_PROMPT_TEXT = "To Credit Money enter 21";
    public static final String DEBIT_PROMPT_TEXT = "To Debit Money enter 22";
    public static final String ACCOUNT_DETAILS_PROMPT_TEXT = "To Know details of account enter 23";
    public static final String MONEY_CREDIT_PROMPT_TEXT = "Enter Money you want to credit";
    public static final String MONEY_DEBIT_PROMPT_TEXT = "Enter Money you want to debit";
    public static final String REMAINING_BALANCE_LESS_TEXT = "Remaining balance is less than you want to debit";
    public static final String DEBIT_SUCCESSFUL_TEXT = "Debit successful";
    public static final String INVALID_OPTION_TEXT = "Option is Invalid";
    public static final String INVALID_MONEY_INPUT = "Money Entered is Invalid";

    static Scanner s = new Scanner(System.in);

    public static void printTextForOption3() {
        System.out.println(CREDIT_PROMPT_TEXT);
        System.out.println(DEBIT_PROMPT_TEXT);
        System.out.println(ACCOUNT_DETAILS_PROMPT_TEXT);
    }

    public static void takeInputForDebit(Account ac, Bank bank) {
        System.out.println("Your balance is " + ac.getRemainingBalance());
        System.out.println(MONEY_DEBIT_PROMPT_TEXT);
        int id = s.nextInt();
        try {
            AccountUtility.debit(id, ac);
        } catch (InsufficientFundException e) {
            e.printStackTrace();
        }
    }


    public static void takeInputForCredit(Account ac, Bank bank) {
        System.out.println(MONEY_CREDIT_PROMPT_TEXT);
        int amount = s.nextInt();
        AccountUtility.credit(amount, ac);
    }

    public static void printWelcomeOptions() {
        System.out.println(OPTION1);
        System.out.println(OPTION2);
        System.out.println(OPTION3);
        System.out.println(OPTION4);
    }

    /***
     * @param bankOne bank object
     */
    public static void accountSearchHelper(Bank bankOne) {
        System.out.println(UserInterfaceHelper.ACCOUNT_SEARCH_TEXT);
        Scanner s = new Scanner(System.in);
        int id = s.nextInt();
        try {
            Account accountObj = AccountUtility.searchAccount(bankOne, id);
            AccountUtility.printDetails(accountObj);
        } catch (InvalidAccountException e) {
             e.getMessage();
             e.printStackTrace();
        }
    }

    /***
     * @param bankOne bank object
     */
    public static void alreadyExistingAccountHelper(Bank bankOne)  {
        System.out.println(UserInterfaceHelper.PROMPT_FOR_ID_INPUT);
        Scanner s = new Scanner(System.in);
        int id = s.nextInt();
        try{
        Account accountObj = AccountUtility.searchAccount(bankOne, id);
            printTextForOption3();
            int option = s.nextInt();
            switch (option) {
                case 21:
                    takeInputForCredit(accountObj, bankOne);
                    break;
                case 22:
                    takeInputForDebit(accountObj, bankOne);
                    break;
                case 23:
                    AccountUtility.printDetails(accountObj);
                    break;
                default:
                    System.out.println(UserInterfaceHelper.INVALID_OPTION_TEXT);
                    break;
            }
        } catch (InvalidAccountException e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    public static int optionValidator() throws InvalidOptionException {
        UserInterfaceHelper.printWelcomeOptions();
        int status = s.nextInt();
        if(status >= 5){
            throw new InvalidOptionException();
        }
        return status;
    }

}
