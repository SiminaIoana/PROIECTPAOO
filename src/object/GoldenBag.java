package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class GoldenBag extends SuperObject {

    GamePanel gp;
    public GoldenBag(GamePanel gp)
    {
        this.gp=gp;
        name="GoldenBag";
        image= LoadImage.LoadImage("/objects/galeata_Aur.png");
        collision=true;
    }
}
