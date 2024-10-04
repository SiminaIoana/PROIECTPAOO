package environment;

import main.GamePanel;

import java.awt.*;
//Este o clasa responsabilă de gestionarea mediului din joc. În această implementare, se ocupă în special de gestionarea luminii
public class EnvironmentManager {

    GamePanel gp;
    Light lighting;

    public EnvironmentManager(GamePanel gp)
    {
        this.gp=gp;
    }

    public void setup()
    {
        lighting=new Light(gp,450);
    }

    public void Draw(Graphics2D g2)
    {
        lighting.Draw(g2);
    }
}
