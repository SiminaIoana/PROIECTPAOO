package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Diamant extends SuperObject {
    GamePanel gp;
    public Diamant(GamePanel gp)
    {
        this.gp=gp;
        name="Diamant";
        image=LoadImage.LoadImage("/DIAMANT/Diamond.png");

        collision=true;
    }
}
