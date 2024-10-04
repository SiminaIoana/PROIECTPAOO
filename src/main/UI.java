package main;

import entity.Entity;
import object.Coin;
import object.Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static main.DataBase.insertB;

//Ne ajuta la afisarea textelor pe ecran sau aale altor pictograme
public class UI {

    GamePanel gp;
    Graphics2D g2;
    public BufferedImage background;
    Font cambri_40;
    Font cambri_25;
    Font arial_80;
    BufferedImage CoinImage;
    BufferedImage Heart_full, Heart_half, Heart_blank;

    public boolean messageOn = false;
    public String message = "";
    int messageCount = 0;


    static double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.0");

    public int CommandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        cambri_40 = new Font("Cambri", Font.PLAIN, 40);
        cambri_25 = new Font("Cambri", Font.PLAIN, 25);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        background = LoadImage.LoadImage("/background/fundal.png");

        Coin coin = new Coin(gp);
        CoinImage = coin.image;
        SuperObject heart = new Heart(gp);
        Heart_full = heart.image;
        Heart_half = heart.image2;
        Heart_blank = heart.image3;
    }


    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    //introdus in gameLoop
    //daca instantiem in game panel o sa ia mai mult timp
    public void Draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(cambri_40);
        g2.setColor(Color.WHITE);

//        if (gp.gameState == gp.playState) {
//            DrawPlayerLife();
//        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            DrawPlayerLife();

        }
        if (gp.gameState == gp.GameOverState) {
            gameOverScreen();
        }
        if(gp.gameState==gp.NextLevelState)
        {
            NextLevelScreen();
        }

        //title state
        if (gp.gameState == gp.titleState) {
            DrawTitleScreen();
        }

        if (gp.gameState == gp.playState)
        {
                if(gp.currentMap==1) {
                    gp.eManager.Draw(g2);
                    PlayingScreen();
                }
                else
                    PlayingScreen();
        }

        if(gp.gameState==gp.FinishState)
        {
            GameFinish();
        }
    }

    public void GameFinish ()
    {
        g2.drawImage(background,0,0,gp.SCREEN_WIDTH,gp.SCREEN_HEIGHT,null);

        String text = "Congratulation!";
        int x, y;
        g2.setFont(g2.getFont().deriveFont(Font.CENTER_BASELINE, 50));
        x = CenteredText(text);
        y = gp.TILE_SIZE;
        g2.setColor(new Color(252,120,244));
        g2.drawString(text, x, y);

        text = "Your time is " + dFormat.format(playTime) + "!";
        x = CenteredText(text);
        y = gp.TILE_SIZE*11;
        g2.drawString(text, x, y);



        //reincercare
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = gp.TILE_SIZE*2;
        y = gp.TILE_SIZE * 7;
        g2.drawString(text, x, y);
        if (CommandNum == 0)
            g2.drawString(">", x - 40, y);

        //back to title screen
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Quit";
        x = gp.TILE_SIZE*2;
        y += gp.TILE_SIZE;
        g2.drawString(text, x, y);
        if (CommandNum == 1)
            g2.drawString(">", x - 40, y);

    }

    public void PlayingScreen ()
    {
        DrawTime();
        DrawPlayerLife();
        g2.setFont(cambri_40);
        g2.setColor(Color.WHITE);
        g2.drawImage((Image) CoinImage, gp.TILE_SIZE / 2, (int) ((double)gp.TILE_SIZE *1.5), gp.TILE_SIZE, gp.TILE_SIZE, null);

        g2.drawString("x " + gp.player.CoinNR, gp.TILE_SIZE * 2,  (int) ((double)gp.TILE_SIZE *2.3));
        //message
        if (messageOn == true)
        {
            g2.setFont(cambri_40);
            g2.drawString(message, gp.TILE_SIZE / 2, gp.TILE_SIZE * 5);
            messageCount++;

            //dispare dupa 1 secunda
            if (messageCount > 60) {
                messageCount = 0;
                messageOn = false;
            }

        }
    }


    public void DrawPlayerLife ()
    {
        //gp.player.Life=5;
        //UNDE Vom afisa inimile
        int x = gp.TILE_SIZE / 2;
        int y = gp.TILE_SIZE / 2;
        int i = 0;

        //draw max heart
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(Heart_blank, x, y, null);
            i++;
            x = x + gp.TILE_SIZE;
        }

        //reset
        x = gp.TILE_SIZE / 2;
        y = gp.TILE_SIZE / 2;
        i = 0;
        //draw current life
        //daca o este mai mic decat o viata completa atunci prima data o sa desenam jumatate de inima

        while (i < gp.player.Life) {
            g2.drawImage(Heart_half, x, y, null);
            i++;
            if (i < gp.player.Life) {
                g2.drawImage(Heart_full, x, y, null);
            }
            i++;
            x += gp.TILE_SIZE;
        }
    }

    public void DrawTime()
    {
        g2.setFont(cambri_25);
        playTime += (double) 1 / 60;
        g2.drawString("Time:" + dFormat.format(playTime), gp.TILE_SIZE * 13, 40);

    }
    public void DrawTitleScreen ()
    {
        g2.drawImage(background,0,0,gp.SCREEN_WIDTH,gp.SCREEN_HEIGHT,null);
        String text = "Saving the Fairyland";
        int x;
        int y;
        x = CenteredText(text);
        //x=gp.TILE_SIZE;
        y = gp.TILE_SIZE+16;

        //shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 3, y + 3);

        g2.setColor(new Color(252,120,244));
        g2.drawString(text, x, y);

        x = gp.SCREEN_WIDTH / 2 - gp.player_size;
        y += gp.TILE_SIZE * 2;


        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x =gp.TILE_SIZE*2;
        y = gp.TILE_SIZE*7;
        g2.drawString(text, x, y);
        if (CommandNum == 0) {
            g2.drawString(">", x - gp.TILE_SIZE, y);
        }


        text = "QUIT";
        x =gp.TILE_SIZE*2;
        y += gp.TILE_SIZE;
        g2.drawString(text, x, y);
        if (CommandNum == 1) {
            g2.drawString(">", x -  gp.TILE_SIZE, y);
        }
    }


    public void NextLevelScreen ()
    {
        g2.drawImage(background,0,0,gp.SCREEN_WIDTH,gp.SCREEN_HEIGHT,null);
        g2.setColor(new Color(252,120,244));
        //g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
        String text = "Level Completed!";
        int x, y;
        g2.setFont(g2.getFont().deriveFont(Font.CENTER_BASELINE, 50));
        x = CenteredText(text);
        //x=gp.TILE_SIZE;
        y = gp.TILE_SIZE+16;
        g2.setColor(new Color(252,120,244));
        g2.drawString(text, x, y);

        //reincercare
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Next Level";
        x = gp.TILE_SIZE*2;
        y = gp.TILE_SIZE * 7;
        g2.drawString(text, x, y);
        if (CommandNum == 0)
            g2.drawString(">", x - 40, y);
        //back to title screen
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Quit";
        x = gp.TILE_SIZE*2;
        y += gp.TILE_SIZE ;
        g2.drawString(text, x, y);
        if (CommandNum == 1)
            g2.drawString(">", x - 40, y);
    }

    public void gameOverScreen ()
    {
        g2.drawImage(background,0,0,gp.SCREEN_WIDTH,gp.SCREEN_HEIGHT,null);

       // g2.setColor(new Color(0, 0, 0, 100));
        //g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
        String text = "GAME OVER";
        int x, y;
        g2.setFont(g2.getFont().deriveFont(Font.CENTER_BASELINE, 50));
        x = CenteredText(text);
        y = gp.TILE_SIZE;
        g2.setColor(new Color(252,120,244));
        g2.drawString(text, x, y);

        //reincercare
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = gp.TILE_SIZE*2;
        y = gp.TILE_SIZE * 7;
        g2.drawString(text, x, y);
        if (CommandNum == 0)
            g2.drawString(">", x - 40, y);

        //back to title screen
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Quit";
        x = gp.TILE_SIZE*2;
        y += gp.TILE_SIZE;
        g2.drawString(text, x, y);
        if (CommandNum == 1)
            g2.drawString(">", x - 40, y);

    }

    public void drawPauseScreen () {
        g2.drawImage(background,0,0,gp.SCREEN_WIDTH,gp.SCREEN_HEIGHT,null);

        //g2.setColor(new Color(0, 140, 128));
        //g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
        g2.setFont(g2.getFont().deriveFont(Font.CENTER_BASELINE, 50));
        String text = "PAUSE";
        int x;
        int y;
        x = CenteredText(text);
        y = gp.TILE_SIZE;

        //shadow
        g2.setColor(new Color(252,120,244));;
        //g2.drawString(text, x + 3, y + 3);

        g2.drawString(text, x, y);
        //playTime -= (double) 1 / 60;

        //reincercare
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Resume";
        x = gp.TILE_SIZE*2;
        y = gp.TILE_SIZE * 7;
        g2.drawString(text, x, y);
        if (CommandNum == 0)
            g2.drawString(">", x - 40, y);

        //back to title screen
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Quit";
        x = gp.TILE_SIZE*2;
        y += gp.TILE_SIZE ;
        g2.drawString(text, x, y);
        if (CommandNum == 1)
            g2.drawString(">", x - 40, y);
    }

        //centram textul pe mijloc
        public int CenteredText (String text)
        {
             int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.SCREEN_WIDTH / 2 - length / 2;
            return x;
    }
}




