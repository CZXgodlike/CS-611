# CS611_Final_Project

### I. Project objective

The task of this final project is to create a bank ATM with Java Swing GUI
that can achieve some functions of an online bank, including deposit, withdraw,
 transfer, loan, stock trading and bank management.

### II. Project design

Our project is mainly divided into four parts: **accounts, assets, controllers** and 
**GUIs**

* **Accounts:**
There are four types of accounts for this ATM: banker account, saving account,
   checking account and security account. 
  
* **Assets:**
Mainly used as assistant class for wrapping and parsing data when read file from
  csv file. 
  
* **Controllers:**
Contain different types of controllers used for controlling(reading and writing) data from different csv files.
  
* **GUIs:**
Classes for making all the GUIs of the ATM.

For data storage, we chose to store data into csv files in data folder.

### III. Main GUI introduction
* AccountWindow: The desktop of saving/checking account, from where window of available
services can be open.

* ActivityHistoryFrame: Window for displaying the activity histories of the
account.
  
* BankerAccountMainFrame: The desktop of the banker account, which will display
all the accounts in the bank divided by account type, from where can enter stock edit
  panel and daily report window.
  
* CustomerMainFrame: Main window for customer after logging in. Will display
all the accounts the customer have. Can enter the main menu of a certain account by
  double clicking the row of the account.
  
* SecurityAccountMainFrame: Desktop of security accounts. Will display all the
stocks the account has. Customer can sell their stocks at latest price by right
  clicking the row of a certain stock. Also can enter stock market and 
  
* StockMarketFrame: Window for displaying the stock information. Can refresh by
clicking the refresh button on the left top. Right click the stock can choose
  to buy it.
  
* 


