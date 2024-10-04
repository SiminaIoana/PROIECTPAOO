package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Coin extends SuperObject {
        GamePanel gp;
    public Coin(GamePanel gp)
    {
        this.gp=gp;
        name="Coin";
        image=LoadImage.LoadImage("/objects/Coin.png");

        collision=true;
    }
}
