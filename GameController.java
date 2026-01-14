import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameController {
    private Pane root;
    private Canvas canvas;
    private GraphicsContext gc;
    private Game game;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean jumpPressed = false;
    private boolean paused = false;
    private boolean started = false;
    private boolean gameOver = false;
    private long jumpStartTime = 0;

    public GameController() {
        root = new Pane();
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        game = new Game();
    }

    public Pane getRoot() {
        return root;
    }

    public void initKeyListeners(Scene scene) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT -> leftPressed = true;
                case RIGHT -> rightPressed = true;
                case SPACE -> {
                    if (!jumpPressed && started && !paused && !gameOver) {
                        jumpPressed = true;
                        jumpStartTime = System.currentTimeMillis();
                    }
                }
                case ENTER -> started = true;
                case P -> paused = !paused;
                case R -> {
                    if (gameOver || !started) {
                        game = new Game();
                        gameOver = false;
                        started = true;
                    }
                }
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT -> leftPressed = false;
                case RIGHT -> rightPressed = false;
                case SPACE -> {
                    if (jumpPressed && started && !paused && !gameOver) {
                        long heldTime = System.currentTimeMillis() - jumpStartTime;
                        game.jumpBall(heldTime);
                        jumpPressed = false;
                    }
                }
            }
        });
    }

    public void startGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!started) {
                    renderStartScreen();
                    return;
                }

                if (gameOver) {
                    renderGameOverScreen();
                    return;
                }

                if (!paused) {
                    game.update(leftPressed, rightPressed);
                }

                if (game.isGameOver()) {
                    gameOver = true;
                }

                render();
            }
        };
        timer.start();
    }

    private void render() {
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, 800, 600);
        game.render(gc);

        if (paused) {
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Arial", 36));
            gc.fillText("PAUSED", 330, 300);
        }
    }

    private void renderStartScreen() {
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", 30));
        gc.fillText("DUNE BALL GAME", 270, 250);
        gc.setFont(Font.font("Arial", 20));
        gc.fillText("Press ENTER to Start", 290, 300);
    }

    private void renderGameOverScreen() {
        render(); // show last frame
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", 36));
        gc.fillText("GAME OVER", 300, 280);
        gc.setFont(Font.font("Arial", 18));
        gc.setFill(Color.BLACK);
        gc.fillText("Press R to Restart", 315, 320);
    }
}
