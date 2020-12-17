/**
 * Event for clicking the add stock button
 */

package gui;

import java.util.EventObject;

public class AddStockEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private String symbol;
    private String company;
    private String last;
    private String change;
    private String change_percentage;
    private String volume;
    private String traded;

    public AddStockEvent(Object source, String symbol, String company, String last,
                         String change, String change_percentage, String volume, String traded) {
        super(source);

        this.symbol = symbol;
        this.company = company;
        this.last = last;
        this.change = change;
        this.change_percentage = change_percentage;
        this.volume = volume;
        this.traded = traded;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompany() {
        return company;
    }

    public String getLast() {
        return last;
    }

    public String getChange() {
        return change;
    }

    public String getChange_percentage() {
        return change_percentage;
    }

    public String getVolume() {
        return volume;
    }

    public String getTraded() {
        return traded;
    }
}
