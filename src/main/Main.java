package main;

import main.GamePanel;

import javax.swing.*;

import static main.GameSingleton.getGameInstance;

public class Main {
    public static void main(String[] args) {
        GameWindow game=getGameInstance();
    }
}