package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartScreen extends JPanel {

    public StartScreen(ActionListener startGameAction) {

        setLayout(new BorderLayout());
        setBackground(Color.black);

        // TÍTULO
        JLabel title = new JLabel("SnakeParce", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.yellow);

        // BOTÓN
        JButton startButton = new JButton("Iniciar Juego");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setFocusPainted(false);

        // Acción del botón
        startButton.addActionListener(startGameAction);

        // Panel inferior para centrar botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.black);
        bottomPanel.add(startButton);

        add(title, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}