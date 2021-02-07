package bank;

import exception.InvalidAccountException;
import model.Account;
import ui.UserInterfaceHelper;
import util.AccountUtility;

import java.awt.desktop.SystemEventListener;
import java.util.HashMap;
import java.util.Scanner;

/***
 * Represents a Bank
 */
public class Bank {
    public HashMap<Integer, Account> accountList;

    public Bank() {
        accountList = new HashMap<>();
    }

    public static void main(String[] args) {
        int id;
        int status;
        Bank bankOne = new Bank();
        Scanner s = new Scanner(System.in);
        while (true) {
            UserInterfaceHelper.printWelcomeOptions();
            status = s.nextInt();
            // exit from system
            if (status == 4) {
                break;
            }
            // search account
            if (status == 3) {
                accountSearchHelper(bankOne);
            }
            switch (status) {
                // 1 -> make a new account
                case 1:
                    Account ac = AccountUtility.makeNewAccount();
                    bankOne.accountList.put(ac.getId(), ac);
                    break;
                // 2 -> already existing account
                case 2:
                    alreadyExistingAccountHelper(bankOne);
                    break;
                default:
                    break;
            }
        }
    }

    public static void alreadyExistingAccountHelper(Bank bankOne) {
        System.out.println(UserInterfaceHelper.PROMPT_FOR_ID_INPUT);
        Scanner s = new Scanner(System.in);
        int id = s.nextInt();
        Account ac = AccountUtility.searchAccount(bankOne, id);
        if (ac != null) {
            UserInterfaceHelper.printTextForOption3();
            int option = s.nextInt();
            switch (option) {
                case 21:
                    UserInterfaceHelper.takeInputForCredit(ac, bankOne);
                    break;
                case 22:
                    UserInterfaceHelper.takeInputForDebit(ac, bankOne);
                    break;
                case 23:
                    AccountUtility.printDetails(ac);
                    break;
                default:
                    System.out.println(UserInterfaceHelper.INVALID_OPTION_TEXT);
                    break;
            }
        } else {
            try {
                throw new InvalidAccountException();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void accountSearchHelper(Bank bankOne) {
        System.out.println(UserInterfaceHelper.ACCOUNT_SEARCH_TEXT);
        Scanner s = new Scanner(System.in);
        int id = s.nextInt();
        Account ac = AccountUtility.searchAccount(bankOne, id);
        if (ac != null) {
            AccountUtility.printDetails(ac);
        } else {
            try {
                throw new InvalidAccountException();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
