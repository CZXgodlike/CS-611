/**
 * Class used for storing a line of account information from csv
 */
package assets;

public class AccountInformation {
    private String type;
    private String id;

    public AccountInformation() {}

    public AccountInformation(String path){
        type = path.substring(0, 1);
        id = path.substring(1);
    }

    public String getType() {
        return switch (type){
            case "A" -> "Saving account";
            case "C" -> "Checking account";
            case "S" -> "Security account";
            default -> null;
        };
    }

    public String getId() {
        return id;
    }
}
