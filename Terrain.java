import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private List<Double> xPoints = new ArrayList<>();
    private List<Double> yPoints = new ArrayList<>();
    private final int segmentLength = 1;
    private final int screenWidth = 800;
    private final int buffer = 200;

    public Terrain(double startX, double height) {
        generateInitial(startX, height);
    }

    private void generateInitial(double startX, double height) {
        for (int i = 0; i < screenWidth + buffer; i++) {
            double x = startX + i * segmentLength;
            double y = getHeightFromFunction(x);
            xPoints.add(x);
            yPoints.add(y);
        }
    }

    private double getHeightFromFunction(double x) {
        return 400 + 30 * Math.sin(x * 0.02) + 15 * Math.sin(x * 0.07);
    }

    public void extendIfNeeded(double ballX) {
        double lastX = xPoints.get(xPoints.size() - 1);
        while (lastX < ballX + screenWidth + buffer) {
            lastX += segmentLength;
            xPoints.add(lastX);
            yPoints.add(getHeightFromFunction(lastX));
        }
    }

    public double getYAt(double x) {
        int i = (int) (x - xPoints.get(0));
        if (i < 0) i = 0;
        if (i >= yPoints.size()) i = yPoints.size() - 1;
        return yPoints.get(i);
    }

    public void render(GraphicsContext gc) {
        LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#d2b48c")),
            new Stop(1, Color.web("#8b5e3c"))
        );

        gc.setFill(gradient);
        gc.beginPath();
        gc.moveTo(xPoints.get(0), 600);
        for (int i = 0; i < xPoints.size(); i++) {
            gc.lineTo(xPoints.get(i), yPoints.get(i));
        }
        gc.lineTo(xPoints.get(xPoints.size() - 1), 600);
        gc.closePath();
        gc.fill();
    }
}
