import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PowerUp extends GameObject implements Collectible, Collidable, PowerUpEffect {
    private final double radius = 12;
    private boolean collected = false;
    private final double startY;
    private long collectedTime;
    private final long shieldDuration = 5000;

    public PowerUp(double x, double y) {
        super(x, y);
        this.startY = y;
    }

    public boolean isExpired() {
    return collected && System.currentTimeMillis() - collectedTime >= shieldDuration;
    }

    @Override
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void collect() {
        collected = true;
        collectedTime = System.currentTimeMillis();
    }

    @Override
    public void applyEffect(Ball ball) {
        ball.setInvincible(true);
    }

    @Override
    public void updateEffect(Ball ball) {
        if (collected && System.currentTimeMillis() - collectedTime >= shieldDuration) {
            ball.setInvincible(false);
        }
    }

    @Override
    public void update() throws GameException {
        if (!collected) {
            setY(startY + Math.sin(System.currentTimeMillis() / 200.0) * 10);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (collected) return;

        double cx = getX();
        double cy = getY();

        gc.setFill(Color.CYAN);
        gc.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);

        gc.setStroke(Color.LIGHTBLUE);
        gc.setLineWidth(2);
        gc.strokeOval(cx - radius, cy - radius, radius * 2, radius * 2);

        gc.setFill(Color.TEAL);
        gc.fillOval(cx - radius / 2, cy - radius / 2, radius, radius);
    }

    @Override
    public boolean collidesWith(Ball ball) {
        double dx = ball.getX() - getX();
        double dy = ball.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (radius + ball.getRadius());
    }
}