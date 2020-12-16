package Creatures;

/**
 * Class represents a numeric attribute of hero
 * For example, HP
 */

public class NumericAttribute {

    private String name;
    private int stat;
    private int maxStat = 999999;

    public NumericAttribute(){}

    public NumericAttribute(String name){
        this.name = name;
    }

    public int getStat() {
        return stat;
    }

    public int getMaxStat() {
        return maxStat;
    }

    public void setStat(int stat) {
        this.stat = Math.min(stat, maxStat);
    }

    public void setMaxStat(int maxStat) {
        this.maxStat = maxStat;
    }

    public void increase(int amount, Hero hero){
        if(stat + amount >= maxStat){
            stat = maxStat;
        } else {
            stat += amount;
        }
        System.out.println("\033[0;32m" + hero + "'s "+ this + " has increased to " + stat + ".");
    }

    public void decrease(int amount, Hero hero){
        if(stat - amount < 0){
            stat = 0;
        } else {
            stat -= amount;
        }
        System.out.println("\033[0;36m" + hero + "'s " + this + " has decreased to " + stat + ".\n");
    }

    public void printStat(){
        int i = stat * 10 / maxStat;
        System.out.println("\033[0;33m" + this + ": " + "\u2588".repeat(i) + "\u25AF".repeat(10 - i) + "(" + stat + "/" + maxStat + ")");
    }

    @Override
    public String toString() {
        return name;
    }
}
