package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Door extends SuperObject {

    GamePanel gp;
    public Door(GamePanel gp)
    {
        this.gp=gp;
        name="Door";
        image= LoadImage.LoadImage("/objects/door.png");
        collision=true;
    }
}
