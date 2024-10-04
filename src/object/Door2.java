package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Door2 extends SuperObject {

    GamePanel gp;
    public Door2(GamePanel gp)
    {
        this.gp=gp;
        name="Door2";
        image= LoadImage.LoadImage("/objects/usa2.png");
        collision=true;
    }
}
