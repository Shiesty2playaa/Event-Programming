// New observer just to prove how easy it is to add
public class TextLogger implements DataObserver {
    @Override
    public void onDataChanged(int newValue) {
        System.out.println("LOG: value changed to " + newValue);
    }
}
