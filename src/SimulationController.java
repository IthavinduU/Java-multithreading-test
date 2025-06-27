import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    private ClockThread clockThread;
    private List<TrafficLightThread> lightThreads = new ArrayList<>();
    private List<CarThread> carThreads = new ArrayList<>();
    private JLabel clockLabel;
    private List<TrafficLightPanel> lights;
    private List<CarPanel> cars;

    public SimulationController(JLabel clockLabel, List<TrafficLightPanel> lights, List<CarPanel> cars) {
        this.clockLabel = clockLabel;
        this.lights = lights;
        this.cars = cars;
    }

    public void startSimulation() {
        clockThread = new ClockThread(clockLabel);
        clockThread.start();

        for (TrafficLightPanel panel : lights) {
            TrafficLightThread thread = new TrafficLightThread(panel);
            lightThreads.add(thread);
            thread.start();
        }

        for (CarPanel panel : cars) {
            CarThread thread = new CarThread(panel);
            carThreads.add(thread);
            thread.start();
        }
    }

    public void pauseSimulation() {
        if (clockThread != null) clockThread.pauseClock();
        lightThreads.forEach(TrafficLightThread::pauseThread);
        carThreads.forEach(CarThread::pauseThread);
    }

    public void resumeSimulation() {
        if (clockThread != null) clockThread.resumeClock();
        lightThreads.forEach(TrafficLightThread::resumeThread);
        carThreads.forEach(CarThread::resumeThread);
    }

    public void stopSimulation() {
        if (clockThread != null) clockThread.stopClock();
        lightThreads.forEach(TrafficLightThread::stopThread);
        carThreads.forEach(CarThread::stopThread);
    }

    public void addCarThread(CarPanel panel) {
        CarThread thread = new CarThread(panel);
        carThreads.add(thread);
        thread.start();
    }

    public void addTrafficLightThread(TrafficLightPanel panel) {
        TrafficLightThread thread = new TrafficLightThread(panel);
        lightThreads.add(thread);
        thread.start();
    }

}
