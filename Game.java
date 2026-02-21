import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int WORLD_LENGTH = 15000;
    private final Terrain terrain;
    private final Ball ball;
    private final List<GameObject> entities = new ArrayList<>();
    private final List<Collidable> collidables = new ArrayList<>();
    private final List<Collectible> collectibles = new ArrayList<>();
    private final List<PowerUpEffect> powerUpEffects = new ArrayList<>();
    private boolean gameOver = false;
    private int score = 0;

    public Game() {
        terrain = new Terrain(0, 600);
        terrain.extendIfNeeded(WORLD_LENGTH);
        ball = new Ball(100, 100, terrain);
        entities.add(ball);
        generateGameObjects();
    }

    private void generateGameObjects() {
        for (int i = 400; i < WORLD_LENGTH; i += 700) {
            try {
                Obstacle o = new Obstacle(i, terrain.getYAt(i) - 40, 30, 30);
                entities.add(o);
                collidables.add(o);
            } catch (GameException e) {
                System.err.println("Failed to spawn Obstacle at " + i + ": " + e.getMessage());
            }
        }

        for (int i = 800; i < WORLD_LENGTH; i += 1400) {
            try {
                Enemy e = new Enemy(i, terrain.getYAt(i) - 30);
                entities.add(e);
                collidables.add(e);
            } catch (GameException e) {
                System.err.println("Failed to spawn Enemy at " + i + ": " + e.getMessage());
            }
        }

        for (int i = 100; i < WORLD_LENGTH; i += 120) {
            try {
                Coin c = new Coin(i, terrain.getYAt(i) - 20);
                entities.add(c);
                collectibles.add(c);
                collidables.add(c);
            } catch (GameException e) {
                System.err.println("Failed to spawn Coin at " + i + ": " + e.getMessage());
            }
        }

        for (int i = 3000; i < WORLD_LENGTH; i += 3500) {
            try {
                PowerUp p = new PowerUp(i, terrain.getYAt(i) - 40);
                entities.add(p);
                collectibles.add(p);
                collidables.add(p);
                powerUpEffects.add(p);
            } catch (GameException e) {
                System.err.println("Failed to spawn PowerUp at " + i + ": " + e.getMessage());
            }
        }
    }

    public void update(boolean left, boolean right) {
        try {
            if (gameOver) return;

            if (left) ball.moveLeft();
            else if (right) ball.moveRight();
            else ball.stopHorizontal();

            updateEntities();
            terrain.extendIfNeeded(ball.getX());
            checkCollisions();
            updatePowerUpEffects();

            if (ball.getY() > 800) {
                endGame("You fell off!");
            }
        } catch (GameException e) {
            endGame("Technical error");
        }
    }

    private void updateEntities() throws GameException {
        for (GameObject obj : entities) {
            obj.update();
        }
    }

    private void updatePowerUpEffects() {
        powerUpEffects.removeIf(effect -> {
            effect.updateEffect(ball);
            return effect.isExpired();
        });
    }

    private void checkCollisions() {
        for (Collidable c : collidables) {
            if (c.collidesWith(ball) && !ball.isInvincible()) {
                if (c instanceof Enemy || c instanceof Obstacle) {
                    endGame("Collision with " + c.getClass().getSimpleName());
                    return;
                }
            }
        }

        for (Collectible collectible : collectibles) {
            if (!collectible.isCollected() && 
                collectible instanceof Collidable collidable &&
                collidable.collidesWith(ball)) {
                
                collectible.collect();
                score += (collectible instanceof Coin) ? 10 : 25;
                
                if (collectible instanceof PowerUpEffect effect) {
                    effect.applyEffect(ball);
                }
            }
        }
    }

    public void render(GraphicsContext gc) {
        double cameraX = Math.max(0, ball.getX() - 300);
        gc.save();
        gc.translate(-cameraX, 0);

        terrain.render(gc);
        for (GameObject obj : entities) {
            obj.render(gc);
        }

        gc.restore();
        
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + score, 700, 30);
    }

    private void endGame(String message) {
        gameOver = true;
        System.out.println(message);
        System.out.println("Final Score: " + score);
    }

    public void jumpBall(long pressTime) {
        ball.jump(pressTime);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Ball getBall() {
        return ball;
    }
}