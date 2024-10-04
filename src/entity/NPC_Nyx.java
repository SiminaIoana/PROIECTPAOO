package entity;

import main.GamePanel;
import main.LoadImage;
import main.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;


//Entitate care doar se misca pe ecran si nu face nimic
public class NPC_Nyx extends Entity{


    public NPC_Nyx(GamePanel gp)
    {
        super(gp);
        direction="down";
        speed=1;
        GetNPCImage();
        name="NPC";

    }

    public void GetNPCImage()
    {
        SpriteSheet Nyx = new SpriteSheet(LoadImage.LoadImage("/npc/nyx.png"));

        /////Vector de animatie pentru mers sus
        up = new BufferedImage[3];
        up[0] =  Nyx.crop(0, 1);
        up[1] =  Nyx.crop(0, 1);
        up[2] =  Nyx.crop(0, 1);



        //Vector animatie mers in jos
        down = new BufferedImage[3];
        down[0] =  Nyx.crop(4, 0);
        down[1] =  Nyx.crop(5, 0);
        down[2] =  Nyx.crop(6, 0);




        //vector animatie mers stg
        left = new BufferedImage[3];
        left[0] =  Nyx.crop(3, 1);
        left[1] =  Nyx.crop(4, 1);
        left[2] =  Nyx.crop(3, 1);


        //Vector animatie mers drt
        right = new BufferedImage[3];
        right[0] =  Nyx.crop(0, 1);
        right[1] =  Nyx.crop(1, 1);
        right[2] =  Nyx.crop(0, 1);

    }

    public void SetAction()
    {
        actionLockCounter++;
        //la fiecare 2 secunde se schimba directia
        if(actionLockCounter==120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;        //alegem un nr de la 0 la100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter=0;
        }
    }
}

