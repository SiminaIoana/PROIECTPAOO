package main;

import java.awt.image.BufferedImage;

//primeste imaginea actuala si o taie sau redimensioneaza
public class SpriteSheet {

    private BufferedImage image ;
    private static final int    tileWidth   = 64;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 64 ;  /*!< Inaltime unei dale din sprite sheet.*/


    public SpriteSheet(BufferedImage img)
    {
        this.image=img;
    }

    //redimensionam tile ul(TAIEREA DALEI RESPECTIVE DIN SPRITE SHEET)
    public BufferedImage crop(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
}
