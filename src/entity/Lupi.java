package entity;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;
import main.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

//Monstii care urmaresc playerul pe harta
public class Lupi extends Entity {


    public Lupi(GamePanel gp) {
        super(gp);
        speed = 2;
        type = 1;
        maxLife = 1;
        Life = maxLife;
        name="Lupi";
        attack=3;
        solidArea.x = 10;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();

    }

    public void getImage() {

        SpriteSheet Lupi = new SpriteSheet(LoadImage.LoadImage("/Lupi/LupMers.png"));
        SpriteSheet Lupi1 = new SpriteSheet(LoadImage.LoadImage("/Lupi/LupAtac.png"));

        idle = Lupi.crop(0, 2);

        /////Vector de animatie pentru mers sus
        up = new BufferedImage[3];
        up[0] = Lupi.crop(3, 0);
        up[1] = Lupi.crop(1, 0);
        up[2] = Lupi.crop(6, 0);

        //Vector animatie mers in jos
        down = new BufferedImage[3];
        down[0] = Lupi.crop(6, 2);
        down[1] = Lupi.crop(2, 2);
        down[2] = Lupi.crop(3, 2);

        //vector animatie mers stg
        left = new BufferedImage[3];
        left[0] = Lupi.crop(1, 1);
        left[1] = Lupi.crop(2, 1);
        left[2] = Lupi.crop(4, 1);

        //Vector animatie mers drt
        right = new BufferedImage[3];
        right[0] = Lupi.crop(1, 3);
        right[1] = Lupi.crop(2, 3);
        right[2] = Lupi.crop(4, 3);

        //animatie pentru atac  //animatie pentru atac drt
        atacDrt = new BufferedImage[3];
        atacDrt[0] = Lupi1.crop(3, 3);
        atacDrt[1] = Lupi1.crop(4, 3);
        atacDrt[2] = Lupi1.crop(5, 3);

        //animatie pentru atac stg
        atacStg = new BufferedImage[3];
        atacStg[0] = Lupi1.crop(3, 1);
        atacStg[1] = Lupi1.crop(4, 1);
        atacStg[2] = Lupi1.crop(5, 1);

        //animatie pentru atac sus
        atacUp = new BufferedImage[3];
        atacUp[0] = Lupi1.crop(3, 0);
        atacUp[1] = Lupi1.crop(4, 0);
        atacUp[2] = Lupi1.crop(5, 0);

        //animatie pentru atac jos
        atacDown = new BufferedImage[3];
        atacDown[0] = Lupi1.crop(3, 2);
        atacDown[1] = Lupi1.crop(4, 2);
        atacDown[2] = Lupi1.crop(5, 2);
    }

    public void update()
    {
        //apelam metoda din super clasa(Entity)
        super.update();

        int xDistance=Math.abs(Worldx-gp.player.Worldx);
        int yDistance=Math.abs(Worldy-gp.player.Worldy);
        int tileDistance=(xDistance+yDistance)/gp.TILE_SIZE;

        if(onPath==false && tileDistance<5)
        {
                onPath=true;
                attacking=true;
        }
         if(onPath == true && tileDistance >5)
         {
            onPath=false;
            attacking=false;
         }

    }

    public void SetAction() {
        if(onPath==true)
        {
            int goalCol=(gp.player.Worldx+gp.player.solidArea.x)/gp.TILE_SIZE;
            int goalRow=(gp.player.Worldy+gp.player.solidArea.y)/gp.TILE_SIZE;

            searchPath(goalCol,goalRow);
        }
        else {
            actionLockCounter++;
            //la fiecare 1 secunda se schimba directia
            if (actionLockCounter == 60) {
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
                actionLockCounter = 0;
            }
        }
    }

    //Reactie la atac
    public void ReactionAtDamage()
    {
        actionLockCounter=0;
        //lupii se indeparteaza de player cand sunt atacati
       // direction=gp.player.direction;
        onPath=true;
    }
}
