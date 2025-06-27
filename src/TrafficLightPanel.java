import javax.swing.*;
import java.awt.*;

public class TrafficLightPanel extends JPanel {
    private TrafficLight light;
    private JLabel label;
    private String name;

    public TrafficLightPanel(TrafficLight light, String name) {
        this.light = light;
        this.name = name;
        this.label = new JLabel(light.getState().toString(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        setBorder(BorderFactory.createTitledBorder(name));
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        setPreferredSize(new Dimension(100, 100));
    }

    public void updateLight() {
        SwingUtilities.invokeLater(() -> {
            label.setText(light.getState().toString());
            switch (light.getState()) {
                case RED -> setBackground(Color.RED);
                case GREEN -> setBackground(Color.GREEN);
                case YELLOW -> setBackground(Color.YELLOW);
            }
            repaint();
        });
    }

    public TrafficLight getLight() {
        return light;
    }
}
