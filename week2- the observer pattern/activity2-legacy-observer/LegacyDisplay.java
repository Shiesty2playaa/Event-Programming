import java.util.Observable;
import java.util.Observer;

// === LEGACY OBSERVER using java.util.Observer (Deprecated!) ===
// The update() method takes the Observable object and an optional Object argument.
@SuppressWarnings("deprecation")
public class LegacyDisplay implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        // Since it's a pull model, the observer must check the type 
        // and explicitly call "getter" methods to pull data out of the Subject.
        if (o instanceof LegacyWeatherData) {
            LegacyWeatherData data = (LegacyWeatherData) o;
            System.out.println("Legacy Display -> Temp: " + data.getTemperature() 
                + "°C, Humidity: " + data.getHumidity() + "%");
        }
    }
}