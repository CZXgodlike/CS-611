/**
 * Class represents a customer
 */
package assets;

import account.Account;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import utils.ReadFileUtil;
import utils.WriteFileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Customer implements User {
    private String userName;
    private List<Account> customerAccts;

    public Customer(String userName, List<Account> accts){
        setUserName(userName);
        setCustomerAccts(accts);
    }

    public Customer(String userName){
        this(userName,new ArrayList<>());
    }

    public Customer(){
        this("", new ArrayList<>());
    }

    public void setCustomerAccts(List<Account> customerAcct) {
        this.customerAccts = customerAcct;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Account> getAccounts() {
        return this.customerAccts;
    }

    public void setPass(String pass){
        File customerData = ReadFileUtil.getPathToCustomerData();
        try {
            CSVReader reader = new CSVReader(new FileReader(customerData));
            List<String[]> data = reader.readAll();
            for(String[] d: data){
                if(d[0].equalsIgnoreCase(this.userName)){
                    d[1] = pass;
                }
            }
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(customerData));
            writer.writeAll(data);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }
}
