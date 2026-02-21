import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Obstacle extends GameObject implements Collidable {
    private final double width;
    private final double height;

    public Obstacle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() throws GameException {
    }

    @Override
    public void render(GraphicsContext gc) {
        double ox = getX();
        double oy = getY();

        gc.setFill(Color.DARKRED);
        gc.fillRoundRect(ox, oy, width, height, 10, 10);
        gc.setStroke(Color.BLACK);
        gc.strokeRoundRect(ox, oy, width, height, 10, 10);
    }

    @Override
    public boolean collidesWith(Ball ball) {
        double bx = ball.getX();
        double by = ball.getY();
        double r = ball.getRadius();

        return bx + r > getX() &&
               bx - r < getX() + width &&
               by + r > getY() &&
               by - r < getY() + height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
}