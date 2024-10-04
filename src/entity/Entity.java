package entity;

import main.GamePanel;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

//parent class for every class
//clasa abstracta care nu va putea fi instatiata de sine statatoare
public class Entity {

    GamePanel gp;
    //pentru toata harta
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackAria = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;

    //ANIMATII
    public BufferedImage[] up;
    public BufferedImage[] left;
    public BufferedImage[] right;
    public BufferedImage[] down;
    public BufferedImage idle;
    public BufferedImage[] atacDrt;
    public BufferedImage[] atacStg;
    public BufferedImage[] atacUp;
    public BufferedImage[] atacDown;
    public String name;


    //STATE
    public int Worldx, Worldy;
    public String direction = "down";
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = false;
    public boolean dying = false;
    public boolean onPath = false;

    //COUNTER
    public int SpriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCount = 0;
    public int dyingCounter = 0;

    //CHARACTER atr
    public int maxLife;
    public int Life;
    public int type;     //0-player,1-npc,2-monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_lup = 2;
    public int attack;
    public int speed;
    public int level;
    public int nextLevelExp;
    public int coin;
//    public int MaxMana;
//    public int Mana;

    //instantiat in player
    public int useCost;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void Draw(Graphics2D g2) {
        int ScreenX = Worldx - gp.player.Worldx + gp.player.ScreenX;
        int ScreenY = Worldy - gp.player.Worldy + gp.player.ScreenY;

        if (Worldx + gp.TILE_SIZE > gp.player.Worldx - gp.player.ScreenX
                && Worldx - gp.TILE_SIZE < gp.player.Worldx + gp.player.ScreenX
                && Worldy + gp.TILE_SIZE > gp.player.Worldy - gp.player.ScreenY
                && Worldy - gp.TILE_SIZE < gp.player.Worldy + gp.player.ScreenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 0) {

                        g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);

                    }
                    if (spriteNum == 1) {
                        g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(up[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    break;
                case "down":
                    if (spriteNum == 0) {
                        g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(down[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    break;
                case "left":
                    if (spriteNum == 0) {
                        g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(left[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    break;
                case "right":
                    if (spriteNum == 0) {
                        g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 1) {
                        g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    if (spriteNum == 2) {
                        g2.drawImage(right[spriteNum], ScreenX, ScreenY, gp.player_size, gp.player_size, null);
                    }
                    break;
                default:
                    g2.drawImage(idle, ScreenX, ScreenY, gp.player_size, gp.player_size, null);

            }
        }
    }

    public void SetAction() {

    }

    public void ReactionAtDamage() {

    }

    public void checkCollision() {

        collisionOn = false;

        //coliziunea cu tile urile
        gp.cChecker.checkTile(this);

        //coliziunea cu obiectele
        gp.cChecker.checkObject(this, false);

        //coliziune intre monstri si npc uri
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.lupi);
        gp.cChecker.checkEntity(this, gp.reptile);
        gp.cChecker.checkBoss(this, gp.Boss);


        boolean contactPlayer = gp.cChecker.CheckPlayer(this);

        //daca este lup si atinge playerul
        if (this.type == 2 && contactPlayer == true) {

            damagePlayer();
        }

        //daca e reptila si atinge playerul
        if (this.type == 3 && contactPlayer == true) {

            damagePlayer();
        }


        //daca e boss si atinge playerul
        if (this.type == 4 && contactPlayer == true) {

            damagePlayer();
        }

    }

    public void update() {
        SetAction();
        checkCollision();
        boolean contactPlayer = gp.cChecker.CheckPlayer(this);

        if (this.type == 1 && contactPlayer == true) {
//
            damagePlayer();

        }
        //DACA COLIZIUNEA E Falsa => PLAYERUL SE MISCA
        if (collisionOn == false) {
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
        if (invincible == true) {
            invincibleCount++;
            if (invincibleCount > 40) {
                invincible = false;
                invincibleCount = 0;
            }
        }
    }



    //atunci cand playerul nu detine proprietatea de invincibilitate de 2 sec dupa un atac
    //atunci el poate fi atacat din nou si pierde vieti
    public void damagePlayer() {
        if (gp.player.invincible == false) {
            gp.player.Life = gp.player.Life - 1;
            gp.player.invincible = true;
            //gp.saveLoad.save();
        }
    }


    //SETTER and GETTER

    public void setX(int x) {
        this.Worldx = x;
    }

    public void setY(int y) {
        this.Worldy = y;
    }

    public void setVelX(int velX) {
        this.speed = velX;
    }

    public void setVelY(int velY) {
        this.speed = velY;
    }

    public int getX() {
        return this.Worldx;
    }

    public int getY() {
        return this.Worldy;
    }



//determină direcția în care o entitate trebuie să se deplaseze pentru a ajunge la un anumit punct
// țintă pe harta jocului. Aceasta folosește algoritmul A*
    public void searchPath(int goalCol, int goalRow){
        //this is where the entity is
        int startCol=(Worldx + solidArea.x)/gp.TILE_SIZE;
        int startRow=(Worldy + solidArea.y)/gp.TILE_SIZE;

        gp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pathFinder.search()==true)
        {
            //it means we fouund a path
            //next worldX and worldY
            int nextX=gp.pathFinder.pathList.get(0).col*gp.TILE_SIZE;
            int nextY=gp.pathFinder.pathList.get(0).row*gp.TILE_SIZE;

            //Entity's solidArea position
            int enLeftX=Worldx + solidArea.x;
            int enRightX= Worldx+ solidArea.x + solidArea.width;
            int enTopY=Worldy + solidArea.y;
            int enBottomY=Worldy + solidArea.y+solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.TILE_SIZE){
                direction="up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.TILE_SIZE){
                direction="down";
            }
            else if(enTopY>= nextY && enBottomY < nextY+gp.TILE_SIZE){
                //left or right
                if(enLeftX > nextX){
                    direction="left";
                }
                if(enLeftX < nextX){
                    direction="right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //up or left
                direction="up";
                checkCollision();
                if(collisionOn == true){
                    direction="left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //up or right
                direction="up";
                checkCollision();
                if(collisionOn == true){
                    direction="right";
                }
            }

            else if(enTopY < nextY && enRightX > nextX){
                //down or left
                direction="down";
                checkCollision();
                if(collisionOn == true){
                    direction="left";
                }
            }
            else if(enTopY < nextY && enRightX < nextX){
                //down or right
                direction="down";
                checkCollision();
                if(collisionOn == true){
                    direction="right";
                }
            }
        }
    }
}

