package Players;

import java.util.List;
import java.util.Random;

public class Players<T extends Player> {

    protected List<T> players;

    public Players(){}

    public Players(List<T> players){
        this.players = players;
    }

    public List<T> getPlayers() {
        return players;
    }

    public void setPlayers(List<T> players){
        this.players =  players;
    }

    public int getPlayerNumber(){
        return players.size();
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

    public boolean nameToken(String name, int index){
        if (index == 0)
            return false;
        else
            for(int i = 0; i < index; i ++ ){
                if(players.get(i).getName().equals(name))
                    return true;
            }
            return false;
    }

    public Player popRandomPlayer(){
        int rnd = new Random().nextInt(players.size());
        Player player = players.get(rnd);
        players.remove(player);
        return player;
    }

    public T richestPlayer(){
        int balance = 0;
        T rich = null;
        for(T player: players){
            if(player.getBalance() > balance){
                rich = player;
            }
        }
        return rich;
    }

    public boolean hasBankruptPlayer(){
        for(T player: players){
            if(player.isBankrupt()){
                return true;
            }
        }
        return false;
    }

}
