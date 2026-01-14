import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;

public class Ball extends GameObject {
    private double radius = 20;
    private double dx = 0;
    private double dy = 0;
    private double gravity = 0.4;
    private boolean onGround = false;

    private Terrain terrain;

    public Ball(double x, double y, Terrain terrain) {
        super(x, y);
        this.terrain = terrain;
    }

    public void jump(long pressDuration) {
        if (onGround) {
            double jumpForce = Math.min(pressDuration / 15.0, 12); // stronger but smooth
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
    public void update() {
        x += dx;

        dy += gravity;
        if (dy > 8) dy = 8; // terminal velocity
        y += dy;

        terrain.extendIfNeeded(x);
        double terrainY = terrain.getYAt(x);
        if (y + radius >= terrainY) {
            y = terrainY - radius;
            dy = 0;
            onGround = true;
        } else {
            onGround = false;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        RadialGradient gradient = new RadialGradient(
            0, 0, x, y, radius, false, CycleMethod.NO_CYCLE,
            new Stop(0, Color.LIGHTBLUE),
            new Stop(1, Color.DODGERBLUE)
        );
        gc.setFill(gradient);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
