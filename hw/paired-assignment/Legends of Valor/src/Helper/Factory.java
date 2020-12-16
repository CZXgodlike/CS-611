package Helper;

/**
 * Abstract class represent a factory.
 * A factory is mainly used for creating things according to given name
 */

public abstract class Factory implements Create{
    protected final DataBase dataBase;

    public Factory(){
        dataBase = new DataBase();
    }

}
