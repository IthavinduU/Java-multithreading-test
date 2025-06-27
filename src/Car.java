public class Car {
    private int id;
    private double x = 0;
    private double speed = 20; // m/s

    public Car(int id) {
        this.id = id;
    }

    public synchronized void updatePosition() {
        x += speed;
        if (x > 1000) x = 0;
    }

    public synchronized double getX() {
        return x;
    }

    public double getSpeed() {
        return speed;
    }

    public int getId() {
        return id;
    }
}
