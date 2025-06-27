public class TrafficLightThread extends Thread {
    private TrafficLightPanel panel;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public TrafficLightThread(TrafficLightPanel panel) {
        this.panel = panel;
    }

    public void run() {
        while (running) {
            if (!paused) {
                try {
                    Thread.sleep(3000);
                    panel.getLight().nextState();
                    panel.updateLight();
                } catch (InterruptedException ignored) {}
            }
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
