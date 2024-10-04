package object;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;

public class Heart extends SuperObject {

    GamePanel gp;
    public Heart(GamePanel gp)
    {
        this.gp=gp;
        //3 imagini
        name="Heart";
        image= LoadImage.LoadImage("/objects/heart_full.png");
        image2= LoadImage.LoadImage("/objects/heart_half.png");
        image3= LoadImage.LoadImage("/objects/heart_blank.png");

    }
}
