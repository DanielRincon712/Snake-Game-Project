package game;

import java.util.Random;

public class Food {

    public int x;
    public int y;

    Random random;

    int boardWidth;
    int boardHeight;
    int tileSize;

    public Food(int boardWidth, int boardHeight, int tileSize) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tileSize = tileSize;

        random = new Random();
        placeFood();
    }

    public void placeFood() {
        x = random.nextInt(boardWidth / tileSize);
        y = random.nextInt(boardHeight / tileSize);
    }
}

