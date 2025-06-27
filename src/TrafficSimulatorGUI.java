import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrafficSimulatorGUI extends JFrame {
    private JLabel clockLabel;
    private List<TrafficLightPanel> trafficLightPanels = new ArrayList<>();
    private List<CarPanel> carPanels = new ArrayList<>();
    private SimulationController controller;

    // Now accessible for dynamic layout updates
    private JPanel carPanel;
    private JPanel lightPanel;

    public TrafficSimulatorGUI() {
        setTitle("Traffic Simulation - CMSC 335");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Clock
        clockLabel = new JLabel("Time: 0s", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(clockLabel, BorderLayout.NORTH);

        // Lights and cars
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));

        // Traffic lights panel
        lightPanel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            TrafficLight light = new TrafficLight();
            TrafficLightPanel panel = new TrafficLightPanel(light, "Intersection " + (i + 1));
            trafficLightPanels.add(panel);
            lightPanel.add(panel);
        }

        // Car panels
        carPanel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            Car car = new Car(i);
            CarPanel panel = new CarPanel(car);
            carPanels.add(panel);
            carPanel.add(panel);
        }

        centerPanel.add(lightPanel);
        centerPanel.add(carPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel controlPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton pauseBtn = new JButton("Pause");
        JButton resumeBtn = new JButton("Resume");
        JButton stopBtn = new JButton("Stop");
        JButton addCarBtn = new JButton("Add Car");
        JButton addIntersectionBtn = new JButton("Add Intersection");

        controlPanel.add(startBtn);
        controlPanel.add(pauseBtn);
        controlPanel.add(resumeBtn);
        controlPanel.add(stopBtn);
        controlPanel.add(addCarBtn);
        controlPanel.add(addIntersectionBtn);
        add(controlPanel, BorderLayout.SOUTH);

        // Controller
        controller = new SimulationController(clockLabel, trafficLightPanels, carPanels);

        // Button actions
        startBtn.addActionListener(e -> controller.startSimulation());
        pauseBtn.addActionListener(e -> controller.pauseSimulation());
        resumeBtn.addActionListener(e -> controller.resumeSimulation());
        stopBtn.addActionListener(e -> controller.stopSimulation());

        addCarBtn.addActionListener(e -> {
            int newId = carPanels.size();
            Car newCar = new Car(newId);
            CarPanel newCarPanel = new CarPanel(newCar);
            carPanels.add(newCarPanel);
            carPanel.setLayout(new GridLayout(1, carPanels.size()));
            carPanel.add(newCarPanel);
            carPanel.revalidate();
            carPanel.repaint();
            controller.addCarThread(newCarPanel);
        });

        addIntersectionBtn.addActionListener(e -> {
            TrafficLight newLight = new TrafficLight();
            String name = "Intersection " + (trafficLightPanels.size() + 1);
            TrafficLightPanel newLightPanel = new TrafficLightPanel(newLight, name);
            trafficLightPanels.add(newLightPanel);
            lightPanel.setLayout(new GridLayout(1, trafficLightPanels.size()));
            lightPanel.add(newLightPanel);
            lightPanel.revalidate();
            lightPanel.repaint();
            controller.addTrafficLightThread(newLightPanel);
        });

        setVisible(true);
    }
}
