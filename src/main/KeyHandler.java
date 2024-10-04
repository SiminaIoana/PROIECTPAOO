package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.DataBase.insertB;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public KeyHandler(GamePanel gp)
   {
       this.gp=gp;
   }

    public boolean up,down,left,right,vPressed,shotPressed;
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //title state
        if (gp.gameState == gp.titleState) {
            if (key == KeyEvent.VK_UP) {
                gp.ui.CommandNum--;
                if (gp.ui.CommandNum < 0)
                    gp.ui.CommandNum = 2;
            }

            if (key == KeyEvent.VK_DOWN) {
                gp.ui.CommandNum++;
                if (gp.ui.CommandNum > 1)
                    gp.ui.CommandNum = 0;
            }

            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.CommandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(2);
                }
                if (gp.ui.CommandNum == 1) {
                    System.exit(0);
                }
            }

        }
        if (gp.gameState == gp.GameOverState) {
            if (key == KeyEvent.VK_UP) {
                gp.ui.CommandNum--;
                if (gp.ui.CommandNum < 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_DOWN) {
                gp.ui.CommandNum++;
                if (gp.ui.CommandNum > 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.CommandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.Retry();
                    gp.playMusic(2);
                }
                if (gp.ui.CommandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.Restart();
                }
            }
        }


        if (gp.gameState==gp.FinishState) {
            if (key == KeyEvent.VK_UP) {
                gp.ui.CommandNum--;
                if (gp.ui.CommandNum < 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_DOWN) {
                gp.ui.CommandNum++;
                if (gp.ui.CommandNum > 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.CommandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.Retry();
                    gp.playMusic(2);
                }
                if (gp.ui.CommandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.Restart();
                }
            }
        }




        if (gp.gameState == gp.NextLevelState)
        {
            if (key == KeyEvent.VK_UP) {
                gp.ui.CommandNum--;
                if (gp.ui.CommandNum < 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_DOWN) {
                gp.ui.CommandNum++;
                if (gp.ui.CommandNum > 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.CommandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.LevelUp();
                }
                if (gp.ui.CommandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.Restart();
                }
            }
        }

        if (gp.gameState == gp.pauseState)
        {
            if (key == KeyEvent.VK_UP) {
                gp.ui.CommandNum--;
                if (gp.ui.CommandNum < 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_DOWN) {
                gp.ui.CommandNum++;
                if (gp.ui.CommandNum > 0)
                    gp.ui.CommandNum = 1;
            }
            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.CommandNum == 0) {
                    gp.gameState = gp.playState;
                    //gp.Resume();
                    //gp.playMusic(2);
                }
                if (gp.ui.CommandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.Restart();
                }
            }
        }


        //PLAY STATE
        if (gp.gameState == gp.playState) {
            if (key == KeyEvent.VK_UP) {
                up = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                down = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (key == KeyEvent.VK_V) {
                vPressed = true;
            }
            if (key == KeyEvent.VK_SPACE) {
                shotPressed = true;
            }
            if (key == KeyEvent.VK_O) {
                gp.gameState = gp.optionState;
            }
            if (key == KeyEvent.VK_P)
            {
                if (gp.gameState == gp.playState)
                {
                    insertB("data","Data_Table",UI.playTime, gp.player.Life, gp.player.Worldx,gp.player.Worldy,gp.currentMap);
                    gp.gameState = gp.pauseState;

                } else if (gp.gameState == gp.pauseState)
                    gp.gameState = gp.playState;
            }
        }
    }


    public void pauseState()
    {

    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            up = false;
        }
        if (key == KeyEvent.VK_DOWN)
        {
            down=false;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            left=false;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            right=false;
        }
        if(key==KeyEvent.VK_V)
        {
            vPressed=false;

        }
        if(key==KeyEvent.VK_SPACE)
        {
            shotPressed=false;
        }
    }

//    public void OptionState(int code)
//    {
//        if(code==KeyEvent.VK_O)
//        {gp.gameState=gp.
//    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

}
