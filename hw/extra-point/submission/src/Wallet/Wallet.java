package Wallet;

/**
 * Class representing a player's wallet
 * It takes care of player's money.
 */
public class Wallet {
    private int balance;

    public Wallet(int balance){
        this.balance = balance;
    }

    public Wallet(){}

    public int getBalance() {
        return balance;
    }

    //Receive money
    public void get(int amount){
        balance += amount;
    }

    //Pay money
    public void pay(int amount){
        balance -= amount;
    }

    public String toString() {
        return "$" + balance;
    }
}