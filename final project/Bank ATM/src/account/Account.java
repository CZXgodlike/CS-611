package account;
/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Abstract account class
 */
import java.io.File;
import java.util.*;

public abstract class Account{

    protected String id; // Account/Rountine number

    /** Default Constructor */
    public Account(){
        this.id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    /** Utilities */
    public String getId(){return this.id;}

}

