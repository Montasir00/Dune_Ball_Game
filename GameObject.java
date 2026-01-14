import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected double x, y;
    protected double width = 0;
    protected double height = 0;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(double x, double y, double width, double height) {
        this(x, y);
        this.width = width;
        this.height = height;
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);
}
