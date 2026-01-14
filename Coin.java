import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Coin extends GameObject {
    private double radius = 10;
    private boolean collected = false;

    public Coin(double x, double y) {
        super(x, y);
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    @Override
    public void update() {
        // static, nothing needed
    }

    @Override
    public void render(GraphicsContext gc) {
        if (collected) return;

        gc.setFill(Color.GOLD);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        gc.setStroke(Color.ORANGE);
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public boolean collidesWith(Ball ball) {
        double dx = ball.getX() - x;
        double dy = ball.getY() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (radius + 20);
    }
}
