import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Coin extends GameObject implements Collectible, Collidable {
    private final double radius = 10;
    private boolean collected = false;

    public Coin(double x, double y) {
        super(x, y);
    }

    @Override
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void collect() {
        collected = true;
    }

    @Override
    public void update() throws GameException {
        
    }

    @Override
    public void render(GraphicsContext gc) {
        if (collected) return;

        double cx = getX();
        double cy = getY();

        gc.setFill(Color.GOLD);
        gc.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);
        gc.setStroke(Color.ORANGE);
        gc.strokeOval(cx - radius, cy - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean collidesWith(Ball ball) {
        double dx = ball.getX() - getX();
        double dy = ball.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (radius + ball.getRadius());
    }
}