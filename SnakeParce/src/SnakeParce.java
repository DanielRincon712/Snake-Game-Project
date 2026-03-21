import java.awt.*; // Librerías para gráficos (dibujar en pantalla)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Para el Timer (loop del juego)
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; // Para detectar teclado
import java.util.ArrayList; // Lista dinámica (cuerpo de la serpiente)
import java.util.Random; // Para posiciones aleatorias de la comida
import javax.swing.*; // Componentes gráficos (JPanel, Timer, etc.)

public class SnakeParce extends JPanel implements ActionListener, KeyListener {

    // Clase interna que representa una posición (x, y) en el tablero
    private class Tile {
        int x;
        int y;

        // Constructor: crea un objeto Tile con coordenadas x e y
        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Dimensiones del tablero
    int AnchoTablero;
    int AlturaTablero;

    // Tamaño de cada bloque del juego (cada "cuadrito")
    int tileSize = 25;

    // Snake (serpiente)
    Tile snakeHead; // Cabeza de la serpiente
    ArrayList<Tile> snakeBody; // Lista del cuerpo de la serpiente

    // Comida
    Tile food;

    // Generador de números aleatorios
    Random random;

    // Lógica del juego
    Timer gameLoop; // Controla el tiempo del juego (loop)
    int velocityX; // Dirección horizontal
    int velocityY; // Dirección vertical
    boolean gameOver = false; // Estado del juego

    // Constructor: inicializa todo el juego
    SnakeParce(int AnchoTablero, int AlturaTablero) {

        // Guardamos dimensiones
        this.AnchoTablero = AnchoTablero;
        this.AlturaTablero = AlturaTablero;

        // Configuración visual del panel
        setPreferredSize(new Dimension(this.AnchoTablero, this.AlturaTablero)); // Tamaño
        setBackground(Color.black); // Fondo negro
        addKeyListener(this); // Activamos detección de teclado
        setFocusable(true); // Permite recibir eventos del teclado

        // Inicializamos la cabeza de la serpiente en posición (5,5)
        snakeHead = new Tile(5, 5);

        // Inicializamos el cuerpo vacío
        snakeBody = new ArrayList<Tile>();

        // Inicializamos la comida
        food = new Tile(10, 10);
        random = new Random(); // Inicializa generador aleatorio
        placeFood(); // Coloca la comida en posición aleatoria

        // La serpiente inicia sin moverse
        velocityX = 0;
        velocityY = 0;

        // Timer: ejecuta el juego cada 100 milisegundos
        gameLoop = new Timer(100, this);
        gameLoop.start(); // Inicia el loop del juego
    }

    // Método que se ejecuta para dibujar el juego

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia la pantalla antes de dibujar
        draw(g); // Llama a nuestro método personalizado
    }

    public void draw(Graphics g) {

    //  Fondo (negro elegante)
    g.setColor(Color.black);
    g.fillRect(0, 0, AnchoTablero, AlturaTablero);

    //  Comida (rojo)
    g.setColor(Color.red);
    g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

    //  Cabeza (amarillo - bandera Colombia)
    g.setColor(Color.yellow);
    g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

    for (int i = 0; i < snakeBody.size(); i++) {
    Tile snakePart = snakeBody.get(i);

    // Alternar colores tipo bandera Colombia
    if (i % 3 == 0) {
        g.setColor(Color.yellow); // Amarillo
    } else if (i % 3 == 1) {
        g.setColor(Color.blue); // Azul
    } else {
        g.setColor(Color.red); // Rojo
    }

    g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
    }

    //  PUNTAJE (arriba centrado)
    g.setColor(Color.white);
    g.setFont(new Font("Arial", Font.BOLD, 20));

    String scoreText = "Puntaje: " + snakeBody.size();
    int scoreWidth = g.getFontMetrics().stringWidth(scoreText);

    g.drawString(scoreText, (AnchoTablero - scoreWidth) / 2, 25);

    //  GAME OVER + MENSAJE BONITO
    if (gameOver) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        String gameOverText = "GAME OVER PARCE";
        int textWidth = g.getFontMetrics().stringWidth(gameOverText);
        g.drawString(gameOverText, (AnchoTablero - textWidth) / 2, AlturaTablero / 2 - 20);

        // Mensaje de reinicio
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(Color.white);

        String restartText = "Presiona R o ENTER para volver a jugar";
        int restartWidth = g.getFontMetrics().stringWidth(restartText);

        g.drawString(restartText, (AnchoTablero - restartWidth) / 2, AlturaTablero / 2 + 20);
    }
}

    // Método para colocar la comida en una posición aleatoria
    public void placeFood() {
        food.x = random.nextInt(AnchoTablero / tileSize); // Número aleatorio en X
        food.y = random.nextInt(AlturaTablero / tileSize); // Número aleatorio en Y
    }

    // Método para detectar colisión entre dos objetos
    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    // Método principal del movimiento del juego
    public void move() {

        // Verifica si la serpiente come la comida
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y)); // Crece la serpiente
            placeFood(); // Nueva comida
        }

        // Movimiento del cuerpo (de atrás hacia adelante)
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);

            if (i == 0) {
                // El primer segmento sigue a la cabeza
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                // Cada segmento sigue al anterior
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Movimiento de la cabeza
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Detecta colisión con el propio cuerpo
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        // Detecta colisión con los bordes
        if (snakeHead.x * tileSize < 0 || snakeHead.x * tileSize > AnchoTablero ||
            snakeHead.y * tileSize < 0 || snakeHead.y * tileSize > AlturaTablero) {
            gameOver = true;
        }
    }

    // Método que se ejecuta cada vez que el Timer hace "tick"
    @Override
    public void actionPerformed(ActionEvent e) {
        move(); // Actualiza lógica del juego
        repaint(); // Redibuja pantalla

        if (gameOver) {
            gameLoop.stop(); // Detiene el juego
        }
    }

    // Metodo para reiniciar el juego
    public void restartGame() {
    snakeHead = new Tile(5, 5); // Reinicia cabeza
    snakeBody.clear(); // Borra el cuerpo

    velocityX = 0;
    velocityY = 0;

    gameOver = false; // Quita game over

    placeFood(); // Nueva comida

    gameLoop.start(); // Reinicia el loop
    }


    // Detecta cuando se presiona una tecla
    @Override
    public void keyPressed(KeyEvent e) {

        // Movimiento hacia arriba
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }
        // Movimiento hacia abajo
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }
        // Movimiento hacia la izquierda
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
        // Movimiento hacia la derecha
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }

        // Reiniciar juego con tecla Enter y R
        if ((e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_ENTER) && gameOver) {
        restartGame();
        }
    }

    // Métodos obligatorios del KeyListener (no usados)
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}