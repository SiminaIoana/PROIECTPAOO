package entity;

import entity.Entity;
import main.GamePanel;
import main.LoadImage;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

//CLASA CARE AJUTA LA CREEAREA BOSS-ului
public class Caleb extends Entity {


    //constructorul in care se initializeaza toate campurile
    // mosntrului , stiind ca la baza e o entitate
    public Caleb(GamePanel gp) {

        super(gp);

        speed = 3;
        type = 4;
        maxLife = 3;
        Life = maxLife;
        name="Caleb";
        attack=3;
        solidArea.x = 10;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 64;
        solidArea.height = 32;

        //imaginile folosite in animatia playerului
        getImage();
        GetAtackCalebImage();

    }


    //Metoda de obtinere a animatiilor se bazeaza pe incarcarea unui sprite sheet care va fi
    // "decupat" in functia de animatia dorita la un anumit moment sau apasare de tasta
    public void getImage() {

        SpriteSheet Caleb = new SpriteSheet(LoadImage.LoadImage("/Caleb/gnom-mers.png"));
        idle = Caleb.crop(0, 2);

        /////Vector de animatie pentru mers sus
        up = new BufferedImage[3];
        up[0] = Caleb.crop(3, 0);
        up[1] = Caleb.crop(1, 0);
        up[2] = Caleb.crop(6, 0);

        //Vector animatie mers in jos
        down = new BufferedImage[3];
        down[0] = Caleb.crop(6, 2);
        down[1] = Caleb.crop(2, 2);
        down[2] = Caleb.crop(3, 2);

        //vector animatie mers stg
        left = new BufferedImage[3];
        left[0] = Caleb.crop(1, 1);
        left[1] = Caleb.crop(2, 1);
        left[2] = Caleb.crop(4, 1);

        //Vector animatie mers drt
        right = new BufferedImage[3];
        right[0] = Caleb.crop(1, 3);
        right[1] = Caleb.crop(2, 3);
        right[2] = Caleb.crop(4, 3);

    }

    public void GetAtackCalebImage()
    {
        SpriteSheet Caleb1 = new SpriteSheet(LoadImage.LoadImage("/Caleb/CalebAttack_down.png"));
        SpriteSheet Caleb2 = new SpriteSheet(LoadImage.LoadImage("/Caleb/CalebAttack_up.png"));
        SpriteSheet Caleb3 = new SpriteSheet(LoadImage.LoadImage("/Caleb/CalebAttack_left.png"));
        SpriteSheet Caleb4 = new SpriteSheet(LoadImage.LoadImage("/Caleb/CalebAttack_right.png"));


        //animatie pentru atac drt
        atacDrt= new BufferedImage[3];
        atacDrt[0]=Caleb4.crop(0,0);
        atacDrt[1]=Caleb4.crop(3,0);
        atacDrt[2]=Caleb4.crop(6,0);

        // animatie pentru atac stg
        atacStg= new BufferedImage[3];
        atacStg[0]=Caleb3.crop(0,0);
        atacStg[1]=Caleb3.crop(3,0);
        atacStg[2]=Caleb3.crop(6,0);

        //animatie pentru atac sus
        atacUp= new BufferedImage[3];
        atacUp[0]=Caleb2.crop(3,0);
        atacUp[1]=Caleb2.crop(6,0);
        atacUp[2]=Caleb2.crop(6,0);

        //animatie pentru atac jos
        atacDown= new BufferedImage[3];
        atacDown[0]=Caleb1.crop(0,0);
        atacDown[1]=Caleb1.crop(3,0);
        atacDown[2]=Caleb1.crop(6,0);
    }

    //Functie de desenare separata deoarece avem nevoie si de atac, iar clasa entity nu are optiunile acestea
    public void Draw(Graphics2D g2) {
        int ScreenX = Worldx - gp.player.Worldx + gp.player.ScreenX;
        int ScreenY = Worldy - gp.player.Worldy + gp.player.ScreenY;
        int scale=2;
        {
            switch (direction) {
                case "up":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 1) {
                            g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(atacUp[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 1) {
                            g2.drawImage(atacUp[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(atacUp[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    break;
                case "down":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 1) {
                            g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(atacDown[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 1) {
                            g2.drawImage(atacDown[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(atacDown[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    break;
                case "left":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 1) {
                            g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    } else {
                        if (spriteNum == 0) {
                            g2.drawImage(atacStg[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 1) {
                            g2.drawImage(atacStg[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(atacStg[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    break;
                case "right":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }

                        if (spriteNum == 1) {
                            g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 0) {
                            g2.drawImage(atacDrt[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }

                        if (spriteNum == 1) {
                            g2.drawImage(atacDrt[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                        if (spriteNum == 2) {
                            g2.drawImage(atacDrt[spriteNum], ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
                        }
                    }
                    break;
                default:
                    g2.drawImage(idle, ScreenX, ScreenY, gp.boss_size, gp.boss_size, null);
            }
        }
    }


    //functia care ajuta la schimbarea pozitiei Monstrului pe ecran, dar si a starii sale
    public void update()
    {
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



//FUnctie care apeleaza functia de cautarea a caii catre player
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


    //atunci cand este atacat, ataca la randul sau
    public void ReactionAtDamage()
    {
        actionLockCounter=0;

        onPath=true;
    }
}
