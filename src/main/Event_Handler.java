package main;

import java.awt.*;

import static main.DataBase.insertB;

public class Event_Handler {

    GamePanel gp;

    EventRect eventRect[][][];

    //daca un eveniment deja a avut loc, el  nu o sa mai aiba loc
    //decat daca playerul o sa se mute pe harta si o sa se reintoarca
    //astfel evenimentul nu o sa se repete in continuu
    int previousEventX , previousEventY;

    //ne ajuta atunci cand nu vrem ca un eveniment sa se petreaca de mai multe ori
    boolean touchEvent=true;

    public Event_Handler(GamePanel gp)
    {
        //vrem ca evenimentul sa aiba loc atunci cand se atinge efectiv tile ul resp
        this.gp=gp;

        eventRect=new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
      int map=0;
       int col=0;
       int row=0;
       while(map<gp.maxMap && col<gp.maxWorldCol && row< gp.maxWorldRow) {

           eventRect[map][col][row] = new EventRect();
           eventRect[map][col][row].x = 23;
           eventRect[map][col][row].y = 23;
           eventRect[map][col][row].width = 2;
           eventRect[map][col][row].height = 2;
           eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row] .x;
           eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row] .y;

           col++;
           if(col==gp.maxWorldCol)
           {
               col=0;
               row++;
               if(row==gp.maxWorldRow)
               {
                   row=0;
                   map++;
               }
           }
       }

    }

    public void checkEvent()
    {
        //ne asiguram daca playerul se afla la mai mult de un tile distanta de ultimul eveniment
        int xDistance=Math.abs(gp.player.Worldx-previousEventX);
        int yDistance=Math.abs(gp.player.Worldy-previousEventY);
        int distance=Math.max(xDistance,yDistance);

        //daca playerul se afla la mai mult de un tile distanta de un eveniment at ev nu are loc
        if(distance>gp.TILE_SIZE)
        {
            touchEvent=true;
        }

        if(touchEvent==true)
        {
             if(Hit(1,49,42,"any")==true)
             {
                 gp.gameState=gp.NextLevelState;
                 gp.player.ResetCoin();

             }

             if(Hit(0,32,35,"any")==true)
             {
                 gp.gameState=gp.NextLevelState;
                 gp.player.ResetCoin();
             }
        }
    }

    public void teleport(int map, int col, int row)
    {
            gp.currentMap=map;
            gp.player.Worldx=gp.TILE_SIZE*col;
            gp.player.Worldy=gp.TILE_SIZE*row;
            previousEventX=gp.player.Worldx;
            previousEventY=gp.player.Worldy;
            touchEvent=false;
            //gp.PlaySE(0);
    }
    public boolean Hit(int map,int col, int row, String direction)
    {

        boolean hit=false;

        //daca harta nu e cea curenta nu se poate realiza "hit"
        if(map==gp.currentMap)
        {
            gp.player.solidArea.x = gp.player.Worldx + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.Worldy + gp.player.solidArea.y;

            //event rectangle position
            eventRect[map][col][row].x = col * gp.TILE_SIZE + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.TILE_SIZE + eventRect[map][col][row].y;

            //damage ul va avea loc doar o data
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.Worldx;
                    previousEventY = gp.player.Worldy;

                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }

    public void Damage(int gameState)
    {
        gp.gameState=gameState;
        gp.player.Life-=1;
      //  eventRect[col][row].eventDone=true;
        touchEvent=false;
    }


}

