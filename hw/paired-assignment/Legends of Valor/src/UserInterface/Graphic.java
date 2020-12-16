package UserInterface;

import Helper.FileReader;

/**
 * Class represent a graphic base of graphics of the game
 */
public class Graphic {

    private final StringBuilder banner;
    private final StringBuilder market;
    private final StringBuilder levelUp;
    private final StringBuilder helper;
    private final StringBuilder castle;
    private final StringBuilder skeleton;

    public Graphic(){
        FileReader fr = new FileReader();
        banner = fr.readGraphic("./graphic/Banner.txt");
        banner.insert(0, "\033[0;31m"); //Banner is red.
        levelUp = fr.readGraphic("./graphic/LevelUp.txt");
        levelUp.insert(0, "\033[0;32m"); //LevelUp sign is green.
        market = fr.readGraphic("./graphic/Market.txt");
        market.insert(0, "\033[0;37m"); //Market sigh is white.
        helper = fr.readGraphic("./file/helper.txt");
        helper.insert(0, "\033[0;33m"); //helper is yellow.
        castle = fr.readGraphic("./graphic/Castle.txt");
        castle.insert(0, "\033[0;32m");//Castle is green.
        skeleton = fr.readGraphic("./graphic/Skeleton.txt");
        skeleton.insert(0, "\033[0;31m");//Skeleton is red.
    }

    public StringBuilder getBanner() {
        return banner;
    }

    public StringBuilder getLevelUp() {
        return levelUp;
    }

    public StringBuilder getMarket() {
        return market;
    }

    public StringBuilder getHelper() {
        return helper;
    }

    public StringBuilder getCastle() {
        return castle;
    }

    public StringBuilder getSkeleton() {
        return skeleton;
    }
}
