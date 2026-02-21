import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy extends GameObject implements Collidable {
    private final double radius = 15;
    private final double speed = 1.5;
    private final double startX;
    private final double patrolDistance = 100;
    private boolean movingRight = true;

    public Enemy(double x, double y) {
        super(x, y);
        this.startX = x;
    }

    @Override
    public void update() throws GameException {
        if (movingRight) {
            setX(getX() + speed);
            if (getX() > startX + patrolDistance) {
                movingRight = false;
            }
        } else {
            setX(getX() - speed);
            if (getX() < startX - patrolDistance) {
                movingRight = true;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        double cx = getX();
        double cy = getY();

        gc.setFill(Color.PURPLE);
        gc.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean collidesWith(Ball ball) {
        double dx = ball.getX() - getX();
        double dy = ball.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (radius + ball.getRadius());
    }
}