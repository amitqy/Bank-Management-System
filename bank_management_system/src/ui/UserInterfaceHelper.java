package ui;

import bank.Bank;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.InvalidOptionException;
import exception.WrongInputNameException;
import model.Account;
import util.AccountUtility;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
    public static final LogManager logManager = LogManager.getLogManager();
    public static final Logger log = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);


    static Scanner s = new Scanner(System.in);


    public static void printTextForOption3() {
        log.log(Level.INFO, CREDIT_PROMPT_TEXT);
        log.log(Level.INFO, DEBIT_PROMPT_TEXT);
        log.log(Level.INFO, ACCOUNT_DETAILS_PROMPT_TEXT);
    }

    public static void takeInputForDebit(Account ac, Bank bank) {
        log.log(Level.INFO, "Your balance is " + ac.getRemainingBalance());
        log.log(Level.INFO, MONEY_DEBIT_PROMPT_TEXT);
        int id = s.nextInt();
        try {
            AccountUtility.debit(id, ac);
        } catch (InsufficientFundException e) {
            e.printStackTrace();
        }
    }


    public static void takeInputForCredit(Account ac, Bank bank) {
        log.log(Level.INFO, MONEY_CREDIT_PROMPT_TEXT);
        int amount = s.nextInt();
        AccountUtility.credit(amount, ac);
    }

    public static void printWelcomeOptions() {
        log.log(Level.INFO, OPTION1);
        log.log(Level.INFO, OPTION2);
        log.log(Level.INFO, OPTION3);
        log.log(Level.INFO, OPTION4);
    }

    /***
     * @param bankOne bank object
     */
    public static void accountSearchHelper(Bank bankOne) {
        log.log(Level.INFO,ACCOUNT_SEARCH_TEXT);
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
        log.log(Level.INFO,PROMPT_FOR_ID_INPUT);
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
                    log.log(Level.WARNING,INVALID_OPTION_TEXT);
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
        if(status >= 6){
            throw new InvalidOptionException();
        }
        return status;
    }
    public static void takeInputFromFile(Bank bankOne){
        Properties p = new Properties();
        try {
            OutputStream os = new FileOutputStream("dataConfig.properties");
            p.setProperty("Name","Amit Padaliya");
            Integer id = AccountUtility.getAccountId();
            p.setProperty("Id",id.toString());
            Account ac = new Account(p.getProperty("Name"),Integer.parseInt(p.getProperty("Id")));
            p.setProperty("credit","6000");
            p.setProperty("debit","2000");
            p.store(os,null);
            InputStream is = new FileInputStream("dataConfig.properties");
            p.load(is);
            bankOne.accountList.put(Integer.parseInt(p.getProperty("Id")),ac);
            AccountUtility.credit(Integer.parseInt(p.getProperty("credit")),ac);
            AccountUtility.debit(Integer.parseInt(p.getProperty("debit")),ac);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InsufficientFundException e) {
            e.printStackTrace();
        }
    }

}
