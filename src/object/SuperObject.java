
package object;

import main.GamePanel;

import java.awt.*;
        import java.awt.image.BufferedImage;

//clasa parinte a tuturor obiectelor
public class SuperObject {

    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision=false;

    public int Worldx, Worldy;

    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public void Draw(Graphics2D g2, GamePanel gp)
    {

        int ScreenX=Worldx-gp.player.Worldx+gp.player.ScreenX;
        int ScreenY=Worldy-gp.player.Worldy+gp.player.ScreenY;

        if(Worldx + gp.TILE_SIZE> gp.player.Worldx-gp.player.ScreenX && Worldx-gp.TILE_SIZE<gp.player.Worldx+gp.player.ScreenX
                && Worldy+gp.TILE_SIZE>gp.player.Worldy-gp.player.ScreenY && Worldy-gp.TILE_SIZE<gp.player.Worldy+gp.player.ScreenY)
        {
            if(this.name.equals("Door2"))
                g2.drawImage(image, ScreenX, ScreenY, 2*gp.TILE_SIZE,2*gp.TILE_SIZE,null);
           else if(this.name.equals("Diamant"))
                g2.drawImage( image, ScreenX, ScreenY,  2*gp.TILE_SIZE, 2*gp.TILE_SIZE,null);
            else if(this.name.equals("Key"))
                g2.drawImage((Image) image, ScreenX, ScreenY, (int) (1.5*gp.TILE_SIZE), (int) (1.5*gp.TILE_SIZE),null);
            else
                g2.drawImage(image, ScreenX, ScreenY, gp.TILE_SIZE,gp.TILE_SIZE,null);
        }
    }

}
