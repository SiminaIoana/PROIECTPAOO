package main;

import AI.FindingPath;
import entity.Caleb;
import entity.Entity;
import entity.Lupi;
import entity.Player;
import environment.EnvironmentManager;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.* ;

//game screen
public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int original_tile_Size = 48;

    public final int MAXSCREENCOL = 24;
    public final int MAXSCREENLINE = 18;

    public final int TILE_SIZE = original_tile_Size;

    public final int player_size = 64;
    public final int boss_size = 96;

    public final int SCREEN_WIDTH = 768;        /*768 */
    public final int SCREEN_HEIGHT = 576;      /*576*/

    //WORLD SETTINGS
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 51;
    public final int maxMap = 3;
    public int currentMap = 0;
    public final int WORLD_WIDTH = TILE_SIZE * maxWorldCol;
    public final int WORLD_HEIGHT = TILE_SIZE * maxWorldRow;


    public TileManager tileManager = new TileManager(this);
    public FindingPath pathFinder = new FindingPath(this);

    //aria luminoasa
    EnvironmentManager eManager = new EnvironmentManager(this);
    //Ajuta la coliziunile cu celelalte elemente, tile, etc
    public CollisionChecker cChecker = new CollisionChecker(this);

    //miscarea oe harta a playerului,atac,meniu
    KeyHandler keyH = new KeyHandler(this);

    //Coloana sonora
    Sound music = new Sound();
    Sound SoundEffect = new Sound();
    public AssetSetter setter = new AssetSetter(this);


    //meniul, interfata jocului, mesajele de ecran
    public UI ui = new UI(this);

    //evenimentele care au loc pe parcurs
    public Event_Handler eventHandler = new Event_Handler(this);


    //firul de executie
    Thread gameThread;


    //ENTITY AND OBJECTS
    //PLAYER
    public Player player = new Player(this, keyH);

    //CALEB
    public Entity[][] Boss = new Caleb[maxMap][1];

    //coin
    public SuperObject[][] obj = new SuperObject[maxMap][50];

    public Entity[][] entity = new Entity[maxMap][50];
    public Entity[][] npc = new Entity[maxMap][10];

    //Reptile
    public Entity[][] reptile = new Entity[maxMap][20];

    public ArrayList<Entity> entityList = new ArrayList<>();


    //LUPI DE a doua  HARTA
    public Lupi[][] lupi = new Lupi[maxMap][20];

    //GAME STATES(pauza, play, stop, titleState)
    public int gameState;

    public final int titleState = 0;

    //jocul incepe cu playState
    public final int playState = 1;

    //Pauza(cand apas P)
    public final int pauseState = 2;
    public final int optionState = 3;
    public final int GameOverState = 4;
    public final int NextLevelState = 5;
    public final int FinishState = 6;

    public GamePanel()
    {

        //Dimensiunile ecranului
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //Culoarea de background
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //Actionarea tastelor
        this.addKeyListener(keyH);
        //vizibilitatea pe ecran
        this.setFocusable(true);

    }


    //CEEA CE VOM INTRODUCE IN JOC
    public void setupGame() {
        setter.setObject();
        setter.setNPC();
        setter.SetBoss();
        setter.setLupi();
        setter.setReptile();
        eManager.setup();

        //Initial game stateul este meniul de intrare
        gameState = titleState;
    }

    public void Retry() {
        currentMap = 0;
        player.setDefaultPositions();
        player.RestoreLife();
        player.ResetCoin();
        setter.setNPC();
        setter.SetBoss();
        UI.playTime = 0;
        setter.setLupi();
        setter.setObject();
    }

    public void Restart() {
        currentMap = 0;
        UI.playTime = 0;
        player.setDefaultPositions();
        player.setDefaultValues();
        player.RestoreLife();
        setter.setObject();
        player.ResetCoin();
        setter.setNPC();
        setter.SetBoss();
        setter.setLupi();
        setter.setReptile();
    }

    public void LevelUp()
    {
        currentMap++;
        eventHandler.teleport(currentMap, 27, 25);
        setter.setNPC();
        setter.setLupi();
        setter.setObject();
        setter.SetBoss();

    }

    public void Start() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        long oldTime = System.nanoTime();     //timpul aferent frame-ului anterior
        long curentTime;                    //timpul aferent frame-ului curent


        //nr de frame-uri pe secunda
        final int framesPerSecond = 60;

        /*!< Durata unui frame in nanosecunde.*/
        final double timeFrame = 1000000000 / framesPerSecond;

        //cat timp thread-ul este pornit facem Update and Draw

        while (gameThread != null) {
            //timpul curent
            curentTime = System.nanoTime();

            //daca diferenra de timp intre timpul curent si cel anterior este mai mare de 16.6 milisec
            if ((curentTime - oldTime) > timeFrame) {
                //actualizam pozitiile elementelor
                Update();
                repaint();
                oldTime = curentTime;

            }
        }
    }


    public void Update() {
        if (gameState == playState) {
            //player
            player.Update();

            //npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null)
                    npc[currentMap][i].update();
            }

            //lupi
            for (int i = 0; i < lupi[1].length; i++) {
                if (lupi[currentMap][i] != null) {
                    lupi[currentMap][i].update();
                }
            }

            //caleb
            for (int i = 0; i < Boss[1].length; i++) {
                if (Boss[currentMap][i] != null) {
                    Boss[currentMap][i].update();
                }
            }
            //reptile
            for (int i = 0; i < reptile[1].length; i++) {
                if (reptile[currentMap][i] != null)
                    reptile[currentMap][i].update();
            }

        }
        if (gameState == pauseState) {
            //nu facem update
        }

    }

//Necesara pentru desenarea tuturor componentelor si animatiilor de pe ecran
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            //desenarea meniului de intrare
            ui.Draw(g2);
        } else {

            //Desenarea hartii
            //TILE
            tileManager.Draw(g2);

            //Object
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null)
                    obj[currentMap][i].Draw(g2, this);
            }

            //NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].Draw(g2);
                }
            }

            //caleb
            for (int i = 0; i < Boss[1].length; i++) {
                if (Boss[currentMap][i] != null) {
                    Boss[currentMap][i].Draw(g2);
                }
            }

            //Lupi
            for (int i = 0; i < lupi[1].length; i++) {
                if (lupi[currentMap][i] != null) {
                    lupi[currentMap][i].Draw(g2);
                }

            }

            //reptile
            for (int i = 0; i < reptile[1].length; i++) {
                if (reptile[currentMap][i] != null) {
                    reptile[currentMap][i].Draw(g2);
                }
            }


            for (int i = 0; i < entityList.size(); i++) {
                if (entityList.get(i) != null)
                    entityList.get(i).Draw(g2);
            }

            //stergerea tuturor elementelor din lista
            entityList.clear();
            //Player
            player.Draw(g2);
            //UI
            ui.Draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        music.SetFile(i);
        music.Play();
        music.Loop();
    }

    public void stopMusic() {
        music.Stop();
    }

    public void PlaySE(int i) {
        SoundEffect.SetFile(i);
        SoundEffect.Play();
    }
}