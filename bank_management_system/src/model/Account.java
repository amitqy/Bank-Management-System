package model;

/***
 * Represents a bank account
 */

public class Account {
    private String name;
    private final int id;
    private int remainingBalance;

    public Account(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /***
     * @return Returns remaining balance of an account
     */
    public int getRemainingBalance() {
        return remainingBalance;
    }

    /***
     * @return The name of the account holder
     */
    public String getName() {
        return name;
    }

    /***
     * @return Is the id of the account holder
     */
    public int getId() {
        return id;
    }

    /**
     * @param name It is name of the account holder, that is to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param remainingBalance It is used to set the remaining balance of an account
     */
    public void setRemainingBalance(int remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}