package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadImage {

    private static BufferedImage image;

    public static BufferedImage LoadImage(String path)
    {
        /// Avand in vedere exista situatii in care fisierul sursa sa nu poate fi accesat
        /// metoda read() arunca o excpetie ce trebuie tratata
        try
        {
            /// Clasa ImageIO contine o serie de metode statice pentru file IO.
            /// Metoda read() are ca argument un InputStream construit avand ca referinta
            /// directorul res, director declarat ca director de resurse in care se gasesc resursele
            /// proiectului sub forma de fisiere sursa.
            image= ImageIO.read(LoadImage.class.getResourceAsStream(path));
        }
        catch(IOException e)
        {
            /// Afiseaza informatiile necesare depanarii.
            e.printStackTrace();
        }
        return image;
    }
}

