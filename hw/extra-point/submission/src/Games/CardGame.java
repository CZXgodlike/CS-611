package Games;

import Players.Player;
import Players.Players;
import UserInterfaces.GameInterface;

import java.util.List;

public abstract class CardGame extends Game{

    protected GameInterface gameInterface;
    protected Players players;

    public CardGame(){}

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
