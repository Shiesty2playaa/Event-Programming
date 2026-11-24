import java.util.Observable;

// === LEGACY SUBJECT using java.util.Observable (Deprecated!) ===
// Notice: We must EXTEND Observable. This is a key limitation because 
// Java only allows single inheritance (a class cannot extend anything else).
@SuppressWarnings("deprecation")
public class LegacyWeatherData extends Observable {
    private float temperature;
    private float humidity;

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        
        // REQUIRED: You must call setChanged() before notifyObservers(), 
        // otherwise observers will silently not be notified!
        setChanged();
        
        // Notify all observers (notice: no data is passed here - this is the "pull model")
        notifyObservers();
    }

    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
}