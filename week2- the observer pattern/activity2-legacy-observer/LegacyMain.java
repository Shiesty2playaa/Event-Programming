public class LegacyMain {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        // 1. Create the legacy subject and display
        LegacyWeatherData data = new LegacyWeatherData();
        LegacyDisplay display = new LegacyDisplay();

        // 2. Add the observer using the built-in addObserver method
        data.addObserver(display);

        // 3. Simulate measurements changing
        data.setMeasurements(26.0f, 60.0f);
        data.setMeasurements(30.0f, 75.0f);
    }
}