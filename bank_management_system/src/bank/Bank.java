package bank;

import exception.InvalidAccountException;
import exception.InvalidOptionException;
import exception.WrongInputNameException;
import model.Account;
import ui.UserInterfaceHelper;
import util.AccountUtility;

import javax.management.openmbean.InvalidOpenTypeException;
import java.awt.desktop.SystemEventListener;
import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/***
 * Represents a Bank
 */
public class Bank {
    public HashMap<Integer, Account> accountList;

    private Bank() {
        accountList = new HashMap<>();
    }

    public static void main(String[] args) {
        int status;
        Bank bankOne = new Bank();
        while (true) {
            try {
                status = UserInterfaceHelper.optionValidator();
                if (status == 4) break;
                switch (status) {
                    // 1 -> make a new account
                    case 1:
                        try {
                            Account accountObj = AccountUtility.makeNewAccount();
                            bankOne.accountList.put(accountObj.getId(), accountObj);
                        } catch (WrongInputNameException e) {
                            e.printStackTrace();
                        }
                        break;
                    // 2 -> already existing account
                    case 2:
                        UserInterfaceHelper.alreadyExistingAccountHelper(bankOne);
                        break;
                    case 3:
                        UserInterfaceHelper.accountSearchHelper(bankOne);
                        break;
                    default:
                        break;
                }
            } catch (InvalidOptionException e) {
                  e.getMessage();
                  e.printStackTrace();
            }
        }
    }

}
