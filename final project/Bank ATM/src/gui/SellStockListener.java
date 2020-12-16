package gui;

import account.SecuritiesAccount;

import java.awt.event.ActionListener;
import java.util.EventListener;

public interface SellStockListener extends EventListener {

    void sellStock(String id, int number, SecuritiesAccount account);
}
