import java.util.ArrayList;
import java.util.List;

// === CONCRETE SUBJECT ===
public class WeatherData implements Subject {
    private List<Observer> observers; // List to hold all registered observers
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>(); // Initialize the observer list
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("[WeatherData] Observer registered: " + o.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
        System.out.println("[WeatherData] Observer removed: " + o.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        // Loop through every observer and send them the updated data
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }

    // Triggered when new weather measurements arrive
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        System.out.println("\n[WeatherData] New measurements received!");
        notifyObservers(); 
    }
}