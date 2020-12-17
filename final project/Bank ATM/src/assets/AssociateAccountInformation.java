/**
 * Class used for storing user's owned account information
 */
package assets;

import java.util.List;

public class AssociateAccountInformation {

    private String userName;
    private String associateAccounts;

    public AssociateAccountInformation(List<String> data){

        this.userName = data.get(0);
        this.associateAccounts = data.get(2);
    }

    public String getUserName() {
        return userName;
    }

    public String getAssociateAccounts() {
        return associateAccounts;
    }
}
