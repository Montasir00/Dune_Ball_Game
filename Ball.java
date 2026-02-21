import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;

public class Ball extends GameObject {
    private final double radius = 20;
    private final Terrain terrain;
    private double dx = 0;
    private double dy = 0;
    private final double gravity = 0.4;
    private boolean onGround = false;
    private boolean invincible = false;

    public Ball(double x, double y, Terrain terrain) {
        super(x, y);
        this.terrain = terrain;
    }

    public void jump(long pressDuration) {
        if (onGround) {
            double jumpForce = Math.min(pressDuration / 15.0, 12);
            dy = -jumpForce;
            onGround = false;
        }
    }

    public void moveLeft() {
        dx = -3;
    }

    public void moveRight() {
        dx = 3;
    }

    public void stopHorizontal() {
        dx *= 0.85;
        if (Math.abs(dx) < 0.05) dx = 0;
    }

    @Override
    public void update() throws GameException {
        setX(getX() + dx);
        dy += gravity;
        if (dy > 8) dy = 8;
        setY(getY() + dy);
        terrain.extendIfNeeded(getX());
        double terrainY = terrain.getYAt(getX());

        if (getY() + radius >= terrainY) {
            setY(terrainY - radius);
            dy = 0;
            onGround = true;
        } else {
            onGround = false;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (invincible) {
            gc.setStroke(Color.CYAN);
            gc.setLineWidth(4);
            gc.strokeOval(getX() - radius - 2, getY() - radius - 2, 
                         (radius + 2) * 2, (radius + 2) * 2);
        }

        RadialGradient gradient = new RadialGradient(
                0, 0, getX(), getY(), radius, false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.LIGHTBLUE),
                new Stop(1, Color.DODGERBLUE)
        );
        gc.setFill(gradient);
        gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    public double getRadius() {
        return radius;
    }

    public void setInvincible(boolean value) {
        this.invincible = value;
    }

    public boolean isInvincible() {
        return invincible;
    }
}