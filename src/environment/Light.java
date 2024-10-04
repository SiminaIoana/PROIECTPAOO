package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Light {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Light(GamePanel gp, int circle)
    {
        //imaginea
        darknessFilter=new BufferedImage(gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2=(Graphics2D)darknessFilter.getGraphics();

        Area screenArea= new Area(new Rectangle2D.Double(0,0,gp.SCREEN_WIDTH,gp.SCREEN_HEIGHT));

        //obtinerea centrului ariei care va fi luminata
        int centerX=gp.player.ScreenX+(gp.TILE_SIZE)/2;
        int centerY=gp.player.ScreenY+(gp.TILE_SIZE)/2;

        //
        double x=centerX-((double) circle /2);
        double y=centerY-((double) circle /2);

        Shape circleShape= new Ellipse2D.Double(x,y,circle,circle);

        //aria de luminii
        Area lightArea= new Area(circleShape);

        //diferentierea intre lumina si restul mapei care va fi intunecata
        screenArea.subtract(lightArea);


        //efect de granulatie
        Color[] color=new Color[5];
        float[] fraction=new float[5];

        color[0]= new Color(0,0,0,0f);
        color[1]= new Color(0,0,0,0.25f);
        color[2]= new Color(0,0,0,0.50f);
        color[3]= new Color(0,0,0,0.75f);
        color[4]= new Color(0,0,0,0.95f);

        fraction[0]=0f;
        fraction[1]=0.25f;
        fraction[2]=0.5f;
        fraction[3]=0.75f;
        fraction[4]=1f;

        //crearea unei imagini de gradatie
        //un fel de setare de desenare pentru lumina
        RadialGradientPaint gradPaint=new RadialGradientPaint(centerX,centerY,(circle/2),fraction,color);

        g2.setPaint(gradPaint);

        //desenarea cercului de lumina
        g2.fill(lightArea);

        //desenarea fara lumina cercului
        g2.fill(screenArea);
        g2.dispose();
    }
    public void Draw(Graphics2D g2)
    {
        g2.drawImage(darknessFilter,0,0,null);
    }
}
