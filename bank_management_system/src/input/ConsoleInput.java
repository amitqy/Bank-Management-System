package input;

import bank.Bank;
import exception.InvalidOptionException;
import exception.WrongInputNameException;
import model.Account;
import ui.UserInterfaceHelper;
import util.AccountUtility;

public class ConsoleInput implements ITakeInput {


    @Override
    public void read(Bank bankOne) {
        int status;
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
