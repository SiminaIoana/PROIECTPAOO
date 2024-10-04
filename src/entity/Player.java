package entity;

import main.GamePanel;
import main.KeyHandler;
import main.SpriteSheet;
import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;

    //indica unde se va desena playerul pe ecran
    public final int ScreenX;
    public final int ScreenY;

    //Numarul de banuti stransi
    public int CoinNR=0;
    public int KeyNr=0;
    public int GoldNr=0;

    public Player(GamePanel gp, KeyHandler keyH)
    {
       super(gp);
        this.keyH=keyH;
        name="player";

        //Camera
        ScreenX=gp.SCREEN_WIDTH/2;
        ScreenY=gp.SCREEN_HEIGHT/2;

        //Box pentru coliziuni
        solidArea= new Rectangle();
        solidArea.x=10;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        attackAria.width=36;
        attackAria.height=36;

        //Valori initiale
        setDefaultValues();
        //Incarcarea imaginii playerului
        GetPlayerImage();
        //Incarcarea imaginii de atac
        GetAtackPlayerImage();
    }

    public void setDefaultPositions()
    {
        Worldx=gp.TILE_SIZE*8;
        Worldy=gp.TILE_SIZE*10;
        speed=5;
        direction="idle";
    }

    public void RestoreLife()
    {
        Life=maxLife;
        invincible=false;
    }
    public void setDefaultValues()
    {
        //sunt pozitiile playerului pe mapa
        Worldx=gp.TILE_SIZE*10;
        Worldy=gp.TILE_SIZE*6;
        speed=4;
        direction="idle";

        //player status
        //6 inseamna 3 vieti deoarece 1 inseamna o viata si 2 inseamna 2 vieti
        //3 inimi=>6 vieti
        maxLife=6;
        Life=maxLife;
        level=1;
        //are nevoie de experienta 5 pentru a putea trece mai departe
        nextLevelExp=5;
        coin=0;

    }

    public void GetPlayerImage()
    {
        SpriteSheet Herra = new SpriteSheet(LoadImage.LoadImage("/Herra/Mers.png"));
        idle = Herra.crop(0, 2);

        /////Vector de animatie pentru mers sus
        up = new BufferedImage[3];
        up[0] = Herra.crop(3, 0);
        up[1] = Herra.crop(1, 0);
        up[2] = Herra.crop(6, 0);

        //Vector animatie mers in jos
        down = new BufferedImage[3];
        down[0] = Herra.crop(6, 2);
        down[1] = Herra.crop(2, 2);
        down[2] = Herra.crop(3, 2);

        //vector animatie mers stg
        left = new BufferedImage[3];
        left[0] = Herra.crop(1, 1);
        left[1] = Herra.crop(2, 1);
        left[2] = Herra.crop(4, 1);

        //Vector animatie mers drt
        right = new BufferedImage[3];
        right[0] = Herra.crop(1, 3);
        right[1] = Herra.crop(2, 3);
        right[2] = Herra.crop(4, 3);


    }

    public void GetAtackPlayerImage()
    {
        SpriteSheet Herra1 = new SpriteSheet(LoadImage.LoadImage("/Herra/atackDown.png"));
        SpriteSheet Herra2 = new SpriteSheet(LoadImage.LoadImage("/Herra/atackUp.png"));
        SpriteSheet Herra3 = new SpriteSheet(LoadImage.LoadImage("/Herra/atackLeft.png"));
        SpriteSheet Herra4 = new SpriteSheet(LoadImage.LoadImage("/Herra/atackRight.png"));


        //animatie pentru atac drt
        atacDrt= new BufferedImage[3];
        atacDrt[0]=Herra4.crop(0,0);
        atacDrt[1]=Herra4.crop(3,0);
        atacDrt[2]=Herra4.crop(6,0);

       // animatie pentru atac stg
        atacStg= new BufferedImage[3];
        atacStg[0]=Herra3.crop(0,0);
        atacStg[1]=Herra3.crop(3,0);
        atacStg[2]=Herra3.crop(6,0);

        //animatie pentru atac sus
        atacUp= new BufferedImage[3];
        atacUp[0]=Herra2.crop(3,0);
        atacUp[1]=Herra2.crop(6,0);
        atacUp[2]=Herra2.crop(9,0);

        //animatie pentru atac jos
        atacDown= new BufferedImage[3];
        atacDown[0]=Herra1.crop(0,0);
        atacDown[1]=Herra1.crop(3,0);
        atacDown[2]=Herra1.crop(6,0);
    }
    public void Update() {

        if(attacking)
        {
            attack();
        }

        if (keyH.up || keyH.down || keyH.left|| keyH.right) {
            if (keyH.right ) {
                direction = "right";
            } else {
                Worldx = Worldx + 0;
            }

            if (keyH.left) {
                direction = "left";
            }

            if (keyH.up ) {
                direction = "up";
            } else {
                Worldy = Worldy - 0;
            }
            if (keyH.down ) {
                direction = "down";
            }

            //CHECK TILE COLISSION
            collisionOn = false;
            gp.cChecker.checkTile(this);


            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //coliziunea cu npc
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            Interact(npcIndex);

            //coliziune cu lupi
            int LupiIndex = gp.cChecker.checkEntity(this, gp.lupi);
            damageFromMonsters(LupiIndex);

            //coliziune boss
           int coliziune=gp.cChecker.checkBoss(this, gp.Boss);
            damageFromCaleb(coliziune);

            //reptile
            int ReptileIndex = gp.cChecker.checkEntity(this, gp.reptile);
            damageFromReptile(ReptileIndex);


            //DACA COLIZIUNEA E FLASE => PLAYERUL SE MISCA
            if (!collisionOn ) {
                switch (direction) {
                    case "up":
                        Worldy = Worldy - speed;
                        break;
                    case "down":
                        Worldy = Worldy + speed;
                        break;
                    case "left":
                        Worldx = Worldx - speed;
                        break;
                    case "right":
                        Worldx = Worldx + speed;
                        break;
                }
            }

            SpriteCounter++;
            if (SpriteCounter > 10) {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 0;
                }
                SpriteCounter = 0;
            }
        }

        if(invincible) {
            invincibleCount++;
            if (invincibleCount > 60) {
                invincible = false;
                invincibleCount = 0;
            }
        }

        if(Life<=0)
        {
            gp.gameState=gp.GameOverState;
            gp.stopMusic();
            gp.PlaySE(4);
            gp.ui.CommandNum=-1;
        }

    }

    public void attack()
    {
        SpriteCounter++;
        if(SpriteCounter>10)
        {
            if(spriteNum==0)
            {
                spriteNum=1;
            }
            else if(spriteNum==1)
            {
                spriteNum=2;

            }
            else if(spriteNum==2)
            {
                spriteNum=0;
                int currentWorldx=Worldx;
                int currentWorldy=Worldy;
                int solidAreaHeight=solidArea.height;
                int solidAreaWidth=solidArea.width;

                //ajustarea playerului worldx si worldy
                switch(direction)
                {
                    case "up":
                        Worldy-=attackAria.height; break;
                    case "down":
                        Worldy+=attackAria.height; break;
                    case "left":
                        Worldx-=attackAria.width; break;
                    case "right":
                        Worldx+=attackAria.width; break;
                }

                //ajuta la coliziunea cu monstrii deoarece partea de atack devine solida
                solidArea.width=attackAria.width;
                solidArea.height=attackAria.height;

                //coliziunea cu monstrii
                int LupiIndex=gp.cChecker.checkEntity(this,gp.lupi);
                damageLupi(LupiIndex);

                int ReptileIndex=gp.cChecker.checkEntity(this,gp.reptile);
                damageReptile(ReptileIndex);

                //damage boss
                int index=gp.cChecker.checkBoss(this, gp.Boss);
                damageCaleb(index);

                //dupa coliziune trebuie reajustate pozitiile playerului
                Worldx=currentWorldx;
                Worldy=currentWorldy;
                solidArea.width=solidAreaWidth;
                solidArea.height=solidAreaHeight;
            }
            SpriteCounter=0;
            attacking=false;
        }
    }

    //Interactiune cu celelalte entitati din joc
    public void Interact(int i)
    {
        if(keyH.vPressed)
        {
            attacking=true;
        }
        else
        {
            attacking=false;
        }

    }


    public void pickUpObject(int i)
    {
        if(i!=99)
        {
            String objName=gp.obj[gp.currentMap][i].name;

            switch(objName)
            {
                case "Comoara":
                    gp.PlaySE(1);
                    GoldNr++;
                    gp.obj[gp.currentMap][i]=null;
                    gp.ui.showMessage("Wooow!");
                    break;


                case "Key":
                    gp.PlaySE(1);
                    KeyNr++;
                    gp.obj[gp.currentMap][i]=null;
                    gp.ui.showMessage("You find the key! We are saved!");
                    break;


                case "Coin":
                    gp.PlaySE(1);
                    CoinNR++;
                    gp.obj[gp.currentMap][i]=null;
                    gp.ui.showMessage("Yeeeey!");
                    break;


                case "Diamant":
                    gp.stopMusic();
                    gp.PlaySE(3);
                    gp.obj[gp.currentMap][i] = null;
                    gp.gameState = gp.FinishState;
                    break;


                case "Door":
                    if(CoinNR==15)
                    {
                        gp.PlaySE(3);
                        gp.eventHandler.checkEvent();
                    }
                    else {
                        gp.ui.showMessage("Go find more coins!");
                    }
                    break;


                case "Door2":
                    int index=0;
                    if(GoldNr==0 && KeyNr==0 &&  gp.Boss[gp.currentMap][index]==null)
                    {
                        gp.obj[gp.currentMap][i]=null;
                    }
                    else {
                        gp.ui.showMessage("Go find more gold, the key and kill Caleb!");
                    }
                    break;


                case "GoldenBag":
                    if(CoinNR==10)
                    {
                        gp.PlaySE(3);
                        gp.eventHandler.checkEvent();
                    }
                    else {
                        gp.ui.showMessage("Go find more coins!");
                    }
                    break;
            }
        }
    }

    public void Draw(Graphics2D g2)
    {
        switch(direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 0)
                    {
                        g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                if (attacking ) {
                    if (spriteNum == 0) {
                        g2.drawImage(atacUp[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(atacUp[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(atacUp[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 0) {
                        g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                if (attacking) {
                    if (spriteNum == 0) {
                        g2.drawImage(atacDown[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(atacDown[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(atacDown[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 0) {
                        g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                else  {
                    if (spriteNum == 0) {
                        g2.drawImage(atacStg[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(atacStg[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(atacStg[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                break;
            case "right":
                if (!attacking ) {
                    if (spriteNum == 0) {
                        g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }

                    if (spriteNum == 1) {
                        g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                if (attacking) {
                    if (spriteNum == 0) {
                        g2.drawImage(atacDrt[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }

                    if (spriteNum == 1) {
                        g2.drawImage(atacDrt[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(atacDrt[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                }
                break;
            default:
                g2.drawImage(idle, ScreenX, ScreenY, gp.player_size, gp.player_size, null);
        }
    }


    public void damageLupi(int i) {
        if (i != 99) {
            if (!gp.lupi[gp.currentMap][i].invincible) {
                gp.lupi[gp.currentMap][i].Life -= 1;
                gp.lupi[gp.currentMap][i].invincible = true;
                gp.lupi[gp.currentMap][i].ReactionAtDamage();
                if (gp.lupi[gp.currentMap][i].Life <= 0) {
                    gp.lupi[gp.currentMap][i].dying = true;
                    gp.lupi[gp.currentMap][i] = null;
                    checkLevelUp();
                }
            } else {System.out.println("Miss!");}
        }

    }

    public void damageCaleb(int i)
    {
        if (i != 99) {
            if (!gp.Boss[gp.currentMap][i].invincible) {
                gp.Boss[gp.currentMap][i].Life -= 1;
                gp.Boss[gp.currentMap][i].invincible = true;
                gp.Boss[gp.currentMap][i].ReactionAtDamage();
                if (gp.Boss[gp.currentMap][i].Life <= 0) {
                    gp.Boss[gp.currentMap][i].dying = true;
                    gp.Boss[gp.currentMap][i] = null;
                    checkLevelUp();
                }
            } else {System.out.println("Miss!");}
        }
    }
    public void damageReptile(int i) {
        if (i != 99) {
            if (!gp.reptile[gp.currentMap][i].invincible) {
                gp.reptile[gp.currentMap][i].Life -= 1;
                gp.reptile[gp.currentMap][i].invincible = true;
                gp.reptile[gp.currentMap][i].ReactionAtDamage();
                if (gp.reptile[gp.currentMap][i].Life <= 0) {
                    gp.reptile[gp.currentMap][i].dying = true;
                    gp.reptile[gp.currentMap][i] = null;
                    checkLevelUp();
                }
            } else {System.out.println("Miss!");}
        }

    }
    public void damageFromCaleb(int index) {
        if (index!= 99)
        //playerul a ajuns in coliziune cu caleb
        {
            if (!invincible && !gp.Boss[gp.currentMap][index].dying) {
                Life -= 1;
                invincible = true;
            }
        }
    }

    public void damageFromReptile ( int index)
    {
        //plsyerul a ajuns in coliziune cu un monstru/Lup
        if (index != 99) {
            if (!invincible && !gp.reptile[gp.currentMap][index].dying) {
                Life -= 1;
                invincible = true;
            }
        }
    }
    public void damageFromMonsters ( int index)
    {
        //plsyerul a ajuns in coliziune cu un monstru/Lup
        if (index != 99) {
            if (!invincible && !gp.lupi[gp.currentMap][index].dying) {
                Life -= 1;
                invincible = true;
            }
        }
    }
    public void checkLevelUp ()
    {
        if (CoinNR == 10) {
            level++;
            Life+=2;
        }
    }

    public void ResetCoin()
    {
        CoinNR=0;
    }
}