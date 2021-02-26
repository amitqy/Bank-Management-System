package input;

import bank.Bank;
import exception.InsufficientFundException;
import model.Account;
import util.AccountUtility;

import java.io.*;
import java.util.Properties;

public class FileReader implements ITakeInput {

    @Override
    public void read(Bank bankOne) {
        Properties p = new Properties();
        try {
            InputStream is = new FileInputStream("resources/InputFile.properties");
            p.load(is);
            Account ac = new Account(p.getProperty("Name"),Integer.parseInt(p.getProperty("Id")));
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
