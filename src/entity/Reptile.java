package entity;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;
import main.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Reptile extends Entity {


    public Reptile(GamePanel gp) {
        super(gp);
        speed=1;
        type=3;
        maxLife=1;
        Life=maxLife;
        name="Reptile";
        solidArea.x=10;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        getImage();

    }

    public void getImage()
    {

        SpriteSheet Reptile = new SpriteSheet(LoadImage.LoadImage("/Reptila/ReptilaMers.png"));
        //SpriteSheet Lupi1 = new SpriteSheet(LoadImage.LoadImage("/Lupi/LupAtac.png"));

        idle = Reptile.crop(0, 2);

        /////Vector de animatie pentru mers sus
        up = new BufferedImage[3];
        up[0] = Reptile.crop(3, 0);
        up[1] = Reptile.crop(1, 0);
        up[2] = Reptile.crop(6, 0);

        //Vector animatie mers in jos
        down = new BufferedImage[3];
        down[0] = Reptile.crop(6, 2);
        down[1] = Reptile.crop(2, 2);
        down[2] = Reptile.crop(3, 2);

        //vector animatie mers stg
        left = new BufferedImage[3];
        left[0] = Reptile.crop(1, 1);
        left[1] = Reptile.crop(2, 1);
        left[2] = Reptile.crop(4, 1);

        //Vector animatie mers drt
        right = new BufferedImage[3];
        right[0] = Reptile.crop(1, 3);
        right[1] = Reptile.crop(2, 3);
        right[2] = Reptile.crop(4, 3);

        //animatie pentru atac  //animatie pentru atac drt
        atacDrt= new BufferedImage[3];
        atacDrt[0]=Reptile.crop(3,3);
        atacDrt[1]=Reptile.crop(4,3);
        atacDrt[2]=Reptile.crop(5,3);

        //animatie pentru atac stg
        atacStg= new BufferedImage[3];
        atacStg[0]=Reptile.crop(3,1);
        atacStg[1]=Reptile.crop(4,1);
        atacStg[2]=Reptile.crop(5,1);

        //animatie pentru atac sus
        atacUp= new BufferedImage[3];
        atacUp[0]=Reptile.crop(3,0);
        atacUp[1]=Reptile.crop(4,0);
        atacUp[2]=Reptile.crop(5,0);

        //animatie pentru atac jos
        atacDown= new BufferedImage[3];
        atacDown[0]=Reptile.crop(3,2);
        atacDown[1]=Reptile.crop(4,2);
        atacDown[2]=Reptile.crop(5,2);
    }

    public void SetAction()
    {
        actionLockCounter++;
        //la fiecare 2 secunde se schimba directia
        if(actionLockCounter==60) {
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

