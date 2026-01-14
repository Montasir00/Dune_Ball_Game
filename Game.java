import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Ball ball;
    private Terrain terrain;
    private List<Obstacle> obstacles;
    private List<Enemy> enemies;
    private List<Coin> coins;

    private boolean gameOver = false;
    private int score = 0;

    public Game() {
        terrain = new Terrain(0, 600);
        terrain.extendIfNeeded(3000); // 🆕 Ensure terrain is available for placement

        ball = new Ball(100, 100, terrain);
        obstacles = new ArrayList<>();
        enemies = new ArrayList<>();
        coins = new ArrayList<>();

        generateObstacles();
        generateEnemies();
        generateCoins();
    }

    private void generateObstacles() {
        for (int i = 300; i < 3000; i += 500) {
            double x = i;
            double y = terrain.getYAt(x) - 30; // obstacle height
            obstacles.add(new Obstacle(x, y, 30, 30));
        }
    }

    private void generateEnemies() {
        enemies.add(new Enemy(600, terrain.getYAt(600) - 30));
        enemies.add(new Enemy(1200, terrain.getYAt(1200) - 30));
    }

    private void generateCoins() {
        for (int i = 400; i < 3000; i += 350) {
            double x = i;
            double y = terrain.getYAt(x) - 20; // coin radius
            coins.add(new Coin(x, y));
        }
    }

    public void update(boolean left, boolean right) {
        if (gameOver) return;

        if (left) {
            ball.moveLeft();
        } else if (right) {
            ball.moveRight();
        } else {
            ball.stopHorizontal();
        }

        ball.update();
        terrain.extendIfNeeded(ball.getX());

        for (Obstacle o : obstacles) o.update();
        for (Enemy e : enemies) e.update();
        for (Coin c : coins) c.update();

        for (Obstacle o : obstacles) {
            if (o.collidesWith(ball)) {
                endGame("💥 Hit obstacle!");
            }
        }

        for (Enemy e : enemies) {
            if (e.collidesWith(ball)) {
                endGame("👾 Hit enemy!");
            }
        }

        for (Coin c : coins) {
            if (!c.isCollected() && c.collidesWith(ball)) {
                c.collect();
                score += 10;
            }
        }

        if (ball.getY() > 800) {
            endGame("💀 You fell off!");
        }
    }

    private void endGame(String message) {
        gameOver = true;
        System.out.println(message);
        System.out.println("Final Score: " + score);
    }

    public void render(GraphicsContext gc) {
        double cameraX = ball.getX() - 300;
        if (cameraX < 0) cameraX = 0;

        gc.save();
        gc.translate(-cameraX, 0);

        terrain.render(gc);
        for (Obstacle o : obstacles) o.render(gc);
        for (Enemy e : enemies) e.render(gc);
        for (Coin c : coins) c.render(gc);
        ball.render(gc);

        gc.restore();

        // Score (top right)
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + score, 700, 30);
    }

    public void jumpBall(long pressTime) {
        ball.jump(pressTime);
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
