import javafx.scene.canvas.GraphicsContext;
public abstract class GameObject {

    private double x;
    private double y;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }

    protected void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update() throws GameException;
    public abstract void render(GraphicsContext gc);
}
