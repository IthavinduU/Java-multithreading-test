import javax.swing.*;

public class ClockThread extends Thread {
    private JLabel clockLabel;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private int time = 0;

    public ClockThread(JLabel label) {
        this.clockLabel = label;
    }

    public void run() {
        while (running) {
            if (!paused) {
                SwingUtilities.invokeLater(() -> clockLabel.setText("Time: " + time + "s"));
                time++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }

    public void pauseClock() {
        paused = true;
    }

    public void resumeClock() {
        paused = false;
    }

    public void stopClock() {
        running = false;
    }
}
