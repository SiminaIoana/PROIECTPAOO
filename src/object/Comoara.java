package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Comoara extends SuperObject {
    GamePanel gp;
    public Comoara(GamePanel gp)
    {
        this.gp=gp;
        name="Comoara";
        image=LoadImage.LoadImage("/objects/Chest_02_Locked.png");

        collision=true;
    }
}
