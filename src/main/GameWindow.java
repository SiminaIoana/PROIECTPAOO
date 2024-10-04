package main;

import main.GamePanel;

import javax.swing.*;

public class GameWindow {
    public GameWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("Saving the Fairyland");

        //Creare a unei noi instante Game Panel
        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.pack();

        //fereastra va fi localizata in centrul ecranului
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //Setarile necesare
        gamePanel.setupGame();
        //start joc
        gamePanel.Start();
    }
}