package bank;

import input.ConsoleInput;
import input.FileReader;
import model.Account;
import ui.UserInterfaceHelper;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

/***
 * Represents a Bank
 */
public class Bank {
    public HashMap<Integer, Account> accountList;

    private Bank() {
        accountList = new HashMap<>();
    }

    public static void main(String[] args) {
        Bank bankOne = new Bank();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            UserInterfaceHelper.log.log(Level.INFO,UserInterfaceHelper.START_OPTION1);
            UserInterfaceHelper.log.log(Level.INFO,UserInterfaceHelper.START_OPTION2);
            UserInterfaceHelper.log.log(Level.INFO,UserInterfaceHelper.START_OPTION3);
            int userOption = inputScanner.nextInt();
            if (userOption == 1) {
                ConsoleInput consoleInput = new ConsoleInput();
                consoleInput.read(bankOne);

            } else if(userOption == 2) {
                FileReader fileInput = new FileReader();
                fileInput.read(bankOne);
            }
            else{
                break;
            }
        }

    }

}
