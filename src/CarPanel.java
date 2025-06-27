import javax.swing.*;
import java.awt.*;

public class CarPanel extends JPanel {
    private Car car;
    private JLabel label;

    public CarPanel(Car car) {
        this.car = car;
        setBorder(BorderFactory.createTitledBorder("Car " + car.getId()));
        setLayout(new BorderLayout());
        label = new JLabel("X: 0.0 m | Speed: 20 m/s", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        add(label, BorderLayout.CENTER);
    }

    public void updatePosition() {
        SwingUtilities.invokeLater(() -> {
            label.setText(String.format("X: %.1f m | Speed: %.1f m/s", car.getX(), car.getSpeed()));
        });
    }

    public Car getCar() {
        return car;
    }
}
