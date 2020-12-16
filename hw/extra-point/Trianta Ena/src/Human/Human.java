package Human;

import Wallet.Wallet;

public abstract class Human {

    protected String name;
    protected Wallet wallet;

    public Human(){}

    public Human(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBalance(){
        return wallet.getBalance();
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isBankrupt(){
        return wallet.getBalance() <= 0;
    }

    public void earn(int value){
        wallet.get(value);
    }

    public void lose(int value) {wallet.pay(value);}

    public Wallet getWallet() {
        return wallet;
    }
}
