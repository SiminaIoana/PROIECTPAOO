package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Key extends SuperObject {
    GamePanel gp;
    public Key(GamePanel gp)
    {
        this.gp=gp;
        name="Key";
        image=LoadImage.LoadImage("/objects/Key_01.png");

        collision=true;
    }
}
