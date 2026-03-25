package game;

import java.util.ArrayList;

public class Snake {

    // Clase interna para posiciones
    public static class Tile {
        public int x;
        public int y;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Tile head;
    public ArrayList<Tile> body;

    public int velocityX;
    public int velocityY;

    public Snake() {
        head = new Tile(5, 5);
        body = new ArrayList<>();

        velocityX = 0;
        velocityY = 0;
    }

    public void move() {

        // Movimiento del cuerpo
        for (int i = body.size() - 1; i >= 0; i--) {
            Tile part = body.get(i);

            if (i == 0) {
                part.x = head.x;
                part.y = head.y;
            } else {
                Tile prev = body.get(i - 1);
                part.x = prev.x;
                part.y = prev.y;
            }
        }

        // Movimiento de la cabeza
        head.x += velocityX;
        head.y += velocityY;
    }

    public void grow(int x, int y) {
        body.add(new Tile(x, y));
    }

    public boolean collision(Tile a, Tile b) {
        return a.x == b.x && a.y == b.y;
    }

    public boolean checkSelfCollision() {
        for (Tile part : body) {
            if (collision(head, part)) {
                return true;
            }
        }
        return false;
    }
}