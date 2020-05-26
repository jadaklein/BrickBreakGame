package BreakoutGame;

import javax.swing.*;
import java.awt.EventQueue;

public class Breakout extends JFrame {
    private String playerName;

    public String returnUsername() {
        return playerName;
    }

    public Breakout() {

        initUI();
    }

    private void initUI() {

        add(new Board());
        setTitle("Brick Break");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    //main method to start the game
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {//not really sure what this does

            var game = new Breakout();
            game.setVisible(true);
        });
    }
}
