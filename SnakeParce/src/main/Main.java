package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.SnakeGame;
import ui.StartScreen;

public class Main {
    public static void main(String[] args) {

        int AnchoTablero = 600;
        int AlturaTablero = 600;

        JFrame frame = new JFrame("SnakeParce");
        frame.setSize(AnchoTablero, AlturaTablero);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Acción para iniciar el juego
        ActionListener startGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SnakeGame game = new SnakeGame(AnchoTablero, AlturaTablero);

                frame.getContentPane().removeAll();
                frame.add(game);
                frame.pack();

                game.requestFocus();

                frame.revalidate();
                frame.repaint();
            }
        };

        // Pantalla inicial
        StartScreen startScreen = new StartScreen(startGame);

        frame.add(startScreen);
        frame.setVisible(true);
    }
}