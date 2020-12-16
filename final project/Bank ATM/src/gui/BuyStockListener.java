package gui;

import account.SecuritiesAccount;
import assets.Stock;

import java.util.EventListener;

public interface BuyStockListener extends EventListener {
    public void buyStock(String id, int number, SecuritiesAccount account);
}
