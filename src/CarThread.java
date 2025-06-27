public class CarThread extends Thread {
    private CarPanel panel;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public CarThread(CarPanel panel) {
        this.panel = panel;
    }

    public void run() {
        while (running) {
            if (!paused) {
                panel.getCar().updatePosition();
                panel.updatePosition();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }

    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        paused = false;
    }

    public void stopThread() {
        running = false;
    }
}
