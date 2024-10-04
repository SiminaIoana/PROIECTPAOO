package main;


//sablonul singleton care ne ajuta sa fim singuri ca avem o singura instanta a jocului
public class GameSingleton {

    private static GameWindow game=null;

    //CONSTRUCTORUL ESTE MEREU PRIVAT
    private GameSingleton(){};

    public static GameWindow getGameInstance() {
        if (game == null)
            game = new GameWindow();

        return game;
    }
}
