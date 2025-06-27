public class TrafficLight {
    public enum LightState { RED, GREEN, YELLOW }

    private LightState state = LightState.RED;

    public synchronized LightState getState() {
        return state;
    }

    public synchronized void nextState() {
        switch (state) {
            case RED -> state = LightState.GREEN;
            case GREEN -> state = LightState.YELLOW;
            case YELLOW -> state = LightState.RED;
        }
    }
}
