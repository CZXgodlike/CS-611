package Stuffs;

/**
 * Class represents a wallet of heroes
 */
public class Wallet {

    private int balance;

    public Wallet(int balance){
        this.balance = balance;
    }

    public Wallet(){}

    public void pay(int amount){
        balance -= amount;
    }

    public void earn(int amount){
        System.out.println();
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public boolean canAfford(int price){
        return balance - price >= 0;
    }

    @Override
    public String toString() {
        return "\033[0;33m" + "Balance: $" + balance;
    }
}
