import javax.swing.*; // Librería para interfaces gráficas (ventanas, botones, etc.)

public class App {
    public static void main(String[] args) throws Exception {

        // Definimos el tamaño del tablero del juego
        int AnchoTablero = 600;
        int AlturaTablero = AnchoTablero; // Se hace cuadrado

        // Creamos la ventana principal del juego
        JFrame frame = new JFrame("SnakeParce");

        frame.setVisible(true); // Hace visible la ventana
        frame.setSize(AnchoTablero, AlturaTablero); // Tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setResizable(false); // Evita que el usuario cambie el tamaño
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa al cerrar ventana

        // Creamos el objeto del juego (donde está toda la lógica)
        SnakeParce snakeGame = new SnakeParce(AnchoTablero, AlturaTablero);

        frame.add(snakeGame); // Añadimos el juego a la ventana
        frame.pack(); // Ajusta la ventana al contenido
        snakeGame.requestFocus(); // Permite que el juego detecte teclas
    }
}