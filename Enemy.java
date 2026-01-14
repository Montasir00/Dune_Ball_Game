import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy extends GameObject {
    private double radius = 15;
    private double speed = 1.5;
    private boolean movingRight = true;

    private double startX;
    private double patrolDistance = 100;

    public Enemy(double x, double y) {
        super(x, y);
        this.startX = x;
    }

    @Override
    public void update() {
        if (movingRight) {
            x += speed;
            if (x > startX + patrolDistance) {
                movingRight = false;
            }
        } else {
            x -= speed;
            if (x < startX - patrolDistance) {
                movingRight = true;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.PURPLE);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public boolean collidesWith(Ball ball) {
        double dx = ball.getX() - x;
        double dy = ball.getY() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (radius + 20); // 20 = ball radius
    }
}
