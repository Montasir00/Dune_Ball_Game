import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Obstacle extends GameObject {
    private double width, height;

    public Obstacle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        // No movement for now
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.DARKRED);
        gc.fillRoundRect(x, y, width, height, 10, 10);
        gc.setStroke(Color.BLACK);
        gc.strokeRoundRect(x, y, width, height, 10, 10);
    }

    public boolean collidesWith(Ball ball) {
        return ball.getX() + 20 > x && ball.getX() - 20 < x + width &&
               ball.getY() + 20 > y && ball.getY() - 20 < y + height;
    }
}
