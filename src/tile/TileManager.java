package tile;

import main.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;

    //tile urile pe care le vom folosi
    public Tile[] tile;

    //HARTA PRIMULUI NIVEL
    public int mapTileNum[][][];
    boolean drawPath=true;
    //public int[][] level1;

    //HARTA CELUI DE-AL DOILEA NIVEL
    public int [][] level2;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        //un nr de tipuri de tile uri
        tile = new Tile[20];

        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        LoadMap("/maps/LEVEL_1.txt",0);
        LoadMap("/maps/LEVEL_2.txt",1);
        LoadMap("/maps/LEVEL_3.txt",2);

    }

    //o sa incarcam imaginile corespunzatoare tile urilor
    public void getTileImage() {

        SpriteSheet sheet1 = new SpriteSheet(LoadImage.LoadImage("/Tiles/forest_tiles.png"));
        SpriteSheet sheet2 = new SpriteSheet(LoadImage.LoadImage("/Tiles/ziduri.png"));
        SpriteSheet sheet3 = new SpriteSheet(LoadImage.LoadImage("/Tiles/pamant.png"));
        SpriteSheet sheet4 = new SpriteSheet(LoadImage.LoadImage("/objects/Coin64.png"));
        SpriteSheet sheet5 = new SpriteSheet(LoadImage.LoadImage("/Tiles/Apa1.png"));
        SpriteSheet sheet6 = new SpriteSheet(LoadImage.LoadImage("/Tiles/Apa2.png"));
        SpriteSheet sheet7=new SpriteSheet(LoadImage.LoadImage("/Tiles/perete.png"));
        SpriteSheet sheet8=new SpriteSheet(LoadImage.LoadImage("/Tiles/pereteSus.png"));
        SpriteSheet sheet9=new SpriteSheet(LoadImage.LoadImage("/Tiles/pereteJos.png"));
        SpriteSheet sheet10=new SpriteSheet(LoadImage.LoadImage("/Tiles/pereteStg.png"));
        SpriteSheet sheet11=new SpriteSheet(LoadImage.LoadImage("/Tiles/pereteDrt.png"));
        SpriteSheet sheet12=new SpriteSheet(LoadImage.LoadImage("/Tiles/floor.png"));
        SpriteSheet sheet13=new SpriteSheet(LoadImage.LoadImage("/Tiles/podea.png"));


        //iarba
        tile[0] = new Tile();
        tile[0].image = sheet1.crop(0, 0);


        //apa de pe spritesheetul cu padure
        tile[3] = new Tile();
        tile[3].image = sheet1.crop(10, 0);
        tile[3].collision = true;

        //zid
        tile[2] = new Tile();
        tile[2].image = sheet2.crop(0, 0);
        tile[2].collision = true;

        //pamant
        tile[1] = new Tile();
        tile[1].image = sheet3.crop(0, 0);
        tile[1].collision = true;

        //flori
        tile[4] = new Tile();
        tile[4].image = sheet1.crop(0, 1);

        //musuroi
        tile[5] = new Tile();
        tile[5].image = sheet1.crop(12, 3);

        //coin
        tile[6] = new Tile();
        tile[6].image = sheet4.crop(0, 0);
        tile[6].collision = true;

        //apa
        tile[7] = new Tile();
        tile[7].image = sheet5.crop(0, 0);
        tile[7].collision = true;

        //apa pe care se poate calca
        tile[8] = new Tile();
        tile[8].image = sheet6.crop(0, 0);

        tile[9]=new Tile();
        tile[9].image=sheet1.crop(1,1);

        //perete 1
        tile[10]=new Tile();
        tile[10].image=sheet7.crop(0,0);
        //perete sus
        tile[11]=new Tile();
        tile[11].image=sheet8.crop(0,0);
        tile[11].collision=true;

        tile[12]=new Tile();
        tile[12].image=sheet9.crop(0,0);
        tile[12].collision=true;

        tile[13]=new Tile();
        tile[13].image=sheet10.crop(0,0);
        tile[13].collision=true;

        tile[14]=new Tile();
        tile[14].image=sheet11.crop(0,0);
        tile[14].collision=true;

        tile[15]=new Tile();
        tile[15].image=sheet12.crop(0,0);

        tile[16]=new Tile();
        tile[16].image=sheet13.crop(0,0);


    }


    public void LoadMap(String path, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                //citim cate o linie din fisierul text
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    //se ignora spatiile
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;

                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;

                }
            }

            br.close();
        } catch (Exception e) {

        }
    }


    public void Draw(Graphics2D g2) {
        //linie si coloana
        int WorldCol = 0;
        int WorldRow = 0;

        //se va implementa o camera

        while (WorldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][WorldCol][WorldRow];

            int worldX = WorldCol * gp.TILE_SIZE;
            int worldY = WorldRow * gp.TILE_SIZE;

            int ScreenX = worldX - gp.player.Worldx + gp.player.ScreenX;
            int ScreenY = worldY - gp.player.Worldy + gp.player.ScreenY;

            if(worldX + gp.TILE_SIZE > gp.player.Worldx - gp.player.ScreenX &&
                    worldX - gp.TILE_SIZE < gp.player.Worldx + gp.player.ScreenX &&
                    worldY + gp.TILE_SIZE > gp.player.Worldy - gp.player.ScreenY &&
                    worldY - gp.TILE_SIZE < gp.player.Worldy + gp.player.ScreenY) {
                g2.drawImage(tile[tileNum].image, ScreenX, ScreenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
            WorldCol++; //so we can draw the next tile

            if(WorldCol== gp.maxWorldCol) {
                WorldCol=0;
                WorldRow++;

            }
        }
        if(drawPath==true)
        {
            g2.setColor(new Color(255,0,0,70));
            for(int i=0;i<gp.pathFinder.pathList.size();i++)
            {
                int worldX=gp.pathFinder.pathList.get(i).col*gp.TILE_SIZE;
                int worldY=gp.pathFinder.pathList.get(i).col*gp.TILE_SIZE;

                int screenX=worldX-gp.player.Worldx+gp.player.ScreenX;
                int screenY=worldY-gp.player.Worldy+gp.player.ScreenY;

              // g2.fillRect(screenX,screenY,gp.TILE_SIZE,gp.TILE_SIZE);
            }
        }
    }
}
//    public void Draw(Graphics2D g2)
//    {
//        //Desenam  harta
//        int col=0;
//        int row=0;
//
//        //pozitia camerei in functie de pozitia jucatorului
//        int cameraX=gp.player.Worldx-(gp.SCREEN_WIDTH/2);
//        int cameraY=gp.player.Worldy-(gp.SCREEN_HEIGHT/2);
//
//        //restrictionarea pozitiei camerei in functie de pozitia jucatorului
//
//
//        while(col!=gp.maxWorldCol && row!= gp.maxWorldRow)
//        {
//            int tileNum=mapTileNum[col][row];
//            if(cameraX<0)
//                cameraX=0;
//            if(cameraY<0)
//                cameraY=0;
//            if(cameraX> gp.WORLD_WIDTH-gp.SCREEN_WIDTH)
//                cameraX=gp.WORLD_WIDTH-gp.SCREEN_WIDTH;
//            if(cameraY>gp.WORLD_HEIGHT- gp.SCREEN_HEIGHT)
//                cameraY= gp.WORLD_HEIGHT-gp.SCREEN_HEIGHT;
//
//            int worldX=col*gp.TILE_SIZE;
//            int worldY=row*gp.TILE_SIZE;
//
//            int ScreenX=worldX-cameraX;
//            int ScreenY=worldY-cameraY;
//
//            if(worldX+gp.TILE_SIZE>cameraX && worldX<cameraX+gp.SCREEN_WIDTH &&
//                worldY+gp.TILE_SIZE>cameraY && worldY<cameraY+ gp.SCREEN_HEIGHT)
//                     g2.drawImage(tile[tileNum].image,ScreenX,ScreenY, gp.TILE_SIZE,gp.TILE_SIZE,null);
//                else
  //                      {
//
 //                       }
//
//            col++;
//            if(col==gp.maxWorldCol)
//            {
//                col=0;
//                row++;
//            }
//        }
//
//    }
//}
