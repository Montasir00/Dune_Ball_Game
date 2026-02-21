public interface PowerUpEffect {
    void applyEffect(Ball ball);
    void updateEffect(Ball ball);
    boolean isExpired();  
}